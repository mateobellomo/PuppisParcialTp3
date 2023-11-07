package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.proyecto.personal.puppisparcialtp3.R
import androidx.navigation.fragment.findNavController
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentInitBinding

class InitFragment : Fragment() {

    private lateinit var binding: FragmentInitBinding

    private var logInBtn: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInitBinding.inflate(inflater, container, false)

        this.logInBtn = binding.initBtn
        this.logInBtn?.setOnClickListener {
            findNavController().navigate(R.id.action_initFragment_to_logInFragment2)
        }

        return binding.root
    }
}