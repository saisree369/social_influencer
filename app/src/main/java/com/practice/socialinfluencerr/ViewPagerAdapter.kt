package com.practice.socialinfluencerr

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ViewPagerAdapter(private val context: Context) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private val images = arrayOf<Int>(R.drawable.similar_1, R.drawable.similar_2, R.drawable.similar_3)

    var colors =
        arrayOf("#D4F4EC","#F3DCD4","#D7ECD9")
    var text_colors =
        arrayOf("#D7ECD9","#D4F4EC","#F3DCD4")
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.view_pager, null)
        val imageView = view.findViewById(R.id.imageView) as ImageView
        val textView = view.findViewById(R.id.textView) as TextView
 //       val linearview =view.findViewById(R.id.linearView)  as LinearLayout
        val textView2 =view.findViewById(R.id.text2) as TextView
        imageView.setImageResource(images[position])
        textView.setText("Top Rated Advertisements")
 //       linearview.setBackgroundColor(Color.parseColor(colors[position]));



        val vp = container as ViewPager
        vp.addView(view, 0)
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)

    }
}
