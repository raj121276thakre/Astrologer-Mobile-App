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

    private lateinit var viewPager: ViewPager2
    private lateinit var introSliderAdapter: IntroSliderAdapter
    private lateinit var slides: MutableList<SlideModel>
    private var targetFragment: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro_slide)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.intro)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        targetFragment = intent.getStringExtra("target_fragment")
        viewPager = findViewById(R.id.viewPager)

        slides = mutableListOf(
           // SlideModel(R.drawable.city_skyline, "Do you want to know your future?"),
            SlideModel(R.drawable.city_skyline, "What's your name?"),
            SlideModel(R.drawable.city_skyline, "Select your gender"),
//            SlideModel(R.drawable.city_skyline, "Select your sentimental status"),
            SlideModel(R.drawable.city_skyline, "Select your date of birth"),
            SlideModel(R.drawable.city_skyline, "Select your time of birth"),
            SlideModel(R.drawable.city_skyline, "What's your place of birth?"),
            SlideModel(R.drawable.city_skyline, "Your report is ready!")
        )

        introSliderAdapter = IntroSliderAdapter(slides)
        viewPager.adapter = introSliderAdapter
        viewPager.isUserInputEnabled = false // Disable swiping

    }

    fun moveToNextSlide() {
        if (viewPager.currentItem < slides.size - 1) {
            viewPager.currentItem += 1
        } else {
            // All slides completed, navigate to the target fragment
            navigateToTargetFragment()
        }
    }


    fun hideViewPager() {

        viewPager.visibility = View.GONE

    }


    private fun navigateToTargetFragment() {
        val fragment = when (targetFragment) {
            "Get Free Kundali" -> KundaliFragment.newInstance(slides)
            "Horoscope Matching" -> KundaliMatchingFragment.newInstance(slides)
            else -> null
        }

        fragment?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, it)
                .commit()
        }
    }

}