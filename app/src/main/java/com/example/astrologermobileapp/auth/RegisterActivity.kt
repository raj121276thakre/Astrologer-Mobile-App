package com.example.astrologermobileapp.auth

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.astrologermobileapp.MainActivity
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.auth.auth_api.RegisterRequest
import com.example.astrologermobileapp.auth.auth_api.RetrofitClient
import com.example.astrologermobileapp.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setGenderSpinner()

        val dobEditText = binding.registrationDob

        // Prevent keyboard from popping up
        dobEditText.isFocusable = false
        dobEditText.isFocusableInTouchMode = false

        // Set OnClickListener to show DatePickerDialog
        dobEditText.setOnClickListener {
            showDatePickerDialog(dobEditText)
        }

        // Profile picture click listener
        binding.registrationChangePhoto.setOnClickListener {
            // Implement profile picture update logic
            openGallery()
        }

        binding.signUpButton.setOnClickListener {
//            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
//            finish()

                registerUser()
        }

    }



    private fun registerUser() {
        val request = RegisterRequest(
            name = binding.registrationFullName.text.toString(),
            email = binding.registrationEmail.text.toString(),
            password = binding.registrationPassword.text.toString(),
            gender = binding.registrationGenderSpinner.selectedItem.toString(),
            dob = binding.registrationDob.text.toString(),
            address = binding.registrationAddress.text.toString(),
            phone = binding.registrationMobileNumber.text.toString()
        )

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.authService.register(request)
                if (response.isSuccessful) {
                    // Save JWT token
                    val token = response.body()?.token
                    saveToken(token)
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
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






















    private fun showDatePickerDialog(dobEditText: EditText) {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = String.format(
                    "%02d/%02d/%04d",
                    selectedDay,
                    selectedMonth + 1,
                    selectedYear
                )

                dobEditText.setText(selectedDate)
            }, year, month, day)
        datePickerDialog.show()


    }


    private fun setGenderSpinner() {
        // Set up the gender spinner
        val genders = arrayOf("Male", "Female", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genders)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.registrationGenderSpinner.adapter = adapter
    }


    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    Glide.with(this).load(uri).into(binding.registrationProfilePic)
                }
            }
        }


}