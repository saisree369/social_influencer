package com.practice.socialinfluencerr

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.*

class Influencer_Amount :AppCompatActivity(){

    private var listData: List<Campaign>? = null
    private var adapter: AmountAdapter? = null
    var DATABASE_POST_UPLOADS = "users"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amount)

        var recyclerView1 = findViewById(R.id.recyclerView_amount) as RecyclerView;
        recyclerView1.layoutManager = GridLayoutManager(this, 1)
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(LinearLayoutManager(this));
        listData = ArrayList()

        val pm1 = FirebaseDatabase.getInstance().reference
        val submainref: DatabaseReference = pm1.child(DATABASE_POST_UPLOADS)
        submainref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {

                    val postKey = postSnapshot.key as String
                    var DATABASE_POST_UPLOADS_1 = "/users/${postKey}/campaigns"

                    val nm = FirebaseDatabase.getInstance().getReference(DATABASE_POST_UPLOADS_1)
                    nm.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (npsnapshot in dataSnapshot.children) {
                                    val l= npsnapshot.getValue(Campaign::class.java)
                                    l?.let { (listData as ArrayList<Campaign>).add(it) }

                                }
                                //adapter = MainAdapter(applicationContext, listData as ArrayList<Post>)
                                // recyclerView1.setAdapter(adapter)
                                adapter = AmountAdapter(applicationContext, listData as ArrayList<Campaign>)
                                recyclerView1.adapter = adapter

                            }
                        }
                        override fun onCancelled(databaseError: DatabaseError) {

                        }
                    })
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}

        })

        var arrow_back = findViewById(R.id.menu_specific) as ImageView;
        arrow_back.setOnClickListener {
            finish()
        }
    }
}