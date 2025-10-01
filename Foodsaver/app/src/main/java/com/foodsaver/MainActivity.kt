package com.foodsaver

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.foodsaver.ui.HomeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Navigate to HomeActivity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish() // Close MainActivity
    }
}