package com.practice.socialinfluencerr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.socialinfluencerr.models.Influencer_menu

class CategoryImageAdapter (private val myContext: Context, val items: ArrayList<Influencer_menu>, val recyclerView_explore_influencer: RecyclerView) : RecyclerView.Adapter<CategoryImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(view: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(view.context).inflate(R.layout.recycler_category, view, false)
        return ViewHolder(v);
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(view: ViewHolder, position: Int) {
/*
        view.name?.setImageResource(userList[position].name)
        view.count?.text = userList[position].count
        */
        view.category_name?.text =items[position].category_label
        Glide.with(myContext)
            .load(items[position].category_icon_url)
            .into(view.category_icon);

        view.category_icon.setOnClickListener {
            var exploreInfluencerAdapter = items?.get(position)?.influencers?.let { ExploreInfluencerAdapter(myContext, it) }
            recyclerView_explore_influencer.adapter = exploreInfluencerAdapter
        }

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category_name = itemView.findViewById<TextView>(R.id.category_title)
        val category_icon = itemView.findViewById<ImageView>(R.id.category_image)

    }

}
