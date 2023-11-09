package com.proyecto.personal.puppisparcialtp3.activities

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.helpers.SharedPref

class InitActivity : AppCompatActivity() {
    private var nightMode: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
        SharedPref.init(applicationContext);
        nightMode = SharedPref.read(SharedPref.DARK_MODE, false)

        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        val userName = SharedPref.read(SharedPref.NAME, "")
        if (!userName.isNullOrEmpty()) {
            // User is signed in
            val i = Intent(this@InitActivity, HomeActivity::class.java)
            startActivity(i)
        }
    }
}