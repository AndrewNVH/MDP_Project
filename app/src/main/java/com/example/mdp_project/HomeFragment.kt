package com.example.mdp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdp_project.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val groupLogo = intArrayOf(
        R.drawable.nogizaka46,
        R.drawable.sakurazaka46,
        R.drawable.hinatazaka46
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Access views using the binding object
        val memberList: ArrayList<MemberList> = MockDB.member
        val memberName = resources.getStringArray(R.array.memberNameList)
        val memberGroup = resources.getStringArray(R.array.memberGroupList)
        if (memberList.size != memberGroup.size) {
            for (i in memberName.indices) {
                if (memberGroup[i] == "Nogizaka46"||memberGroup[i] == "乃木坂46") {
                    memberList.add(MemberList(memberName[i], memberGroup[i], groupLogo[0]))
                }
                if (memberGroup[i] == "Sakurazaka46"||memberGroup[i] == "櫻坂46") {
                    memberList.add(MemberList(memberName[i], memberGroup[i], groupLogo[1]))
                }
                if (memberGroup[i] == "Hinatazaka46"||memberGroup[i] == "日向坂46") {
                    memberList.add(MemberList(memberName[i], memberGroup[i], groupLogo[2]))
                }
            }
        }


        val memberAdapter = MemberListAdapter(memberList)

        binding.memberList.text = getString(R.string.memberList)
        binding.memberRv.adapter = memberAdapter
        binding.memberRv.layoutManager = GridLayoutManager(context,2)
//        binding.memberRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)



    }
}