package com.proyecto.personal.puppisparcialtp3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.helpers.SharedPref

class InitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
        SharedPref.init(applicationContext);
    }
}