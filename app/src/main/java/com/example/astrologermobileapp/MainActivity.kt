package com.example.astrologermobileapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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

            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController

            // Uncheck all items before checking the selected one
            for (i in 0 until navigationView.menu.size()) {
                val item = navigationView.menu.getItem(i)
                item.isChecked = false
                // If the item has a submenu, uncheck its children too
                if (item.hasSubMenu()) {
                    for (j in 0 until (item.subMenu?.size() ?: 0)) {
                        val subItem = item.subMenu?.getItem(j)
                        if (subItem != null) {
                            subItem.isChecked = false
                        }
                    }
                }
            }

            val handled = when (menuItem.itemId) {
                R.id.nav_Shopping -> {
                    // Navigate to ShoppingFragment
                    navController.navigate(R.id.shoppingFragment)
                    true
                }

                R.id.nav_faq_support -> {
                    // Start SupportActivity
                   // startActivity(Intent(this, SupportActivity::class.java))
                    true
                }
                R.id.nav_how_to_use -> {
                    // Start UserGuideActivity
                   // startActivity(Intent(this, UserGuideActivity::class.java))
                    true
                }
                R.id.nav_about_us -> {
                    // Show About Us Dialog
                    showAboutUsDialog()
                    true
                }

                R.id.nav_rate_us -> {
                    // Show Rating Dialog
                    showRatingDialog()
                    true
                }
                R.id.nav_share_app -> {
                    // Share the app via any platform
                    shareApp()
                    true
                }
                R.id.nav_logOut -> {
                    // Handle logout
                    //handleLogout()
                    true
                }
                else -> false
            }

            if (handled) {
                // Highlight the selected item
                menuItem.isChecked = true

                // Close the drawer after the option is clicked
                drawerLayout.closeDrawer(GravityCompat.START)
            }

            handled
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

    // Menu Item Functions ........

    private fun shareApp() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Check out this amazing app!")
            putExtra(Intent.EXTRA_TEXT, "Download this app from: [Play Store link]")
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }


    private fun showRatingDialog() {
        // Inflate the dialog layout
        val dialogView = layoutInflater.inflate(R.layout.dialog_rate_us, null)

        // Find the RatingBar and EditText from the inflated view
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.ratingBar)
        val feedbackEditText = dialogView.findViewById<EditText>(R.id.etFeedback)

        // Create the dialog
        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Submit") { dialog, _ ->
                // Retrieve rating and feedback from the views
                val rating = ratingBar.rating
                val feedback = feedbackEditText.text.toString()

                // Handle the submission of rating and feedback
                handleRatingSubmission(rating, feedback)

                // Dismiss the dialog
                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
            .show()
    }


    private fun handleRatingSubmission(rating: Float, feedback: String) {
        // For example, log the rating and feedback or send them to a server
        Log.d("RatingDialog", "Rating: $rating, Feedback: $feedback")

        // You can also show a Toast or update UI based on the submission
        Toast.makeText(this, "Thank you for your ${rating.toInt()}★ rating and feedback!", Toast.LENGTH_LONG).show()
    }



    private fun showAboutUsDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_about_us, null)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton(android.R.string.ok, null)
            .create()
            .show()
    }

}