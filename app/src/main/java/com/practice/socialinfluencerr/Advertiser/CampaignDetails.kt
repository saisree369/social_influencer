package com.practice.socialinfluencerr

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.practice.socialinfluencerr.models.Example1
import com.practice.socialinfluencerr.models.Influencers
import org.w3c.dom.Text

class CampaignDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val view = inflater.inflate(R.layout.fragment_campaign_details, container, false)
                var data = ExploreInfluencerAdapter.getData()
                var position:Int = ExploreInfluencerAdapter.getPostion()

                var pic  = view.findViewById(R.id.pic) as ImageView
                var desc=view.findViewById(R.id.desc) as TextView
                var money=view.findViewById(R.id.money) as TextView
                var brand_name =view.findViewById(R.id.brand_name) as TextView
                var city=view.findViewById(R.id.city) as TextView
    //          var age = view.findViewById(R.id.age) as TextView
    //          var gender = view.findViewById(R.id.gender) as TextView
                var followers = view.findViewById(R.id.followers) as TextView
                var start_date = view.findViewById(R.id.start_date) as TextView
                var end_date = view.findViewById(R.id.end_date) as TextView


                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).profile_url)
                    .into(pic)
                desc.text =data.get(position).description
                money.text = data.get(position).budget
                brand_name.text = data.get(position).brand_name
                city.text = data.get(position).city
      //        age.text =  data.get(position).age
                followers.text =  data.get(position).influencers.get(0).followers.toString()
      //        gender.text =  data.get(position).influencers.get(0).gender
                start_date.text = data.get(position).influencers.get(0).start_date
                end_date.text = data.get(position).influencers.get(0).end_date
        return view
    }
}
