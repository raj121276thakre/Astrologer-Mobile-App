package com.example.astrologermobileapp.chats

data class ChatMessage(
    val message: String,
    val isSentByUser: Boolean,
    val timestamp: String
)
