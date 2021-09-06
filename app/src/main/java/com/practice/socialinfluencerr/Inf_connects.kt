package com.practice.socialinfluencerr

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException

class Inf_connects : AppCompatActivity() {
    val dataList = ArrayList<Connect>()
    var HorizontalLayout: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connects)
        var recyclerView = findViewById(R.id.recyclerView_main) as RecyclerView;
        recyclerView.layoutManager = GridLayoutManager(this, 1);

        dataList.add(Connect(R.drawable.monarch, "Monarch"))
        dataList.add(Connect(R.drawable.mosaic, "Mosaic"))
        dataList.add(Connect(R.drawable.subway, "Subway"))
        dataList.add(Connect(R.drawable.corporate, "Corporate"))
        dataList.add(Connect(R.drawable.castle, "Castle"))
        dataList.add(Connect(R.drawable.iconic, "Iconic"))
        dataList.add(Connect(R.drawable.volvo, "Volvo"))
        dataList.add(Connect(R.drawable.express, "Express"))
        dataList.add(Connect(R.drawable.kfc, "KFC"))
        dataList.add(Connect(R.drawable.apple, "Apple"))
        dataList.add(Connect(R.drawable.hsbc, "HSBC"))
        dataList.add(Connect(R.drawable.uber, "Uber"))
        dataList.add(Connect(R.drawable.burgerking, "BurgerKing"))
        dataList.add(Connect(R.drawable.pepsi, "Pepsi"))
        dataList.add(Connect(R.drawable.hsbc, "HSBC"))


//        pass the values to RvAdapter
        val imageAdapter = ConnectImageAdapter(dataList)
//        set the recyclerView to the adapter
        HorizontalLayout = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.adapter = imageAdapter;

        var arrow_back = findViewById(R.id.menu_specific) as ImageView;
        arrow_back.setOnClickListener {
            finish()
        }

    }
}