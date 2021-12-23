package com.example.booksapp.util

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RegisterValidationsTest {
    private lateinit var validator: RegisterValidations

    @Before
    fun setup() {
        validator = RegisterValidations()
    }

    @Test
    fun emailIsValidWithInvalidEmailThenReturnFalseValue() {
        val validation = validator.emailIsValid("test-ii.com")

        assertEquals(false, validation)

    }

    @Test
    fun emailIsValidWithValidEmailThenReturnFalseValue() {
        val validation = validator.emailIsValid("test@test.com")

        assertEquals(true, validation)
    }

    @Test
    fun passwordIsValidWithInvalidEmailThenReturnFalseValue() {
        val validation = validator.passwordIsValid("37")
        assertEquals(false, validation)
    }

    @Test
    fun passwordIsValidWithInvalidEmailThenReturnTrueValue() {
        val validation = validator.passwordIsValid("Tu@21856")
        assertEquals(true, validation)
    }

}