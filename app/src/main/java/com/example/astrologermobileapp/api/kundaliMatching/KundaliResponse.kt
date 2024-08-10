package com.example.astroappproject.api


data class KundaliResponse(
    val status: Int,
    val response: MatchingResponse
)

data class MatchingResponse(
    val tara: Detail,
    val gana: Detail,
    val yoni: Detail,
    val bhakoot: Detail,
    val grahamaitri: Detail,
    val vasya: Detail,
    val nadi: Detail,
    val varna: Detail,
    val score: Double,
    val bot_response: String
)

data class Detail(
    val boy_tara: String,
    val girl_tara: String,
    val tara: Double,
    val description: String,
    val full_score: Int
)


//data class KundaliResponse(
//    val status: Int,
//    val response: MatchingResponse
//)
//
//data class MatchingResponse(
//    val tara: Detail,
//    val gana: Detail,
//    val yoni: Detail,
//    val bhakoot: Detail,
//    val grahamaitri: Detail,
//    val vasya: Detail,
//    val nadi: Detail,
//    val varna: Detail,
//    val score: Int,
//    val bot_response: String
//)
//
//data class Detail(
//    val boy_tara: String,
//    val girl_tara: String,
//    val tara: Int,
//    val description: String,
//    val full_score: Int
//)
