package com.foodsaver.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.foodsaver.R
import com.foodsaver.ui.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Set up login button click listener
        login_button.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        // Logic for user authentication goes here

        // On successful login, navigate to HomeActivity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onRegisterClick(view: View) {
        // Navigate to RegisterActivity
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}