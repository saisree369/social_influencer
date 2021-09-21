package com.practice.socialinfluencerr

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
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.practice.socialinfluencerr.models.Example1
import com.practice.socialinfluencerr.models.Media_instagram
import de.hdodenhof.circleimageview.CircleImageView


class InfluencerInfo : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_influencer_info, container, false)



                var data = ExploreInfluencerAdapter.getData()
                var position:Int = ExploreInfluencerAdapter.getPostion()

                var inf_pic = view.findViewById(R.id.inf_pic) as CircleImageView
                var inf_name = view.findViewById(R.id.inf_name) as TextView
                var inf_city = view.findViewById(R.id.inf_city) as TextView
                var inf_budget = view.findViewById(R.id.inf_budget) as TextView
                var inf_connects = view.findViewById(R.id.inf_connects) as TextView
                var inf_followers = view.findViewById(R.id.inf_followers) as TextView

                var inf_insta_post = view.findViewById(R.id.inf_insta_post) as TextView
                var inf_insta_following = view.findViewById(R.id.inf_insta_following) as TextView
                var inf_insta_followers = view.findViewById(R.id.inf_insta_followers) as TextView
                var inf_youtube_post = view.findViewById(R.id.inf_youtube_post) as TextView
                var inf_youtube_following = view.findViewById(R.id.inf_youtube_following) as TextView
                var inf_youtube_followers = view.findViewById(R.id.inf_youtube_followers) as TextView
                var inf_twitter_post = view.findViewById(R.id.inf_twitter_post) as TextView
                var inf_twitter_following = view.findViewById(R.id.inf_twitter_following) as TextView
                var inf_twitter_followers = view.findViewById(R.id.inf_twitter_followers) as TextView

                var insta_url_1 =view.findViewById(R.id.insta_pic_1) as ImageView
                var insta_url_2 =view.findViewById(R.id.insta_pic_2) as ImageView
                var insta_url_3 =view.findViewById(R.id.insta_pic_3) as ImageView
                var insta_url_4 =view.findViewById(R.id.insta_pic_4) as ImageView
                var insta_url_5 =view.findViewById(R.id.insta_pic_5) as ImageView
                var insta_url_6 =view.findViewById(R.id.insta_pic_6) as ImageView

                var utube_url_1 =view.findViewById(R.id.utube_pic_1) as ImageView
                var utube_url_2 =view.findViewById(R.id.utube_pic_2) as ImageView
                var utube_url_3 =view.findViewById(R.id.utube_pic_3) as ImageView
                var utube_url_4 =view.findViewById(R.id.utube_pic_4) as ImageView
                var utube_url_5 =view.findViewById(R.id.utube_pic_5) as ImageView
                var utube_url_6 =view.findViewById(R.id.utube_pic_6) as ImageView

                var twitter_url_1 =view.findViewById(R.id.twitter_pic_1) as ImageView
                var twitter_url_2 =view.findViewById(R.id.twitter_pic_2) as ImageView
                var twitter_url_3 =view.findViewById(R.id.twitter_pic_3) as ImageView
                var twitter_url_4 =view.findViewById(R.id.twitter_pic_4) as ImageView
                var twitter_url_5 =view.findViewById(R.id.twitter_pic_5) as ImageView
                var twitter_url_6 =view.findViewById(R.id.twitter_pic_6) as ImageView


                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position)?.profile_url)
                    .into(inf_pic)
                inf_name.text = data.get(position).name
                inf_city.text = data.get(position).city
                inf_budget.text = data.get(position).budget
                inf_connects.text = data.get(position).connects.toString()
                inf_followers.text = data.get(position).followers.toString()

                inf_youtube_post.text = data.get(position).instagram?.get(0)?.post.toString()
                inf_youtube_following.text = data.get(position).instagram?.get(0)?.following.toString()
                inf_insta_followers.text = data.get(position).instagram?.get(0)?.followers.toString()

                inf_insta_post.text = data.get(position).youtube?.get(0)?.post.toString()
                inf_insta_following.text = data.get(position).youtube?.get(0)?.following.toString()
                inf_youtube_followers.text = data.get(position).youtube?.get(0)?.followers.toString()

                inf_twitter_post.text = data.get(position).twitter?.get(0)?.post.toString()
                inf_twitter_following.text = data.get(position).twitter?.get(0)?.following.toString()
                inf_twitter_followers.text = data.get(position).twitter?.get(0)?.followers.toString()

                Glide.with(view.context.getApplicationContext())
                .load(data.get(position).instagram?.get(0)?.media_instagram?.get(0)?.url_1)
                .into(insta_url_1)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).instagram?.get(0)?.media_instagram?.get(0)?.url_2)
                    .into(insta_url_2)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).instagram?.get(0)?.media_instagram?.get(0)?.url_3)
                    .into(insta_url_3)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).instagram?.get(0)?.media_instagram?.get(0)?.url_4)
                    .into(insta_url_4)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).instagram?.get(0)?.media_instagram?.get(0)?.url_5)
                    .into(insta_url_5)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).instagram?.get(0)?.media_instagram?.get(0)?.url_6)
                    .into(insta_url_6)


                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).youtube?.get(0)?.media_youtube?.get(0)?.url_1)
                    .into(utube_url_1)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).youtube?.get(0)?.media_youtube?.get(0)?.url_2)
                    .into(utube_url_2)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).youtube?.get(0)?.media_youtube?.get(0)?.url_3)
                    .into(utube_url_3)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).youtube?.get(0)?.media_youtube?.get(0)?.url_4)
                    .into(utube_url_4)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).youtube?.get(0)?.media_youtube?.get(0)?.url_5)
                    .into(utube_url_5)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).youtube?.get(0)?.media_youtube?.get(0)?.url_6)
                    .into(utube_url_6)



                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).twitter?.get(0)?.media_twitter?.get(0)?.url_1)
                    .into(twitter_url_1)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).twitter?.get(0)?.media_twitter?.get(0)?.url_2)
                    .into(twitter_url_2)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).twitter?.get(0)?.media_twitter?.get(0)?.url_3)
                    .into(twitter_url_3)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).twitter?.get(0)?.media_twitter?.get(0)?.url_4)
                    .into(twitter_url_4)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).twitter?.get(0)?.media_twitter?.get(0)?.url_5)
                    .into(twitter_url_5)
                Glide.with(view.context.getApplicationContext())
                    .load(data.get(position).twitter?.get(0)?.media_twitter?.get(0)?.url_6)
                    .into(twitter_url_6)

return view
    }


}

