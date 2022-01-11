package com.example.booksapp.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.booksapp.R
import com.example.booksapp.activities.SHARED_PREF
import com.example.booksapp.activities.STATE
import com.example.booksapp.activities.USERID
import com.example.booksapp.databinding.FragmentDetailsBinding
import com.example.booksapp.models.Book
import com.example.booksapp.models.MyListModel
import com.example.booksapp.viewmodel.BooksViewModel
import com.example.booksapp.viewmodel.MyListViewModel
import com.squareup.picasso.Picasso
import retrofit2.http.Url

private const val TAG = "DetailsFragment"

class DetailsFragment() : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val booksViewModel: BooksViewModel by activityViewModels()
    private val myListViewModel: MyListViewModel by activityViewModels()
    private lateinit var book: Book
    lateinit var sharedPref: SharedPreferences

    private var userID: String = ""

    private lateinit var webView: WebView

    var list = listOf<MyListModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()

        myListViewModel.callMyList()

        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

        userID = sharedPref.getString(USERID, "")!!


        binding.imageButton.setOnClickListener {

            if (sharedPref.getBoolean(STATE, false)) {

                Log.d(TAG, "The state inside the if is ${sharedPref.getBoolean(STATE, false)}")
                Log.d(TAG, "user id: $userID")

                // Check the book already exists in MyList
                var state: Boolean = true
                Log.d(TAG, "List val: $list")
                for (l in list) {
                    if (book.title == l.name) {
                        Log.d(TAG, "book: ${book.title}, list:  ${l.name}")
                        Log.d(TAG, "book object: $book, list:  $l")
                        state = false
                        break
                    }
                }

                if (state) {
                    Log.d(TAG, "inside state if")
                    booksViewModel.addBooks(book, "")
                    findNavController().navigate(R.id.action_detailsFragment_to_myListFragment2)
                } else {
                    Log.d(TAG, "inside state else")
                    Toast.makeText(
                        requireContext(),
                        "the item is already exist",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                findNavController().navigate(R.id.action_detailsFragment_to_loginFragment)
            }
        }

        binding.readMeButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(book.url));
            startActivity(browserIntent);
        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun observers() {

        booksViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, {
            binding.webProgressBar.animate().alpha(0f).duration = 1500
            book = it
            binding.autherTextview.text = it.authors
            binding.subtitleTextView.text = it.subtitle
            Picasso.get().load(it.image).into(binding.bookDetailsImageView)

            //call web view function
            showWebView(it.url)
        })

        // Get the user saved book marked to compare its ids with the newly added book (avoid duplicate)
        myListViewModel.myListLiveData.observe(viewLifecycleOwner, {

            Log.d(TAG, "the marked list: $it")
            it?.let {
                val filterList = it.filter { Item ->
                    Log.d(TAG, "userId: $userID, ${userID == Item.userid}")
                    userID == Item.userid
                }
                list = filterList
                Log.d(TAG, "the filtered list: $list")
            }
        })

        booksViewModel.addLiveData.observe(viewLifecycleOwner, {


        })


    }


    /**
     * WebView is a view that display web pages inside your application.
     */
    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    fun showWebView(url: String) {
        webView = binding.webView

        // WebViewClient allows you to handle
        webView.webViewClient = WebViewClient()

        // this will load the url of the website
        webView.apply {
            loadUrl(url)

            // this will enable the javascript settings
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }

    }
}
