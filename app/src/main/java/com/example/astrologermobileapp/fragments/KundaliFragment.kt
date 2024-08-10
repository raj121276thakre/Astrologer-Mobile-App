package com.example.astrologermobileapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.introslide.SlideModel


class KundaliFragment : Fragment() {

    companion object {
        private const val ARG_SLIDES = "slides"

        fun newInstance(slides: List<SlideModel>): KundaliFragment {
            val fragment = KundaliFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_SLIDES, ArrayList(slides))
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var slides: List<SlideModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kundali, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        slides = arguments?.getParcelableArrayList(ARG_SLIDES) ?: listOf()

        // Use the slides data
        // For example, you could log the data:
        slides.forEach {
            Log.d("KundaliFragmentQ1", "Slide Title: ${it.title1}")
            Log.d("KundaliFragmentQ2", "Slide Title: ${it.title2}")
        }
    }


}