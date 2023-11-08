package com.proyecto.personal.puppisparcialtp3.activities

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.helpers.SharedPref

class InitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
        SharedPref.init(applicationContext);

        val userName = SharedPref.read(SharedPref.NAME, null)
        if (userName != null) {
            // User is signed in
            val i = Intent(this@InitActivity, HomeActivity::class.java)
            startActivity(i)
        }
    }
}