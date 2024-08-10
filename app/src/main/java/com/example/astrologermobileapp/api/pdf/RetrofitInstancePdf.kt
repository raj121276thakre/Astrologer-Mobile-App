package com.example.astroappproject.api.pdf

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstancePdf {
    private const val BASE_URL = "https://api.vedicastroapi.com/"

    val api: PdfApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PdfApiService::class.java)
    }
}
