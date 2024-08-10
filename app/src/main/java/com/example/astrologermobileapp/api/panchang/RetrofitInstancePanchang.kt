package com.example.astrologermobileapp.api.panchang

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstancePanchang {
    private const val BASE_URL = "https://api.vedicastroapi.com/v3-json/"

    val api: PanchangApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PanchangApi::class.java)
    }
}