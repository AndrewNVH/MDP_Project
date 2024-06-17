package com.example.mdp_project

data class MemberList(val deviceId:String, val memberName: String, val deviceType: String)
//data class MemberList(val memberName: String, val memberGroup: String, val logo: Int)
data class UserList(val userName: String, val password: String)


data class Msg(
    var text: String
)