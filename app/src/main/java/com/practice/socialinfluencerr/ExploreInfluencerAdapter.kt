package com.practice.socialinfluencerr

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.socialinfluencerr.models.Influencers
import androidx.core.content.ContextCompat.startActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import java.io.Serializable
lateinit var data_1: List<Influencers>
 var current_position = 0
class ExploreInfluencerAdapter (private val myContext: Context, public val items: List<Influencers>) : RecyclerView.Adapter<ExploreInfluencerAdapter.ViewHolder>() {

    companion object {
        fun getData():List<Influencers>{
            return data_1
        }
        fun getPostion():Int{
            return current_position
        }
    }
    override fun onCreateViewHolder(view: ViewGroup, position: Int): ViewHolder {
        data_1 = items
        val v = LayoutInflater.from(view.context).inflate(R.layout.recycler_explore_influencer, view, false)
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
        view.influencer_brand?.text =items[position].brand_name
//        view.influencer_connects?.text = items[position].connects.toString()
//        view.influencer_followers?.text = items[position].followers.toString()
        Glide.with(myContext)
            .load(items[position].profile_url)
            .into(view.category_icon);
        Glide.with(myContext)
            .load(items[position].brand_url)
            .into(view.campaign_image);
        view.age?.text =items[position].age.toString()
        view.platform_details?.text = items[position].social_media_platforms
        view.description_product?.text = items[position].description


        view.button_view.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View) {


               val intent = Intent(myContext, Influencer_campaign::class.java)
                //intent.putExtra("items", items as Serializable)
//                Log.e("Items","Hai ${items[position].description}")
                current_position = position
               myContext.startActivity(intent)

//                val bundle = Bundle()
//                bundle.putParcelable("data", items as Parcelable)
//
//                intent.putExtras(bundle)
//         //     intent.startActivity()
//              myContext.startActivity(Intent(myContext, Influencer_campaign::class.java))
            }
        })

}
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val category_icon = itemView.findViewById<ImageView>(R.id.image_influencer)
        val influencer_brand = itemView.findViewById<TextView>(R.id.tv_brand)

        val influencer_followers = itemView.findViewById<TextView>(R.id.tv_followers)
        val campaign_image = itemView.findViewById<ImageView>(R.id.campaign_image)
        val age = itemView.findViewById<TextView>(R.id.age)
        val platform_details =  itemView.findViewById<TextView>(R.id.platform)
        val description_product = itemView.findViewById<TextView>(R.id.product_desc)
        val button_view = itemView.findViewById<Button>(R.id.buttonView)

    }

}