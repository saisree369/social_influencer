package com.practice.socialinfluencerr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.practice.socialinfluencerr.CardAdapter.Companion.MAX_ELEVATION_FACTOR

import java.util.ArrayList

class CardPagerAdapter : PagerAdapter(), CardAdapter {

    private val mViews: MutableList<CardView?>
    private val mData: MutableList<CardItem>
    private var mBaseElevation: Float = 0.toFloat()

    init {
        mData = ArrayList()
        mViews = ArrayList()
    }

    fun addCardItem(item: CardItem) {
        mViews.add(null)
        mData.add(item)
    }

    override fun getBaseElevation(): Float {
        return mBaseElevation
    }

    override fun getCardViewAt(position: Int): CardView {
        return mViews[position]!!
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.fragment_adapter, container, false)
        container.addView(view)
        bind(mData[position], view)
        val cardView = view.findViewById(R.id.cardView1) as CardView

        if (mBaseElevation == 0f) {
            mBaseElevation = cardView.cardElevation
        }

        cardView.maxCardElevation = mBaseElevation * MAX_ELEVATION_FACTOR
        mViews[position] = cardView
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        mViews.set(position, null)
    }

    private fun bind(item: CardItem, view: View) {
        val titleTextView = view.findViewById(R.id.tv1) as TextView
        val contentTextView = view.findViewById(R.id.tv2) as TextView
        val cardViewImage= view.findViewById(R.id.image1) as ImageView

        titleTextView.setText(item.getTitle())
        contentTextView.setText(item.getText())
        cardViewImage.setBackgroundResource(item.getImage())
    }

}