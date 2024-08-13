package com.example.astrologermobileapp.auth.auth_api

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val gender: String,
    val dob: String,
    val address: String,
    val phone: String
)


