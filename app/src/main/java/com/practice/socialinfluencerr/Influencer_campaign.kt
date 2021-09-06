package com.practice.socialinfluencerr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import android.content.Intent
import android.util.Log
import com.practice.socialinfluencerr.models.Influencers


class Influencer_campaign : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_influencer_campaign)
/*
        viewPager =findViewById(R.id.viewpager_main)
        tabs = findViewById(R.id.tabs_main)
        tabs!!.addTab(tabs!!.newTab().setText("influencer Info"))
        tabs!!.addTab(tabs!!.newTab().setText("My Campaigns"))
        tabs!!.addTab(tabs!!.newTab().setText("Apply Campaign"))
        tabs!!.tabGravity = TabLayout.GRAVITY_FILL


        val fragmentAdapter = MyPagerAdapter(this, supportFragmentManager, tabs!!.tabCount)
        viewPager!!.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        tabs!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
*/

        var arrow_back = findViewById(R.id.menu_specific) as ImageView;
        arrow_back.setOnClickListener {
            finish()
        }
        viewPager = findViewById(R.id.viewpager_main)
        tabs = findViewById(R.id.tabs_main)
        var data = ExploreInfluencerAdapter.getData()
        Log.e("data", "${data}")

//        val items: List<Influencers>
//        val i = intent
//        items = i.getSerializableExtra("LIST") as List<Influencers>

 //     val fragmentAdapter = MyPagerAdapter(supportFragmentManager,items,this)


      val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)

    }
}
