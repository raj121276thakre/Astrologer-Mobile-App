package com.example.astrologermobileapp.api.kundali

data class PersonKundaliResponse(
    val status: Int,
    val response: PersonKundaliDetails
)

data class PersonKundaliDetails(
    val gana: String,
    val yoni: String,
    val vasya: String,
    val nadi: String,
    val varna: String,
    val paya: String,
    val tatva: String,
    val life_stone: String,
    val lucky_stone: String,
    val fortune_stone: String,
    val name_start: String,
    val ascendant_sign: String,
    val ascendant_nakshatra: String,
    val rasi: String,
    val rasi_lord: String,
    val nakshatra: String,
    val nakshatra_lord: String,
    val nakshatra_pada: Int,
    val sun_sign: String,
    val karana: String,
    val yoga: String
)