package com.sample.zoopark


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI


class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        navController?.let {
            NavigationUI.setupActionBarWithNavController(this, it)
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        navController?.navigateUp()
        return super.onSupportNavigateUp()

    }
}