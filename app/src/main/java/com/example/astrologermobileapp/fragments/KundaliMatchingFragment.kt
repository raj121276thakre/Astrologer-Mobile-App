package com.example.astrologermobileapp.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.astrologermobileapp.api.kundaliMatching.KundaliResponse
import com.example.astrologermobileapp.api.kundaliMatching.RetrofitInstance

import com.example.astrologermobileapp.api.pdf.PdfResponse
import com.example.astrologermobileapp.api.pdf.RetrofitInstancePdf
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.databinding.FragmentKundaliMatchingBinding
import com.example.astrologermobileapp.introslide.KundaliMatchingData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class KundaliMatchingFragment : Fragment() {

    private var _binding: FragmentKundaliMatchingBinding? = null
    private val binding get() = _binding!!

    private var pdfUrl: String? = null

    companion object {
        private const val ARG_MATCHING_DATA = "matching_data"

        fun newInstance(matchingData: KundaliMatchingData): KundaliMatchingFragment {
            val fragment = KundaliMatchingFragment()
            val args = Bundle()
            args.putParcelable(ARG_MATCHING_DATA, matchingData)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var matchingData: KundaliMatchingData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKundaliMatchingBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchingData = arguments?.getParcelable(ARG_MATCHING_DATA) ?: KundaliMatchingData()
        displayInputedData(matchingData)

        setupButtons(matchingData)

    }


    private fun setupButtons(matchingData: KundaliMatchingData) {

        getLanguageFromUser()

        binding.matchKundaliBtn.setOnClickListener {
            val apiKey = getString(R.string.vedicastro_apikey)
            // Get selected language from Spinner
            val selectedLang =
                binding.languageSpinner.tag?.toString() ?: "en"  // default to "en" if not set

            fetchKundaliMatchingDetails(
                boyDob = matchingData.boyDob,
                boyTob = matchingData.boyTob,
                boyTz = matchingData.boyTz,
                boyLat = matchingData.boyLat,
                boyLon = matchingData.boyLon,

                girlDob = matchingData.girlDob,
                girlTob = matchingData.girlTob,
                girlTz = matchingData.girlTz,
                girlLat = matchingData.girlLat,
                girlLon = matchingData.girlLon,
                apiKey = apiKey,
                lang = selectedLang
            )

        }

        binding.getPdfbtn.setOnClickListener {
            val apiKey = getString(R.string.vedicastro_apikey)
            // Get selected language from Spinner
            val selectedLang =
                binding.languageSpinner.tag?.toString() ?: "en"  // default to "en" if not set

            getPdfUrl(
                boyDob = matchingData.boyDob,
                boyTob = matchingData.boyTob,
                boyTz = matchingData.boyTz,
                boyLat = matchingData.boyLat,
                boyLon = matchingData.boyLon,

                girlDob = matchingData.girlDob,
                girlTob = matchingData.girlTob,
                girlTz = matchingData.girlTz,
                girlLat = matchingData.girlLat,
                girlLon = matchingData.girlLon,
                apiKey = apiKey,
                lang = selectedLang,

                boyPob = matchingData.boyPob, //......
                girlPob = matchingData.girlPob, //......
                boyName = matchingData.boyName,
                girlName = matchingData.girlName
            )

        }

    }


    //     match and fetch kundali details from api
    private fun fetchKundaliMatchingDetails(
        boyDob: String, boyTob: String, boyTz: Double, boyLat: Double, boyLon: Double,
        girlDob: String, girlTob: String, girlTz: Double, girlLat: Double, girlLon: Double,
        apiKey: String, lang: String
    ) {

        val api = RetrofitInstance.api
        val call = api.getMatchingDetails(
            boyDob = boyDob,
            boyTob = boyTob,
            boyTz = boyTz,
            boyLat = boyLat,
            boyLon = boyLon,
            girlDob = girlDob,
            girlTob = girlTob,
            girlTz = girlTz,
            girlLat = girlLat,
            girlLon = girlLon,
            apiKey = apiKey,
            lang = lang  // Pass selected language to API call
        )

        Log.d("API_DEBUG", "Boy DOB: $boyDob, Boy TOB: $boyTob")
        Log.d("API_DEBUG", "Girl DOB: $girlDob, Girl TOB: $girlTob")

        call.enqueue(object : Callback<KundaliResponse> {
            override fun onResponse(
                call: Call<KundaliResponse>,
                response: Response<KundaliResponse>
            ) {
                if (response.isSuccessful) {
                    val kundaliResponse = response.body()
                    if (kundaliResponse != null && kundaliResponse.response != null) {
                        val botResponse = kundaliResponse.response.bot_response
                        requireActivity().runOnUiThread {
                            binding.responseText.text = botResponse
                            //  binding.getPdfbtn.visibility = View.VISIBLE // Show the button
                        }
                    } else {
                        requireActivity().runOnUiThread {
                            binding.responseText.text = "Empty response"
                        }
                    }
                } else {
                    requireActivity().runOnUiThread {
                        binding.responseText.text = "Error: ${response.message()}"
                    }
                }
            }

            override fun onFailure(call: Call<KundaliResponse>, t: Throwable) {
                requireActivity().runOnUiThread {
                    binding.responseText.text = "Failure: ${t.message}"
                }
            }
        })


    }


    private fun getPdfUrl(
        boyDob: String,
        boyTob: String,
        boyTz: Double,
        boyLat: Double,
        boyLon: Double,
        girlDob: String,
        girlTob: String,
        girlTz: Double,
        girlLat: Double,
        girlLon: Double,
        apiKey: String,
        lang: String,
        boyPob: String,
        girlPob: String,
        boyName: String,
        girlName: String
    ) {


        val call = RetrofitInstancePdf.api.getPdfUrl(
            boyDob, boyTob, boyTz, boyLat, boyLon,
            girlDob, girlTob, girlTz, girlLat, girlLon,
            apiKey, lang, boyPob, girlPob, boyName, girlName
        )

        call.enqueue(object : Callback<PdfResponse> {
            override fun onResponse(call: Call<PdfResponse>, response: Response<PdfResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    pdfUrl = response.body()!!.response
                    Log.d("PDF_URL", "PDF URL: $pdfUrl")
                    Toast.makeText(requireContext(), pdfUrl, Toast.LENGTH_LONG).show()
                    gotoBrowser(requireContext(), pdfUrl.toString())

                } else {
                    Log.e("PDF_URL", "Failed to get PDF URL: ${response.errorBody()?.string()}")
                    Toast.makeText(requireContext(), "Failed to get PDF URL", Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<PdfResponse>, t: Throwable) {
                Log.e("PDF_URL", "Error: ${t.message}")
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG)
                    .show()
            }
        })


    }

    private fun gotoBrowser(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
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


    private fun displayInputedData(matchingData: KundaliMatchingData) {

        // Set the data to the TextViews
        binding.tvBoyName.text = "Boy's Name: \n${matchingData.boyName}"
        binding.tvBoyDob.text = "Boy's Date of Birth: \n${matchingData.boyDob}"
        binding.tvBoyTob.text = "Boy's Time of Birth: \n${matchingData.boyTob}"
        binding.tvBoyLat.text = "Boy's Latitude: \n${matchingData.boyLat}"
        binding.tvBoyLon.text = "Boy's Longitude: \n${matchingData.boyLon}"
        binding.tvBoyTz.text = "Boy's Time Zone: \n${matchingData.boyTz}"

        binding.tvGirlName.text = "Girl's Name: \n${matchingData.girlName}"
        binding.tvGirlDob.text = "Girl's Date of Birth: \n${matchingData.girlDob}"
        binding.tvGirlTob.text = "Girl's Time of Birth: \n${matchingData.girlTob}"
        binding.tvGirlLat.text = "Girl's Latitude: \n${matchingData.girlLat}"
        binding.tvGirlLon.text = "Girl's Longitude: \n${matchingData.girlLon}"
        binding.tvGirlTz.text = "Girl's Time Zone: \n${matchingData.girlTz}"


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}