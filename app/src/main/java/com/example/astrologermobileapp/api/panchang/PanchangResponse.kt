package com.example.astrologermobileapp.api.panchang

import com.example.astrologermobileapp.api.panchang.PanchangData

data class PanchangResponse(
    val status: Int,
    val response: PanchangData
)