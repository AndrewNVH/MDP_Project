package com.example.mdp_project

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.widget.Toast

class RegisterViewModel(application: Application): AndroidViewModel(application) {
    val ioScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)

    suspend fun RegisterList(username: String, password: String, cPassword:String) {
//        if (username == "" || password == "" || cPassword == "") {
//            Toast.makeText(getApplication(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
////            return "Please fill in all fields"
//        }
//        if (password != cPassword) {
//            Toast.makeText(getApplication(), "Passwords do not match", Toast.LENGTH_SHORT).show()
////            return "Passwords do not match"
//        }
    }
}
