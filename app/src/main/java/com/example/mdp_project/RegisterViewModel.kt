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
    val array_user: ArrayList<UserList> = MockDB.user

    suspend fun registerList(username: String, password: String, cPassword:String):String {
        if (username == "" || password == "" || cPassword == "") {
//            Toast.makeText(getApplication(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return "Please fill in all fields"
        }
        for (user in array_user) { //
            if (user.userName == username) {
//                Toast.makeText(getApplication(), "Username already exists", Toast.LENGTH_SHORT).show()
                return "Username already exists"
            }
        }
        if (password != cPassword) {
//            Toast.makeText(getApplication(), "Passwords do not match", Toast.LENGTH_SHORT).show()
            return "Passwords do not match"
        }
        MockDB.user.add(UserList(username, password))
        Utils.sendUserToSql(username, password)
        return "Register Successful"
    }
}
