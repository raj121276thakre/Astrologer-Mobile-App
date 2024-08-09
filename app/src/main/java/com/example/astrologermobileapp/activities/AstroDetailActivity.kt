package com.example.astrologermobileapp.activities

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.databinding.ActivityAstroDetailBinding

class AstroDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAstroDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAstroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.details)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupAstroDetails()



    }

    private fun setupAstroDetails() {
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val languages = intent.getStringExtra("languages")
        val experience = intent.getStringExtra("experience")
        val image = intent.getStringExtra("image")

        binding.nameTextView.text = name
        binding.detailDescriptionTextView.text = description
        Glide.with(binding.imageView.context)
            .load(image)
            .into(binding.imageView)
    }






}