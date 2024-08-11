package com.example.astrologermobileapp.api.kundali

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonsKundaliApiService {
    @GET("extended-horoscope/extended-kundli-details")
    fun getKundaliDetails(
        @Query("dob") dob: String,
        @Query("tob") tob: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("tz") tz: Double = 5.5,
        @Query("api_key") apiKey: String,
        @Query("lang") lang: String = "en"
    ): Call<PersonKundaliResponse>
}