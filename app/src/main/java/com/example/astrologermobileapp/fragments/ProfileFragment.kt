package com.example.astrologermobileapp.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setGenderSpinner()

        // Profile picture click listener
        binding.changePhotoButton.setOnClickListener {
            // Implement profile picture update logic
            openGallery()
        }


        val dobEditText = binding.dateOfBirth

        // Prevent keyboard from popping up
        dobEditText.isFocusable = false
        dobEditText.isFocusableInTouchMode = false

        // Set OnClickListener to show DatePickerDialog
        dobEditText.setOnClickListener {
            showDatePickerDialog(dobEditText)
        }

    }

    private fun showDatePickerDialog(dobEditText: EditText) {

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
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
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genders)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGender.adapter = adapter
    }


    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    Glide.with(this).load(uri).into(binding.profilePic)
                }
            }
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}