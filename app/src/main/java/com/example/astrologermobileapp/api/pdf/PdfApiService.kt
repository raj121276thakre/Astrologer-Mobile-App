package com.example.astrologermobileapp.api.pdf

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PdfApiService {
    @GET("v3-json/pdf/matching")
    fun getPdfUrl(
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
        @Query("lang") lang: String,
        @Query("boy_pob") boyPob: String,
        @Query("girl_pob") girlPob: String,
        @Query("boy_name") boyName: String,
        @Query("girl_name") girlName: String,
        @Query("style") style: String = "north",
        @Query("color") color: Int = 140
    ): Call<PdfResponse>
}

data class PdfResponse(
    val status: Int,
    val response: String
)
