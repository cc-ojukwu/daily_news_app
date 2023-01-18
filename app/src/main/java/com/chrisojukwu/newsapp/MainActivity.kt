package com.chrisojukwu.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.chrisojukwu.newsapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //prevent phone keyboard from pushing up page
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        //set custom toolbar as ActionBar
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val bottomNavView: BottomNavigationView = binding.bottomNav

        //list of fragments that should be regarded as first level destinations
        //this tells the navController to remove the up navigate button from these destinations
        val appBarConfiguration = AppBarConfiguration
            .Builder(R.id.containerFragment, R.id.searchNewsFragment, R.id.topPicksFragment)
            .build()

        //setup navController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        //link the navController to the bottomNav for navigation
        NavigationUI.setupWithNavController(bottomNavView, navController)

        //link the appBarConfiguration to the navController
        setupActionBarWithNavController(navController, appBarConfiguration)

        //remove bottom nav from view when the following destinations are active
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.completeNewsFragment ||
                destination.id == R.id.fullNewsPageSportsFragment ||
                destination.id == R.id.fullNewsPageTechFragment ||
                destination.id == R.id.fullNewsPageSearchFragment
            ) {
                bottomNavView.visibility = View.GONE
            } else {
                bottomNavView.visibility = View.VISIBLE
            }
        }

    }

    //call the navController to handle up button clicks from the ActionBar
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
