package com.example.mdp_project

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mdp_project.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userList : ArrayList<UserList> = MockDB.user
        val username = resources.getStringArray(R.array.username)
        val password = resources.getStringArray(R.array.password)



        binding.loginButton.setOnClickListener{
            val usernameInput = binding.usernameInput.text.toString()
            val passwordInput = binding.passwordInput.text.toString()
            if(usernameInput.isEmpty()){
                binding.usernameInput.error = "Username Kosong"
                return@setOnClickListener
            }
            if(passwordInput.isEmpty()){
                binding.passwordInput.error = "Password Kosong"
                return@setOnClickListener
            }

            val user = userList.find { it.userName == usernameInput }
            if (user == null){
                binding.usernameInput.error = "User not found"
                return@setOnClickListener
            }

            if (user.password != passwordInput){
                binding.passwordInput.error = "Wrong password"
                return@setOnClickListener
            }
            binding.usernameInput.text.clear()
            binding.passwordInput.text.clear()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)

//            for(i in username.indices){
//                if(usernameInput == username[i] && passwordInput == password[i]){
//                    binding.usernameInput.text.clear()
//                    binding.passwordInput.text.clear()
//                    val intent = Intent(activity, MainActivity::class.java)
//                    startActivity(intent)
////                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
////                    findNavController().navigate(action)
//                }
//            }


        }
    }

}