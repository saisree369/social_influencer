package com.practice.socialinfluencerr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ConnectImageAdapter (val userList: ArrayList<Connect>) : RecyclerView.Adapter<ConnectImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(view: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(view.context).inflate(R.layout.recycler_inf_connects, view, false)
        return ViewHolder(v);
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    override fun onBindViewHolder(view: ViewHolder, position: Int) {

        view.name?.setImageResource(userList[position].name)
        view.count?.text = userList[position].count
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<ImageView>(R.id.ImageView)
        val count = itemView.findViewById<TextView>(R.id.ImageViewText)

    }

}
