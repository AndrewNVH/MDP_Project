package com.example.mdp_project

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.mdp_project.databinding.FragmentLCDBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LCDFragment : Fragment() {
    val ioScope = CoroutineScope(Dispatchers.IO)
    lateinit var binding : FragmentLCDBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLCDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
            }
        })

        val data = arguments?.getString("key")
        Log.d("data", data.toString())
        val position = data?.toInt()
        val deviceInfo = MockDB.member[position!!]
        Log.d("deviceName", deviceInfo.toString())
        Log.d("deviceName", deviceInfo.memberName)

        binding.btnBackLCD.setOnClickListener{
            ioScope.launch {
                val deviceIpAddress = "http://" + binding.deviceIpAddLCD.text.toString()
                API.configureRetrofit(deviceIpAddress)
                API.retrofitService.LCD(binding.LCDText.text.toString())
            }
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
        }

    }

}