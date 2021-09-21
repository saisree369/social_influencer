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

class AmountAdapter(private val myContext: Context, val example:List<Campaign> ) : RecyclerView.Adapter<AmountAdapter.ViewHolder>() {
    override fun onCreateViewHolder(view: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(view.context).inflate(R.layout.recycler_view_amount, view, false)
        return ViewHolder(v);
    }
    override fun getItemCount(): Int {
        return (example.size)
    }
    override fun onBindViewHolder(view: ViewHolder, position: Int) {
        Glide.with(myContext)
                .load(example[position].url)
                .into(view.image);
//        val id =myContext.getResources().getIdentifier("drawable/" + example[position].profile_pic, null, myContext.getPackageName())
        //       view.name.setImageResource(id)
        view.name.text = example[position].brand.toString()
        view.amount.text =example[position].price.toString()

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.image_influencer)
        val name = itemView.findViewById<TextView>(R.id.name)
        val amount = itemView.findViewById<TextView>(R.id.amount)

    }

}
