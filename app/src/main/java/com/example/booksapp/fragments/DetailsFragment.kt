package com.example.booksapp.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.booksapp.R
import com.example.booksapp.activities.SHARED_PREF
import com.example.booksapp.activities.STATE
import com.example.booksapp.databinding.FragmentDetailsBinding
import com.example.booksapp.models.Book
import com.example.booksapp.viewmodel.BooksViewModel
import com.squareup.picasso.Picasso
import retrofit2.http.Url


class DetailsFragment() : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val booksViewModel: BooksViewModel by activityViewModels()
    private lateinit var book: Book
    lateinit var sharedPref: SharedPreferences

    private lateinit var webView: WebView


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

        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

        binding.imageButton.setOnClickListener {

            if (sharedPref.getBoolean(STATE, false)) {
                booksViewModel.addBooks(book, "")
                findNavController().navigate(R.id.action_detailsFragment_to_myListFragment2)
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
            binding.webProgressBar.animate().alpha(0f).duration = 1000
            book = it
            binding.autherTextview.text = it.authors
            binding.subtitleTextView.text = it.subtitle
            Picasso.get().load(it.image).into(binding.bookDetailsImageView)

            //call web view function
            showWebView(it.url)
        })


    }



    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    fun showWebView(url: String){
        webView = binding.webView
        // WebViewClient allows you to handle
        webView.webViewClient = WebViewClient()

        // this will load the url of the website
        webView.apply {
            loadUrl(url)
//            // this will enable the javascript settings
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }

    }
}
