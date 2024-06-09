package com.example.mdp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mdp_project.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    lateinit var binding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnBack.setOnClickListener {
//            findNavController().popBackStack()
//            findNavController().navigate(R.id.action_global_homeFragment)
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
//            findNavController().navigate(R.id.action_infoFragment_to_homeFragment)
//                parentFragmentManager.beginTransaction().replace(, HomeFragment()).commit()
//            finish()
        }

        binding.seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.seekBarValue.text = p1.toString()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(requireContext(), "SeekBar touched!", Toast.LENGTH_SHORT).show()

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(requireContext(), "SeekBar touched!", Toast.LENGTH_SHORT).show()
            }
        })
    }

}