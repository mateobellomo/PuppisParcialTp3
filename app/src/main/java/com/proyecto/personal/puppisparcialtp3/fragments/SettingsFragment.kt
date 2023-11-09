package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentSettingsBinding
import com.proyecto.personal.puppisparcialtp3.helpers.SharedPref

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var switch: Switch
    private val nightMode: Boolean = SharedPref.read(SharedPref.DARK_MODE, false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        switch = binding.themeModeSwitch

        if(nightMode) {
            switch.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        switch.setOnClickListener {
            if (nightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                SharedPref.write(SharedPref.DARK_MODE, false)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                SharedPref.write(SharedPref.DARK_MODE, true)
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        var toolbar = binding.toolbarSettings
        toolbar.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}