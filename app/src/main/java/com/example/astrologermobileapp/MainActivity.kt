package com.example.astrologermobileapp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.astrologermobileapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    //private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbarTitle: TextView


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set the status bar color to yellow
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            handleNavigationItemSelected(menuItem)
            true
        }

       binding.ivMenu.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }


        setupBottomNavigation()


    }


    private fun handleNavigationItemSelected(menuItem: MenuItem) {
        when (menuItem.itemId) {
//            R.id.nav_dashboard -> {
//                // Handle Dashboard click
//            }
//            R.id.nav_order_history -> {
//                // Handle Order History click
//            }
//            R.id.nav_wallet -> {
//                // Handle Wallet Transactions click
//            }
//            R.id.nav_settings -> {
//                // Handle Settings click
//            }
//            R.id.nav_faq_support -> {
//                // Handle FAQs & Support click
//            }
//            R.id.nav_how_to_use -> {
//                // Handle How to Use click
//            }
//            R.id.nav_rate_us -> {
//                // Handle Rate Us click
//            }
//            R.id.nav_share_app -> {
//                // Handle Share App click
//            }
//            R.id.nav_about_us -> {
//                // Handle About Us click
//            }
        }
        // Highlight the selected item
        menuItem.isChecked = true
        // Close the drawer after handling the click
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
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