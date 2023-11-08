package com.proyecto.personal.puppisparcialtp3.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.activities.HomeActivity
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentLogInBinding
import com.proyecto.personal.puppisparcialtp3.helpers.SharedPref

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    private var continueBtn: Button? = null
    private var nameEditText: EditText? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)

        this.continueBtn = binding.navigateHomeBtn
        this.nameEditText = binding.nameEditTxt

        this.continueBtn?.setOnClickListener {
            this.checkNameAndNavigate()
        }

        return binding.root
    }

    private fun checkNameAndNavigate() {
        val name = this.nameEditText?.text.toString()
        if (name.isNullOrEmpty()) {
            this.nameEditText?.error = "Mandatory field!"

            Handler().postDelayed({
                this.nameEditText?.error = null
            }, 3000)
        } else {
            SharedPref.write(SharedPref.NAME, name)
            val intent = Intent (activity, HomeActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}