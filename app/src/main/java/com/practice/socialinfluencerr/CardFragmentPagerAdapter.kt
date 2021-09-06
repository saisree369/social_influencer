package com.practice.socialinfluencerr

import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


import java.util.ArrayList

class CardFragmentPagerAdapter(fm: FragmentManager?, private val mBaseElevation: Float) : FragmentStatePagerAdapter(fm!!),
    CardAdapter {

    private val mFragments: MutableList<CardFragment>

    init {
        mFragments = ArrayList()

        for (i in 0..4) {
            addCardFragment(CardFragment())
        }
    }

    override fun getBaseElevation(): Float {
        return mBaseElevation
    }

    override fun getCardViewAt(position: Int): CardView? {
        return mFragments[position].cardView
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position)
        mFragments[position] = fragment as CardFragment
        return fragment
    }

    fun addCardFragment(fragment: CardFragment) {
        mFragments.add(fragment)
    }

}