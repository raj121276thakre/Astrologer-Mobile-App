package com.example.astrologermobileapp.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.activities.ChatScreenActivity
import com.example.astrologermobileapp.adapters.AstrologerAdapter
import com.example.astrologermobileapp.adapters.FeaturesAdapter
import com.example.astrologermobileapp.adapters.ImageSliderAdapter
import com.example.astrologermobileapp.adapters.ServiceAdapter
import com.example.astrologermobileapp.databinding.FragmentHomeBinding
import com.example.astrologermobileapp.introslide.IntroSlideActivity
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

        binding.tvViewAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chatsFragment)
        }

        return binding.root
    }

    private fun setUpServices() {
        val recyclerView: RecyclerView = binding.servicesRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val serviceList = listOf(
            Service("Chat with an Astrologer", R.drawable.ic_chat_img, R.drawable.chat_image),
            Service("Call with an Astrologer", R.drawable.ic_call, R.drawable.call_image),
            Service(
                "Offline Video Consultation",
                R.drawable.ic_videocall,
                R.drawable.consultaion_image
            ),
            Service("Shop at \nAstroMall", R.drawable.ic_shop_cart, R.drawable.shop_image)
        )

        val serviceAdapter = ServiceAdapter(requireContext(), serviceList) { service ->
            navigateToFragment(service)
        }


        recyclerView.adapter = serviceAdapter
    }


    private fun navigateToFragment(service: Service) {
        val actionId =  when (service.name) {
            "Chat with an Astrologer" -> R.id.action_homeFragment_to_chatsFragment
            "Call with an Astrologer" -> R.id.action_homeFragment_to_chatsFragment
            "Offline Video Consultation" -> R.id.action_homeFragment_to_chatsFragment
            "Shop at \nAstroMall" -> R.id.action_homeFragment_to_shoppingFragment
            else -> return
        }

        findNavController().navigate(actionId)
    }

    private fun setupFeaturesRecyclerView() {
        val recyclerView = binding.featuresRecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        featuresAdapter = FeaturesAdapter(getFeatures()){ feature ->

            val actionId =  when (feature.title) {
                "Daily Horoscope" -> R.id.action_homeFragment_to_dailyPanchangFragment
//                "Get Free Kundali" -> R.id.action_homeFragment_to_kundaliFragment
//                "Horoscope Matching" -> R.id.action_homeFragment_to_kundaliMatchingFragment

                "Get Free Kundali", "Horoscope Matching" -> {
                    val intent = Intent(context, IntroSlideActivity::class.java)
                    // Pass the feature title or ID to identify the target fragment
                    intent.putExtra("target_fragment", feature.title)
                    context?.startActivity(intent)
                    return@FeaturesAdapter // Return here to prevent navigating twice
                }

                else -> return@FeaturesAdapter
            }

            findNavController().navigate(actionId)
        }

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
        astrologerAdapter = AstrologerAdapter(astrologerList){
                selectedAstrologer ->
            // Handle item click here
            val intent = Intent(requireContext(), ChatScreenActivity::class.java)
            intent.putExtra("name", selectedAstrologer.name)
            intent.putExtra("image", selectedAstrologer.profileImageUrl)
            startActivity(intent)
        }
        recyclerView.adapter = astrologerAdapter


    }


    private fun setupImageSlider() {

        val images = listOf(
            "https://theacharyamukti.com/image/product/new/panchang.jpg",
            "https://theacharyamukti.com/img/online-puja.jpg",
            "https://www.astroseva24.com/img/upload/images/banner/7525058360245.jpg"


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
        }, 4000, 4000)  // Delay and period in milliseconds
    }


    override fun onDestroyView() {
        super.onDestroyView()
        sliderHandler.removeCallbacksAndMessages(null)
        _binding = null
    }




}