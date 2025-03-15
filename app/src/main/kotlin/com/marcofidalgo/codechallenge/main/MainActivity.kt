package com.marcofidalgo.codechallenge.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.marcofidalgo.feature.home.ui.HomeActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}