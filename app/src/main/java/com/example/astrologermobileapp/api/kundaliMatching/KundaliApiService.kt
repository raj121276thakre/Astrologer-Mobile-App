package com.example.astroappproject.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface KundaliApiService {
    @GET("v3-json/matching/ashtakoot")
    fun getMatchingDetails(
        @Query("boy_dob") boyDob: String,
        @Query("boy_tob") boyTob: String,
        @Query("boy_tz") boyTz: Double,
        @Query("boy_lat") boyLat: Double,
        @Query("boy_lon") boyLon: Double,

        @Query("girl_dob") girlDob: String,
        @Query("girl_tob") girlTob: String,
        @Query("girl_tz") girlTz: Double,
        @Query("girl_lat") girlLat: Double,
        @Query("girl_lon") girlLon: Double,

        @Query("api_key") apiKey: String,
        @Query("lang") lang: String      // = "en"
    ): Call<KundaliResponse>
}
