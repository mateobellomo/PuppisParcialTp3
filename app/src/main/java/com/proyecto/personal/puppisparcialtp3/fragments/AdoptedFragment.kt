package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentAdoptedBinding
import com.proyecto.personal.puppisparcialtp3.viewModels.AdoptedViewModel

class AdoptedFragment : Fragment() {

    private var _binding: FragmentAdoptedBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adoptedViewModel =
            ViewModelProvider(this).get(AdoptedViewModel::class.java)

        _binding = FragmentAdoptedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        adoptedViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}