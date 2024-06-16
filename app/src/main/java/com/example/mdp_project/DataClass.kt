package com.example.mdp_project

data class MemberList(val memberId:String, val memberName: String, val memberGroup: String)
//data class MemberList(val memberName: String, val memberGroup: String, val logo: Int)
data class UserList(val userName: String, val password: String)
data class RegisterList(val userName: String, val Password:String, val cPassword:String)

data class Msg(
    var text: String
)