package com.example.mdp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            for(i in username.indices){
                if(usernameInput == username[i] && passwordInput == password[i]){
//                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
//                    findNavController().navigate(action)
                }
            }

        }
    }

}