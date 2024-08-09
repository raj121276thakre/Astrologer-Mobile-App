package com.example.astrologermobileapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.astrologermobileapp.MainActivity
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.chats.ChatAdapter
import com.example.astrologermobileapp.chats.ChatMessage

import com.example.astrologermobileapp.databinding.ActivityChatScreenBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatScreenBinding

    private val chatList = mutableListOf<ChatMessage>()
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var chatStartTime: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityChatScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chatScreen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupAstroDetails()

        showChatTime()

        chatAdapter = ChatAdapter(chatList)
        binding.recyclerViewChat.adapter = chatAdapter
        binding.recyclerViewChat.layoutManager = LinearLayoutManager(this)

        binding.buttonSend.setOnClickListener {
            sendMessage()
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }


        binding.buttonEnd.setOnClickListener {
            val chatTime = calculateChatDuration()
            Toast.makeText(this,"Chat Time is $chatTime minutes.",Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun showChatTime() {

        supportActionBar?.setDisplayShowTitleEnabled(false) // Disable the default title display

        // Get the current time and set it as chat start time
        chatStartTime = Date()
        val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val chatStartTimeString = dateFormat.format(chatStartTime)
        binding.toolbarChatStartTime.text = chatStartTimeString
    }

    private fun calculateChatDuration(): Long {
        val currentTime = Date()
        val durationInMillis = currentTime.time - chatStartTime.time
        val durationInMinutes = durationInMillis / (1000 * 60)
        return durationInMinutes
    }


    private fun sendMessage() {
        val messageText = binding.editTextMessage.text.toString().trim()
        if (messageText.isNotEmpty()) {
            val timestamp = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
            val chatMessage = ChatMessage(messageText, true, timestamp)
            chatList.add(chatMessage)
            chatAdapter.notifyItemInserted(chatList.size - 1)
            binding.recyclerViewChat.scrollToPosition(chatList.size - 1)
            binding.editTextMessage.text.clear()
        }
    }


    private fun setupAstroDetails() {
        val name = intent.getStringExtra("name")
//        val description = intent.getStringExtra("description")
//        val languages = intent.getStringExtra("languages")
//        val experience = intent.getStringExtra("experience")
        val image = intent.getStringExtra("image")

        binding.txtUserName.text = name
        // binding.detailDescriptionTextView.text = description
        Glide.with(binding.imgUserProfile.context)
            .load(image)
            .into(binding.imgUserProfile)

    }


}