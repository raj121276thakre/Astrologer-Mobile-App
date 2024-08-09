package com.example.astrologermobileapp.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.adapters.AstrologerAdapter
import com.example.astrologermobileapp.adapters.FeaturesAdapter
import com.example.astrologermobileapp.adapters.ImageSliderAdapter
import com.example.astrologermobileapp.adapters.ServiceAdapter
import com.example.astrologermobileapp.databinding.FragmentHomeBinding
import com.example.astrologermobileapp.models.Astrologer
import com.example.astrologermobileapp.models.Feature
import com.example.astrologermobileapp.models.Service
import java.util.Timer
import java.util.TimerTask


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private val sliderHandler = Handler(Looper.getMainLooper())

    private lateinit var featuresAdapter: FeaturesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var astrologerAdapter: AstrologerAdapter
    private lateinit var astrologerList: List<Astrologer>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewPager = binding.viewPagerImageSlider

        setupImageSlider()
        setupFeaturesRecyclerView()

        // Initialize Astrologers
        setupAstrologers()
        setUpServices()

        return binding.root
    }

    private fun setUpServices() {
        val recyclerView: RecyclerView = binding.servicesRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val serviceList = listOf(
            Service("Chat with an Astrologer", R.drawable.ic_chat_img, R.drawable.chat_image),
            Service("Call with an Astrologer", R.drawable.ic_call, R.drawable.call_image),
            Service("Offline Video Consultation", R.drawable.ic_videocall, R.drawable.consultaion_image),
            Service("Shop at \nAstroMall", R.drawable.ic_shop_cart, R.drawable.shop_image)
        )

        val serviceAdapter = ServiceAdapter(requireContext(), serviceList)
        recyclerView.adapter = serviceAdapter
    }


    private fun setupFeaturesRecyclerView() {
        val recyclerView = binding.featuresRecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        featuresAdapter = FeaturesAdapter(getFeatures())
        recyclerView.adapter = featuresAdapter
    }

    private fun getFeatures(): List<Feature> {
        // Populate with dummy data or fetch from a server
        return listOf(
            Feature("Daily Horoscope", R.drawable.ic_panchang),
            Feature("Get Free Kundali", R.drawable.ic_kundali),
            Feature("Horoscope Matching", R.drawable.ic_matching)
        )
    }


    private fun setupAstrologers() {

        // Initialize RecyclerView
        recyclerView = binding.rvAstrologers
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Initialize your astrologers list here
        astrologerList = listOf(
            Astrologer(
                "Madhav",
                "Vedic, Tarot",
                "https://cdn.astrochakra.com/profile-photos/YyFe8hB0d5tKhUptQzjU3AVx3HZqARlaPTwVg3xY.jpg"
            ),
            Astrologer(
                "Vinod",
                "Vedic, Tarot",
                "https://cdn.astrochakra.com/profile-photos/llWQi35RDqSeiRgxA1NXtTvOsxz6dSd531Kd0e9c.jpg"
            ),
            Astrologer(
                "Deepak",
                "Vedic, Tarot",
                "https://cdn.astrochakra.com/profile-photos/Hepxw3B3s3QhY3l5n7qGfSwUGhNI2stebrwK3My6.jpg"
            ),
            Astrologer(
                "Namita",
                "Vedic, Tarot",
                "https://cdn.astrochakra.com/profile-photos/YyFe8hB0d5tKhUptQzjU3AVx3HZqARlaPTwVg3xY.jpg"
            )
        )

        // Set up the adapter with the astrologers list
        astrologerAdapter = AstrologerAdapter(astrologerList)
        recyclerView.adapter = astrologerAdapter



    }


    private fun setupImageSlider() {

        val images = listOf(
            "https://cdn.create.vista.com/downloads/f0c8b00e-29c2-481f-9b86-76525f19e2ac_1024.jpeg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpbhSXVKL4omvZLNHW40u9B-Hw5QKGxvW24T7HpPHADldv3KGZmNavBV0_zA&s",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzmCD5WOsF1QLGHR7eO9DUkXIVHskRkBn6gQ&s",

            )

        imageSliderAdapter = ImageSliderAdapter(images)
        viewPager.adapter = imageSliderAdapter

        setupAutoSlide()

    }

    private fun setupAutoSlide() {
        val slideRunnable = Runnable {
            if (viewPager.currentItem < imageSliderAdapter.itemCount - 1) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
                viewPager.currentItem = 0
            }
        }

        // Schedule a task to run every 3 seconds (3000 milliseconds)
        val slideTimer = Timer()
        slideTimer.schedule(object : TimerTask() {
            override fun run() {
                sliderHandler.post(slideRunnable)
            }
        }, 3000, 3000)  // Delay and period in milliseconds
    }


    override fun onDestroyView() {
        super.onDestroyView()
        sliderHandler.removeCallbacksAndMessages(null)
        _binding = null
    }

}