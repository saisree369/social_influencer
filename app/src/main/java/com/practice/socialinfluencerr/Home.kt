package com.practice.socialinfluencerr

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson


class Home : Fragment(), AdapterView.OnItemSelectedListener,ExampleInterface {

    var jsonData: Example? = null;
    var dataFetched: Boolean = false
    private var arrayAdapter: ArrayAdapter<String>? = null

    //private val JSON_URL = "https://www.json-generator.com/api/json/get/cdXLjKgMJe?indent=2"

    private val JSON_URL = "https://mocki.io/v1/ba755a3d-432f-4cd8-b993-cd5e642aee70"

    private var mViewPager: ViewPager? = null
    private var mCardAdapter: CardPagerAdapter? = null
    private var mCardShadowTransformer: ShadowTransformer? = null
    private var mFragmentCardAdapter: CardFragmentPagerAdapter? = null
    private var mFragmentCardShadowTransformer: ShadowTransformer? = null

    var HorizontalLayout: LinearLayoutManager? = null

    val dataList = ArrayList<Brand>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        var mViewPager = view.findViewById(R.id.viewPager) as ViewPager

        val pagerAdapter = CardFragmentPagerAdapter(getFragmentManager(), dpToPixels(2, view.context))
        val fragmentCardShadowTransformer = ShadowTransformer(mViewPager, pagerAdapter)
        fragmentCardShadowTransformer.enableScaling(true)

        mCardAdapter = CardPagerAdapter()
        mCardAdapter!!.addCardItem(CardItem(R.string.title_1, R.string.text_1, R.drawable.add_1))
        mCardAdapter!!.addCardItem(CardItem(R.string.title_2, R.string.text_2, R.drawable.add_2))
        mCardAdapter!!.addCardItem(CardItem(R.string.title_3, R.string.text_3, R.drawable.add_3))
        mCardAdapter!!.addCardItem(CardItem(R.string.title_4, R.string.text_4, R.drawable.add_4))
        mFragmentCardAdapter = CardFragmentPagerAdapter(
            getFragmentManager(),
            dpToPixels(2, view.context)
        )

        mCardShadowTransformer = ShadowTransformer(mViewPager, mCardAdapter!!)
        mFragmentCardShadowTransformer = ShadowTransformer(mViewPager, mFragmentCardAdapter!!)

        mViewPager.adapter = mCardAdapter
        mViewPager.setPageTransformer(false, mCardShadowTransformer)

        mViewPager.setClipToPadding(false);
        mViewPager.setOffscreenPageLimit(10);


        var recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView;
        recyclerView.layoutManager = GridLayoutManager(view.context, 1);

        dataList.add(Brand(R.drawable.bmw, "BMW"))
        dataList.add(Brand(R.drawable.pepsi, "Pepsi"))
        dataList.add(Brand(R.drawable.kfc, "KFC"))
        dataList.add(Brand(R.drawable.apple, "Apple"))
        dataList.add(Brand(R.drawable.hsbc, "HSBC"))
        dataList.add(Brand(R.drawable.uber, "Uber"))
        dataList.add(Brand(R.drawable.burgerking, "BurgerKing"))
        dataList.add(Brand(R.drawable.bmw, "BMW"))
        dataList.add(Brand(R.drawable.pepsi, "Pepsi"))
        dataList.add(Brand(R.drawable.kfc, "KFC"))
        dataList.add(Brand(R.drawable.apple, "Apple"))
        dataList.add(Brand(R.drawable.hsbc, "HSBC"))
        dataList.add(Brand(R.drawable.uber, "Uber"))
        dataList.add(Brand(R.drawable.burgerking, "BurgerKing"))
        dataList.add(Brand(R.drawable.pepsi, "Pepsi"))
        dataList.add(Brand(R.drawable.kfc, "KFC"))
        dataList.add(Brand(R.drawable.apple, "Apple"))
        dataList.add(Brand(R.drawable.hsbc, "HSBC"))

//        pass the values to RvAdapter
        val imageAdapter = BrandImageAdapter(dataList)
//        set the recyclerView to the adapter
        HorizontalLayout = LinearLayoutManager(
            view.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.adapter = imageAdapter;



        getResponse(JSON_URL, this)

        Handler().postDelayed(
            {

                var recyclerView1 = view.findViewById(R.id.recyclerView1) as RecyclerView;
                recyclerView1.layoutManager = GridLayoutManager(view.context, 2)
                val imageAdapter = jsonData?.results?.content?.let { InfluencerAdapter(view.context, it) }
                recyclerView1.adapter = imageAdapter


            },
            2000 // value in milliseconds
        )


/*
        viewPager.adapter = pagerAdapter
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer)
        viewPager.offscreenPageLimit = 3*/

        return view

    }

    fun getResponse(JSON_URL: String, callback: ExampleInterface) {
        val stringRequest = StringRequest(
            Request.Method.GET, JSON_URL,
            Response.Listener { response -> callback.onSuccessResponse(response) },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Log.e("response..", "...error")
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }


    override fun onSuccessResponse(response: String) {
        val gson = Gson()
        jsonData = gson.fromJson(response, Example::class.java!!)
        dataFetched = true
     //   Toast.makeText(view!.context,"Fetched...",Toast.LENGTH_LONG).show()
    }

    override fun onFailure(error: String) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }


    fun dpToPixels(dp: Int, context: Context): Float {
        val displayMetrics = activity!!.resources.displayMetrics

        return dp * context.resources.displayMetrics.density
    }
}
