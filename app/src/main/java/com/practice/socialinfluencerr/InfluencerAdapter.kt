package com.practice.socialinfluencerr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class InfluencerAdapter(private val myContext: Context, val example: ArrayList<Content>) : RecyclerView.Adapter<InfluencerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(view: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(view.context).inflate(R.layout.recycler_influencer, view, false)
        return ViewHolder(v);
    }
    override fun getItemCount(): Int {
        return (example.size)
    }
    override fun onBindViewHolder(view: ViewHolder, position: Int) {
        Glide.with(myContext)
            .load(example[position].profile_pic)
            .into(view.name);
//        val id =myContext.getResources().getIdentifier("drawable/" + example[position].profile_pic, null, myContext.getPackageName())
 //       view.name.setImageResource(id)
        view.posts.text = example[position].posts.toString()
        view.followers.text =example[position].followers.toString()
        view.connects.text = example[position].Connects.toString()

    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<ImageView>(R.id.image_influencer)
        val posts = itemView.findViewById<TextView>(R.id.tv_posts)
        val followers = itemView.findViewById<TextView>(R.id.tv_followers)
        val connects = itemView.findViewById<TextView>(R.id.tv_connects)

    }

}
