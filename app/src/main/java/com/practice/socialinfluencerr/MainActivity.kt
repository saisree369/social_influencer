package com.practice.socialinfluencerr

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase.getInstance
import com.google.firebase.database.ValueEventListener
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private var listData: List<Post>? = null
    private var adapter: MainAdapter? = null
    var DATABASE_POST_UPLOADS = "/influencer_users"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var image_pic = findViewById(R.id.user_logo) as ImageView;
        image_pic.setOnClickListener {
        val intent = Intent(this, SpecificLoginActivity::class.java)
        startActivity(intent);
        }

//        var camera_pic = findViewById(R.id.info_logo) as ImageView;
//        camera_pic.setOnClickListener {
//            val intent = Intent(this, SampleCameraScreen::class.java)
//            startActivity(intent);
//        }



        val pm = getInstance().reference
        val submainref_1: DatabaseReference = pm.child(DATABASE_POST_UPLOADS)
        submainref_1.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {

                    val postKey = postSnapshot.key as String
                    var DATABASE_POST_UPLOADS_1 = "/influencer_users/${postKey}/posts"

                    val pDataBase = getInstance().reference
                    val subref : DatabaseReference = pDataBase.child(DATABASE_POST_UPLOADS_1)

                    subref.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            for (postSnapshot in dataSnapshot.children) {
                                val postKey_1 = postSnapshot.key as String
                                val views = postSnapshot.child("views").getValue(String::class.java) as String
                                var views_count = views.toInt() + 1
                                pDataBase.child("influencer_users/${postKey}/posts/${postKey_1}/views").setValue("${views_count}");
                            }
                        }
                        override fun onCancelled(databaseError: DatabaseError) {}
                    })
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

        var recyclerView1 = findViewById(R.id.recyclerView_main1) as RecyclerView;
        recyclerView1.layoutManager = GridLayoutManager(this, 1)
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(LinearLayoutManager(this));
        listData = ArrayList()

        val pm1 = getInstance().reference
        val submainref: DatabaseReference = pm1.child(DATABASE_POST_UPLOADS)
        submainref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {

                    val postKey = postSnapshot.key as String
                    var DATABASE_POST_UPLOADS_1 = "/influencer_users/${postKey}/posts"
                    val nm = getInstance().getReference(DATABASE_POST_UPLOADS_1)
                    nm.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (npsnapshot in dataSnapshot.children) {
                                    val l= npsnapshot.getValue(Post::class.java)
                                    l?.let { (listData as ArrayList<Post>).add(it) }

                                }
                                //adapter = MainAdapter(applicationContext, listData as ArrayList<Post>)
                                // recyclerView1.setAdapter(adapter)
                                adapter = MainAdapter(applicationContext, listData as ArrayList<Post>)
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
