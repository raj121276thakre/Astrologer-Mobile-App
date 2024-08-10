package com.example.astrologermobileapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.introslide.KundaliData
import com.example.astrologermobileapp.introslide.SlideModel


class KundaliFragment : Fragment() {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kundali, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kundaliData = arguments?.getParcelable(ARG_KUNDALI_DATA) ?: KundaliData()

        // Use the data in your views
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvDob: TextView = view.findViewById(R.id.tvDob)
        val tvTob: TextView = view.findViewById(R.id.tvTob)
        val tvLat: TextView = view.findViewById(R.id.tvLat)
        val tvLon: TextView = view.findViewById(R.id.tvLon)
        val tvTz: TextView = view.findViewById(R.id.tvTz)

        tvName.text = "Boy's Name: ${kundaliData.name}"
        tvDob.text = "Date of Birth: ${kundaliData.dob}"
        tvTob.text = "Time of Birth: ${kundaliData.tob}"
        tvLat.text = "Latitude: ${kundaliData.lat}"
        tvLon.text = "Longitude: ${kundaliData.lon}"
        tvTz.text = "Time Zone: ${kundaliData.tz}"
    }


}