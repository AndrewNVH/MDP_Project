package com.example.mdp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mdp_project.databinding.FragmentHomeBinding

class HomeFragment : Fragment(),  MemberListAdapter.OnItemClickListener{
    lateinit var binding: FragmentHomeBinding
    val memberList: ArrayList<MemberList> = MockDB.member

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

        // Access from String.h ... not needed... uncomment unless you use database
//        val memberName = resources.getStringArray(R.array.memberNameList)
//        val memberGroup = resources.getStringArray(R.array.memberGroupList)
//        if (memberList.size != memberGroup.size) {
//            for (i in memberName.indices) {
//                if (memberGroup[i] == "Nogizaka46"||memberGroup[i] == "乃木坂46") {
//                    memberList.add(MemberList(memberName[i], memberGroup[i], groupLogo[0]))
//                }
//                if (memberGroup[i] == "Sakurazaka46"||memberGroup[i] == "櫻坂46") {
//                    memberList.add(MemberList(memberName[i], memberGroup[i], groupLogo[1]))
//                }
//                if (memberGroup[i] == "Hinatazaka46"||memberGroup[i] == "日向坂46") {
//                    memberList.add(MemberList(memberName[i], memberGroup[i], groupLogo[2]))
//                }
//            }
//        }


        val memberAdapter = MemberListAdapter(memberList, this)

        binding.memberList.text = getString(R.string.memberList)
        binding.memberRv.adapter = memberAdapter
        binding.memberRv.layoutManager = GridLayoutManager(context,2)
//        binding.memberRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)



    }


    override fun onItemClick(position: Int) {
        // Handle item click
        val clickedItem = memberList[position]
        val infoFragment = InfoFragment()
        val bundle = Bundle()
        bundle.putString("key", position.toString())
        infoFragment.arguments = bundle
        parentFragmentManager.beginTransaction().replace(R.id.fragment_container, infoFragment).commit()
//        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
//        findNavController().navigate(R.id.action_homeFragment_to_infoFragment)
        Toast.makeText(requireContext(), "Clicked: $clickedItem", Toast.LENGTH_SHORT).show()
    }

}