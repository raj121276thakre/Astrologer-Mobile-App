package com.example.astrologermobileapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.astrologermobileapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbarTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        setupBottomNavigation()


    }

    private fun setupBottomNavigation() {
        // Initialize the NavHostFragment and NavController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        // Set up a listener to change the title based on the destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            title = when (destination.id) {
                R.id.chatsFragment -> "Chat"
                R.id.profileFragment -> "Profile"
                R.id.homeFragment -> "Home"
                R.id.shoppingFragment -> "Shop"
                else -> "Astrovastutalk"
            }
            Log.d("MainActivity", "Navigated to ${destination.label}")
        }

        // Handle BottomNavigationView item selection
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.nav_chat -> {
                    navController.navigate(R.id.chatsFragment)
                    true
                }

                R.id.nav_shop -> {
                    navController.navigate(R.id.shoppingFragment)
                    true
                }

//                R.id.nav_wallet -> {
//                    navController.navigate(R.id.profileFragment)
//                    true
//                }

                R.id.nav_profile -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }

                else -> false
            }
        }

        changeToolbarTitle(navController)
    }


    private fun changeToolbarTitle(navController: NavController) {
        // Initialize toolbarTitle
        toolbarTitle = findViewById(R.id.tvTitle)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> toolbarTitle.text = "Home"
                R.id.profileFragment -> toolbarTitle.text = "Profile"
                // Add more cases as needed
                R.id.chatsFragment -> toolbarTitle.text = "Chats"
                R.id.kundaliFragment -> toolbarTitle.text = "Kundali "
                R.id.kundaliMatchingFragment -> toolbarTitle.text = "kundali Matching"
                R.id.dailyPanchangFragment -> toolbarTitle.text = "Daily Panchang"
                R.id.shoppingFragment -> toolbarTitle.text = "Astro Shop"


                else -> toolbarTitle.text = getString(R.string.app_name).toString() // Default title
            }
        }

    }


}