package com.practice.socialinfluencerr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.socialinfluencerr.databinding.SubProfileBinding

class CampaignAdapter(private val myContext: Context, val example:List<Campaign> ) : RecyclerView.Adapter<CampaignAdapter.ViewHolder>() {
    override fun onCreateViewHolder(view: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(view.context).inflate(R.layout.recycle_campaign, view, false)
        return ViewHolder(v);
    }
    override fun getItemCount(): Int {
        return (example.size)
    }
    override fun onBindViewHolder(view: ViewHolder, position: Int) {
        Glide.with(myContext)
            .load(example[position].url)
            .into(view.brand_image);
//        val id =myContext.getResources().getIdentifier("drawable/" + example[position].profile_pic, null, myContext.getPackageName())
        //       view.name.setImageResource(id)
        view.category_name.text = example[position].category_selected.toString()
        view.brand_name.text =example[position].brand.toString()
        view.product_description.text = example[position].product_desc.toString()

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val brand_image = itemView.findViewById<ImageView>(R.id.main_image)
        val category_name = itemView.findViewById<TextView>(R.id.category_name)
        val brand_name = itemView.findViewById<TextView>(R.id.brand_name)
        val product_description = itemView.findViewById<TextView>(R.id.product_desc)

    }

}
