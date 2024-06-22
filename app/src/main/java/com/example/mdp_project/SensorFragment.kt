package com.example.mdp_project

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.mdp_project.databinding.FragmentLoginBinding
import com.example.mdp_project.databinding.FragmentSensorBinding


class SensorFragment : Fragment() {
    lateinit var binding: FragmentSensorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSensorBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
            }
        })
        binding.btnBacksns.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
        }

        val data = arguments?.getString("key")
        Log.d("data", data.toString())
        val position = data?.toInt()
        val deviceInfo = MockDB.member[position!!]
        Log.d("deviceName", deviceInfo.toString())
        Log.d("deviceName", deviceInfo.memberName)

        binding.deviceNameSensor.setText(deviceInfo.memberName)
    }

}