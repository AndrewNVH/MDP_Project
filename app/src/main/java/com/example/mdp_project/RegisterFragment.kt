package com.example.mdp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mdp_project.databinding.FragmentRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import android.widget.Toast
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    val ioScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)

    lateinit var vm: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_register, container, false)
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.btnRegisterR.setOnClickListener{
            ioScope.launch {
                val msg = vm.RegisterList(binding.etUsernamerR.text.toString(), binding.etPasswordR.text.toString(),binding.etCPasswordR.text.toString())
                mainScope.launch{
                    if(msg == "Register Success"){
                        binding.etUsernamerR.text.setText("")
                        binding.etPasswordR.text.setText("")
                        binding.etCPasswordR.text.setText("")
                    }
                    Toast.makeText(this@RegisterFragment, msg, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    companion object {

    }
}