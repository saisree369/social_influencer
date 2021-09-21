package com.practice.socialinfluencerr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class CampaignListView : AppCompatActivity() {

    private var listData: List<Campaign>? = null
    private var adapter: CampaignAdapter? = null

    private var DATABASE_POST_UPLOADS = "users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign_list_view)




        var recyclerView1 = findViewById(R.id.recyclerView_campaign) as RecyclerView;
        recyclerView1.layoutManager = GridLayoutManager(this, 1)
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(LinearLayoutManager(this));
        listData = ArrayList()

        var arrow_back = findViewById(R.id.menu_specific) as ImageView;
        arrow_back.setOnClickListener {
            finish()
        }


        val pm = FirebaseDatabase.getInstance().reference
       val submainref: DatabaseReference = pm.child(DATABASE_POST_UPLOADS)
       // val lastQuery_1 = pm.child(USERS).orderByKey().limitToLast(1)
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
                    adapter = CampaignAdapter(applicationContext, listData as ArrayList<Campaign>)
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
    }

}
