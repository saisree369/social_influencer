package com.practice.socialinfluencerr

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class Inf_Followers: AppCompatActivity() , AdapterView.OnItemSelectedListener,ExampleInterface{
    var jsonData: Example? = null;
    var dataFetched: Boolean = false
   // private val JSON_URL = "https://www.json-generator.com/api/json/get/bTqYvSyVhK?indent=2"
   private val JSON_URL = "https://mocki.io/v1/ba755a3d-432f-4cd8-b993-cd5e642aee70"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inf_followers)

             Log.e("Hai1", "hello1")
             getResponse(JSON_URL, this)
             Log.e("Hai2..", "hello2")
             Handler().postDelayed(
                     {
                         Log.e("Hai3..", "hello3")

                         var recyclerView1 = findViewById(R.id.recyclerView_main) as RecyclerView;
                         recyclerView1.layoutManager = GridLayoutManager(this, 1)
                         val imageAdapter = jsonData?.results?.content?.let { InfluencerFollowersAdapter(this, it) }

                         recyclerView1.adapter = imageAdapter


                     },
                     2000 // value in milliseconds
             )
        var arrow_back = findViewById(R.id.menu_specific) as ImageView;
        arrow_back.setOnClickListener {
            finish()
        }

    }

    fun getResponse(JSON_URL: String, callback: ExampleInterface) {
        val stringRequest = StringRequest(
                Request.Method.GET, JSON_URL,
                Response.Listener { response -> callback.onSuccessResponse(response) },
                Response.ErrorListener { error ->
                    error.printStackTrace()
                    Log.e("response..", "...error")
                })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
    override fun onSuccessResponse(response: String) {
        val gson = Gson()
        jsonData = gson.fromJson(response, Example ::class.java!!)
        dataFetched = true
        //   Toast.makeText(view!.context,"Fetched...",Toast.LENGTH_LONG).show()
    }

    override fun onFailure(error: String) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }
}