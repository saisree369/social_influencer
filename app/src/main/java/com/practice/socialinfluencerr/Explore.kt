package com.practice.socialinfluencerr

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import com.yuyakaido.android.cardstackview.*
import android.app.SearchManager
import android.os.Handler
import android.view.*
import android.widget.AdapterView


import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.practice.socialinfluencerr.models.Example1


class Explore : Fragment(), CardStackListener,ExampleInterface,AdapterView.OnItemSelectedListener {

    var jsonData: Example1? = null;
    var dataFetched: Boolean = false

    //private val JSON_URL = "https://www.json-generator.com/api/json/get/camUlXiDGW?indent=2"
    private val JSON_URL ="https://mocki.io/v1/db922c5b-ac06-4b86-9656-9a47f3e53e64"
//    private val adapter = ProfilesAdapter()
    private lateinit var layoutManager: CardStackLayoutManager

    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null
    var position: Int? = null
    var HorizontalLayout: LinearLayoutManager? = null

    val dataList = ArrayList<Category>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

/*        Fresco.initialize(view.context)
        layoutManager = CardStackLayoutManager(view.context, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }
        val stack_view = view.findViewById<CardStackView>(R.id.stack_view)
        stack_view.layoutManager = layoutManager
        stack_view.adapter = adapter
        stack_view.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

        InfluencerApi().getProfiles().enqueue(object : Callback<List<Inf_Profile>> {
            override fun onFailure(call: Call<List<Inf_Profile>>, t: Throwable) {

            }
            override fun onResponse(call: Call<List<Inf_Profile>>, response: Response<List<Inf_Profile>>) {
                response.body()?.let {
                    adapter.setProfiles(it)
        //            Log.e("Dataaa","${response.body()}")
                }
            }
        })

*/


        getResponse(JSON_URL, this)


        Handler().postDelayed(
            {
                HorizontalLayout = LinearLayoutManager(
                    view.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                var recyclerView_explore_influencer=view.findViewById(R.id.recyclerView_explore_influencer) as RecyclerView
                recyclerView_explore_influencer.layoutManager = GridLayoutManager(view.context, 1)

                var exploreInfluencerAdapter = jsonData?.data?.influencer_menu?.get(0)?.influencers?.let { ExploreInfluencerAdapter(view.context, it) }
                recyclerView_explore_influencer.adapter = exploreInfluencerAdapter



                var recyclerView_category = view.findViewById(R.id.recyclerView_category) as RecyclerView;

                recyclerView_category.layoutManager = GridLayoutManager(view.context, 1)
                val categoryAdapter = jsonData?.data?.influencer_menu?.let { CategoryImageAdapter(view.context, it,recyclerView_explore_influencer) }
                recyclerView_category.setLayoutManager(HorizontalLayout);
                recyclerView_category.adapter = categoryAdapter

            },
            2000 // value in milliseconds
        )
        return view
    }



    fun getResponse(JSON_URL: String, callback: ExampleInterface) {
        val stringRequest = StringRequest(
            Request.Method.GET, JSON_URL,
            com.android.volley.Response.Listener { response -> callback.onSuccessResponse(response) },
            com.android.volley.Response.ErrorListener { error ->
                error.printStackTrace()

                Log.e("response..", "...error")
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }


    override fun onSuccessResponse(response: String) {
        val gson = Gson()
        jsonData = gson.fromJson(response, Example1::class.java!!)
        dataFetched = true
        Log.e("Dataaa Fetched","${jsonData}")
        //   Toast.makeText(view!.context,"Fetched...",Toast.LENGTH_LONG).show()
    }

    override fun onFailure(error: String) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }


    override fun onCardDisappeared(view: View?, position: Int) {

    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardRewound() {
    }

}
