package com.practice.socialinfluencerr

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.practice.socialinfluencerr.models.Influencers
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable


//class MyPagerAdapter(var fm: FragmentManager,var items: List<Influencers>,val context: Context) : FragmentPagerAdapter(fm) {

class MyPagerAdapter(var fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {

//              val bundle = Bundle()
//                bundle.putSerializable("items", items as Serializable)
//                val fragInfo = CampaignDetails()
//                fragInfo.setArguments(bundle)
//                fm.beginTransaction().replace(R.id.add, fragInfo)
//                fm.beginTransaction().commit()


                 CampaignDetails()

            }
            1 -> {
                InfluencerInfo()
            }
            else -> {
                return ApplyCampaign()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "My Campaigns"
            1 -> "Inf Info"
            else -> {
                return "Apply"
            }
        }
    }
}