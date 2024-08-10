package com.example.astrologermobileapp.api.panchang

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PanchangApi {
    @GET("panchang/panchang")
    fun getPanchang(
        @Query("api_key") apiKey: String,
        @Query("date") date: String,
        @Query("tz") tz: Double,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("time") time: String,
        @Query("lang") lang: String
    ): Call<PanchangResponse>
}


