package com.example.astrologermobileapp.api.panchang

data class PanchangData(
    val day: Day,
    val tithi: Tithi,
    val nakshatra: Nakshatra,
    val karana: Karana,
    val yoga: Yoga,
    val advanced_details: AdvancedDetails,
    val rahukaal: String,
    val gulika: String,
    val yamakanta: String,
    val date: String
)

data class Day(val name: String)
data class Tithi(
    val name: String,
    val number: Int,
    val type: String,
    val diety: String,
    val start: String,
    val end: String,
    val special: String
)

data class Nakshatra(
    val name: String,
    val number: Int,
    val lord: String,
    val diety: String,
    val start: String,
    val end: String,
    val special: String
)

data class Karana(
    val name: String,
    val number: Int,
    val type: String,
    val lord: String,
    val diety: String,
    val start: String,
    val end: String,
    val special: String
)

data class Yoga(val name: String, val number: Int, val start: String, val end: String)
data class AdvancedDetails(
    val sun_rise: String,
    val sun_set: String,
    val moon_rise: String,
    val moon_set: String,
    val masa: Masa,
    val moon_yogini_nivas: String,
    val ahargana: Double,
    val years: Years,
    val next_full_moon: String,
    val next_new_moon: String,
    val vaara: String,
    val disha_shool: String,
    val abhijit_muhurta: AbhijitMuhurta
)

data class Masa(
    val amanta_number: Int,
    val amanta_name: String,
    val adhik_maasa: Boolean,
    val ayana: String,
    val purnimanta_number: Int,
    val purnimanta_name: String,
    val moon_phase: String,
    val paksha: String,
    val ritu: String,
    val ritu_tamil: String
)

data class Years(
    val kali: Int,
    val saka: Int,
    val vikram_samvaat: Int,
    val kali_samvaat_number: Int,
    val kali_samvaat_name: String,
    val vikram_samvaat_number: Int,
    val vikram_samvaat_name: String,
    val saka_samvaat_number: Int,
    val saka_samvaat_name: String
)

data class AbhijitMuhurta(val start: String, val end: String)