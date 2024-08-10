package com.example.astrologermobileapp.introslide

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.fragments.KundaliFragment
import com.example.astrologermobileapp.fragments.KundaliMatchingFragment

class IntroSlideActivity : AppCompatActivity() {


    private lateinit var slides: MutableList<SlideModel>

    private lateinit var viewPager: ViewPager2
    private lateinit var introSliderAdapter: IntroSliderAdapter
    private val kundaliMatchingData = KundaliMatchingData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro_slide)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.intro)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewPager = findViewById(R.id.viewPager)

        val slides = mutableListOf(
            SlideModel(R.drawable.city_skyline, "Boy's Date of Birth", "Girl's Date of Birth"),
            SlideModel(R.drawable.city_skyline, "Boy's Time of Birth", "Girl's Time of Birth"),
            SlideModel(R.drawable.city_skyline, "Boy's Latitude", "Girl's Latitude"),
            SlideModel(R.drawable.city_skyline, "Boy's Longitude", "Girl's Longitude")
        )


        introSliderAdapter = IntroSliderAdapter(slides, kundaliMatchingData)
        viewPager.adapter = introSliderAdapter
        viewPager.isUserInputEnabled = false // Disable swiping

    }

    fun moveToNextSlide() {
        if (viewPager.currentItem < introSliderAdapter.itemCount - 1) {
            viewPager.currentItem += 1
        } else {
            // All slides completed, navigate to KundaliMatchingFragment
            hideViewPager()
            val fragment = KundaliMatchingFragment.newInstance(kundaliMatchingData)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }


    fun hideViewPager() {

        viewPager.visibility = View.GONE

    }




}