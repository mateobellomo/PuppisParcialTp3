package com.proyecto.personal.puppisparcialtp3

import android.app.Application
import com.proyecto.personal.puppisparcialtp3.core.Config
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PuppiesParcial : Application() {
    override fun onCreate() {
        super.onCreate()
        Config.baseUrl = resources.getString(R.string.quotes_api_base_url)
    }
}