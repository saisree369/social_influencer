package com.practice.socialinfluencerr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class InfluencerFollowersAdapter(private val myContext: Context, val example1: List<Content>) : RecyclerView.Adapter<InfluencerFollowersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(view: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(view.context).inflate(R.layout.recycler_influencer_follower, view, false)
        return ViewHolder(v);
    }
    override fun getItemCount(): Int {
        return (example1.size)
    }
    override fun onBindViewHolder(view: ViewHolder, position: Int) {
        Glide.with(myContext)
                .load(example1[position].profile_pic)
                .into(view.image);
//        val id =myContext.getResources().getIdentifier("drawable/" + example[position].profile_pic, null, myContext.getPackageName())
        //       view.name.setImageResource(id)
        view.name.text = example1[position].name
        view.age.text = example1[position].age.toString()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.image_influencer)
        val name = itemView.findViewById<TextView>(R.id.name)
        val age = itemView.findViewById<TextView>(R.id.age)

    }



}
