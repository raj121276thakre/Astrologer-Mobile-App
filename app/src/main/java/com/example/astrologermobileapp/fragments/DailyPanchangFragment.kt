package com.example.astrologermobileapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.astrologermobileapp.api.panchang.PanchangData
import com.example.astrologermobileapp.api.panchang.PanchangResponse
import com.example.astrologermobileapp.api.panchang.RetrofitInstancePanchang
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.databinding.FragmentDailyPanchangBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class DailyPanchangFragment : Fragment() {

    private var _binding: FragmentDailyPanchangBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDailyPanchangBinding.inflate(inflater, container, false)


        fetchPanchangData()

        return binding.root
    }


    private fun fetchPanchangData() {
        val apiKey = getString(R.string.vedicastro_apikey)
        // Get the current date
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date = currentDate.format(formatter)
        val tz = 5.5
        val lat = 18.2781
        val lon = 74.1612
        val time = "05:20"
        val lang = "en"

        RetrofitInstancePanchang.api.getPanchang(apiKey, date, tz, lat, lon, time, lang)
            .enqueue(object : Callback<PanchangResponse> {
                override fun onResponse(
                    call: Call<PanchangResponse>,
                    response: Response<PanchangResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { displayPanchangData(it.response,date) }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Failed to fetch data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<PanchangResponse>, t: Throwable) {
                    Toast.makeText(
                        requireContext(),
                        "Failed to fetch data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun displayPanchangData(panchangData: PanchangData, date: String) {

        // Set text from response
        binding.textViewDay.text = "Day: ${panchangData.day.name}"
        binding.textViewDate.text = "Date: ${date}"
        binding.textViewTithi.text = "Tithi: ${panchangData.tithi.name}"
        binding.textViewNakshatra.text = "Nakshatra: ${panchangData.nakshatra.name}"
        binding.textViewKarana.text = "Karana: ${panchangData.karana.name}"
        binding.textViewYoga.text = "Yoga: ${panchangData.yoga.name}"

        binding.textViewAyanamsa.text = "Ayanamsa: ${panchangData.advanced_details.vaara}"
        binding.textViewRasi.text = "Rasi: Aquarius"

        binding.textViewYears.text = "Kali: ${panchangData.advanced_details.years.kali}, Saka: ${panchangData.advanced_details.years.saka}, Vikram Samvaat: ${panchangData.advanced_details.years.vikram_samvaat}"

        binding.textViewRahukaal.text = "Rahukaal: ${panchangData.rahukaal}"
        binding.textViewGulika.text = "Gulika: ${panchangData.gulika}"
        binding.textViewYamakanta.text = "Yamakanta: ${panchangData.yamakanta}"
    }



    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


}