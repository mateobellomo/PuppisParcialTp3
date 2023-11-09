package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.proyecto.personal.puppisparcialtp3.R
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.proyecto.personal.puppisparcialtp3.adapters.ImagePagerAdapter
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentInitBinding

class InitFragment : Fragment() {

    private lateinit var binding: FragmentInitBinding

    private var logInBtn: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInitBinding.inflate(inflater, container, false)

        logInBtn = binding.initBtn
        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        val imageList = listOf(R.drawable.init_image_1, R.drawable.init_image_2, R.drawable.init_image_3)
        val adapter = ImagePagerAdapter(requireContext(), imageList)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "."
        }.attach()

        this.logInBtn?.setOnClickListener {
            findNavController().navigate(R.id.action_initFragment_to_logInFragment2)
        }

        return binding.root
    }
}