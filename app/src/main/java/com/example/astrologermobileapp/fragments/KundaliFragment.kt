package com.example.astrologermobileapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.api.kundali.PersonKundaliResponse
import com.example.astrologermobileapp.api.kundali.PersonKundaliRetrofitInstance
import com.example.astrologermobileapp.databinding.FragmentKundaliBinding
import com.example.astrologermobileapp.introslide.KundaliData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class KundaliFragment : Fragment() {

    private var _binding: FragmentKundaliBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_KUNDALI_DATA = "kundali_data"

        fun newInstance(kundaliData: KundaliData): KundaliFragment {
            val fragment = KundaliFragment()
            val args = Bundle()
            args.putParcelable(ARG_KUNDALI_DATA, kundaliData)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var kundaliData: KundaliData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKundaliBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kundaliData = arguments?.getParcelable(ARG_KUNDALI_DATA) ?: KundaliData()
        displayInputedData(kundaliData)

        setupButtons(kundaliData)

    }

    private fun setupButtons(kundaliData: KundaliData) {
        getLanguageFromUser()

        binding.buttonGetKundali.setOnClickListener {
            val api_Key = getString(R.string.vedicastro_apikey)
            // Get selected language from Spinner
            val selectedLang =
                binding.languageSpinner.tag?.toString() ?: "en"  // default to "en" if not set

            fetchKundaliDetails(api_Key,selectedLang)
        }
    }





    private fun fetchKundaliDetails(api_Key: String, selectedLang: String) {
        // Replace with actual parameters
        val dob = "21/04/2021"
        val tob = "11:40"
        val lat = 11.0
        val lon = 77.0
        val tz = 5.5
        val apiKey = api_Key
        val lang = selectedLang

        val call = PersonKundaliRetrofitInstance.api.getKundaliDetails(dob, tob, lat, lon, tz, apiKey,lang)

        call.enqueue(object : Callback<PersonKundaliResponse> {
            override fun onResponse(call: Call<PersonKundaliResponse>, response: Response<PersonKundaliResponse>) {
                if (response.isSuccessful) {
                    val kundaliDetails = response.body()
                    // Handle the response here, for example, display data in the UI
                    Toast.makeText(requireContext(), "Kundali fetched successfully!", Toast.LENGTH_SHORT).show()
                    Log.d("KundaliDetails", kundaliDetails.toString())

                    // Example: Update the UI with received Kundali details
                    binding.responseText.text = kundaliDetails?.status.toString()
                    binding.responseText1.text = kundaliDetails?.response?.gana

                    // Add other fields as needed

                } else {
                    // Handle the case where the API call is not successful
                    Toast.makeText(requireContext(), "Failed to fetch Kundali", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PersonKundaliResponse>, t: Throwable) {
                // Handle the error
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("KundaliError", t.message.toString())
            }
        })
    }






    //    return language
    private fun getLanguageFromUser() {
        val languages = resources.getStringArray(R.array.language_array)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_lamguge_item, languages)
        binding.languageSpinner.setAdapter(arrayAdapter)

        binding.languageSpinner.setOnItemClickListener { parent, view, position, id ->
            val selectedLanguage = parent.getItemAtPosition(position).toString()
            Log.d("SelectedLanguage", selectedLanguage)
            // Save the selected language to use later
            binding.languageSpinner.tag = selectedLanguage
        }
    }

    private fun displayInputedData(kundaliData: KundaliData) {

        // Set the data to the TextViews
        binding.tvName.text = "Boy's Name: ${this.kundaliData.name}"
        binding.tvDob.text = "Date of Birth: ${this.kundaliData.dob}"
        binding.tvTob.text = "Time of Birth: ${this.kundaliData.tob}"
        binding.tvLat.text = "Latitude: ${this.kundaliData.lat}"
        binding.tvLon.text = "Longitude: ${this.kundaliData.lon}"
        binding.tvTz.text = "Time Zone: ${this.kundaliData.tz}"

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}