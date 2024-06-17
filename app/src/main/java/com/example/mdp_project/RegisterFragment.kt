package com.example.mdp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mdp_project.databinding.FragmentRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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
            val username = binding.etUsernamerR.text.toString()
            val password = binding.etPasswordR.text.toString()
            val cpassword = binding.etCPasswordR.text.toString()
            ioScope.launch {
               val msg = vm.registerList(username, password, cpassword)
                    mainScope.launch {
                        binding.etUsernamerR.setText("")
                        binding.etPasswordR.setText("")
                        binding.etCPasswordR.setText("")
                        if(msg == "Register Successful"){
                            Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                        }else if(msg == "Passwords do not match"){
                            Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                        }else if(msg == "Username already exists"){
                            Toast.makeText(requireContext(), "Username already exists", Toast.LENGTH_SHORT).show()
                        }else if(msg == "Please fill in all fields"){
                            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                        }
                }
            }
//            if(username.isNotEmpty() && password.isNotEmpty() && cpassword.isNotEmpty()){
//                if(password == cpassword){
//                    ioScope.launch {
//                        Utils.sendUserToSql(username, password)
//                        mainScope.launch{
//                            findNavController().popBackStack()
//                        }
//                    }
//                }
//            }
//            else{
//                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
//            }
        }

        binding.btnLoginR.setOnClickListener{
            findNavController().popBackStack()
        }

//        vm = ViewModelProvider(this).get(RegisterViewModel::class.java)

//        binding.btnRegisterR.setOnClickListener{
//            ioScope.launch {
//                val msg = vm.RegisterList(binding.etUsernamerR.text.toString(), binding.etPasswordR.text.toString(),binding.etCPasswordR.text.toString())
//                mainScope.launch{
//                    if(msg == "Register Success"){
//                        binding.etUsernamerR.text.setText("")
//                        binding.etPasswordR.text.setText("")
//                        binding.etCPasswordR.text.setText("")
//                    }
//                    Toast.makeText(this@RegisterFragment, msg, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//        binding.btnLoginR.setOnClickListener{

//        }

    }

    companion object {

    }
}