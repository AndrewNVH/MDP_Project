package com.example.mdp_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemberListAdapter(val data:ArrayList<MemberList>) : RecyclerView.Adapter<MemberListAdapter.viewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemberListAdapter.viewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_row,parent,false)
        return viewHolder(layout)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val member = data[position]
        holder.memberName.text = member.memberName
        holder.memberGroup.text = member.memberGroup
        holder.groupLogo.setImageResource(member.logo)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val memberName: TextView = itemView.findViewById(R.id.memberName)
        val memberGroup:TextView = itemView.findViewById(R.id.memberGroup)
        val groupLogo:ImageView = itemView.findViewById(R.id.imageView2)

    }
}