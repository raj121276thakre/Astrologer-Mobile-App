package com.example.astrologermobileapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.fragments.KundaliFragment.Companion
import com.example.astrologermobileapp.introslide.KundaliMatchingData
import com.example.astrologermobileapp.introslide.SlideModel


class KundaliMatchingFragment : Fragment() {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kundali_matching, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchingData = arguments?.getParcelable(ARG_MATCHING_DATA) ?: KundaliMatchingData()

        // Find the views by their IDs
        val tvBoyDob: TextView = view.findViewById(R.id.tvBoyDob)
        val tvBoyTob: TextView = view.findViewById(R.id.tvBoyTob)
        val tvBoyLat: TextView = view.findViewById(R.id.tvBoyLat)
        val tvBoyLon: TextView = view.findViewById(R.id.tvBoyLon)
        val tvBoyTz: TextView = view.findViewById(R.id.tvBoyTz)
        val tvGirlDob: TextView = view.findViewById(R.id.tvGirlDob)
        val tvGirlTob: TextView = view.findViewById(R.id.tvGirlTob)
        val tvGirlLat: TextView = view.findViewById(R.id.tvGirlLat)
        val tvGirlLon: TextView = view.findViewById(R.id.tvGirlLon)
        val tvGirlTz: TextView = view.findViewById(R.id.tvGirlTz)

        // Set the data to the TextViews
        tvBoyDob.text = "Boy's Date of Birth: ${matchingData.boyDob}"
        tvBoyTob.text = "Boy's Time of Birth: ${matchingData.boyTob}"
        tvBoyLat.text = "Boy's Latitude: ${matchingData.boyLat}"
        tvBoyLon.text = "Boy's Longitude: ${matchingData.boyLon}"
        tvBoyTz.text = "Boy's Time Zone: ${matchingData.boyTz}"
        tvGirlDob.text = "Girl's Date of Birth: ${matchingData.girlDob}"
        tvGirlTob.text = "Girl's Time of Birth: ${matchingData.girlTob}"
        tvGirlLat.text = "Girl's Latitude: ${matchingData.girlLat}"
        tvGirlLon.text = "Girl's Longitude: ${matchingData.girlLon}"
        tvGirlTz.text = "Girl's Time Zone: ${matchingData.girlTz}"
    }


}