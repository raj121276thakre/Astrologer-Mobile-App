package com.example.astrologermobileapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.astrologermobileapp.databinding.FragmentKundaliBinding
import com.example.astrologermobileapp.introslide.KundaliData


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

        //...............................
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