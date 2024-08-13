package com.example.astrologermobileapp.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.astrologermobileapp.MainActivity
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.auth.auth_api.LoginRequest
import com.example.astrologermobileapp.auth.auth_api.RetrofitClient
import com.example.astrologermobileapp.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.loginButton.setOnClickListener {
//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//            finish()
            loginUser()

        }

        binding.loginSignupPrompt.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))


        }

        binding.loginHelp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))


        }

    }



    private fun loginUser() {
        val request = LoginRequest(
            email = binding.loginEmail.text.toString(),
            password = binding.loginPassword.text.toString()
        )

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.authService.login(request)
                if (response.isSuccessful) {
                    // Save JWT token
                    val token = response.body()?.token
                    saveToken(token)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    // Handle error
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

    private fun saveToken(token: String?) {
        val sharedPreferences = getSharedPreferences("auth", MODE_PRIVATE)
        sharedPreferences.edit().putString("jwt_token", token).apply()
    }



}












