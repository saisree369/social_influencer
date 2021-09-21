package com.practice.socialinfluencerr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class PostListView : AppCompatActivity() {

    private var listData: List<Post>? = null
    private var adapter: MainAdapter? = null
    var DATABASE_POST_UPLOADS = "/influencer_users"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view)


//        val pm = FirebaseDatabase.getInstance().reference
//        val submainref_1: DatabaseReference = pm.child(DATABASE_POST_UPLOADS)
//        //val lastQuery_1 = pm.child(DATABASE_POST_UPLOADS).orderByKey().limitToLast(1)
//        submainref_1.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (postSnapshot in dataSnapshot.children) {
//
//                    val postKey = postSnapshot.key as String
//                    var DATABASE_POST_UPLOADS_1 = "/influencer_users/${postKey}/posts"
//
//        val pDataBase = FirebaseDatabase.getInstance().reference
//        val subref : DatabaseReference = pDataBase.child(DATABASE_POST_UPLOADS_1)
//        // val lastQuery = pDataBase?.child(POSTS).orderByKey().limitToLast(1)
//        subref.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (postSnapshot in dataSnapshot.children) {
//
//                    val postKey_1 = postSnapshot.key as String
//                    //       val likes = postSnapshot.child("likes").getValue(String::class.java) as String
//                    val views = postSnapshot.child("views").getValue(String::class.java) as String
//                    var views_count = views.toInt() + 1
//                    pDataBase.child("influencer_users/${postKey}/posts/${postKey_1}/views").setValue("${views_count}");
//
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {}
//
//        })
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {}
//
//        })

        var recyclerView1 = findViewById(R.id.recyclerView_main) as RecyclerView;
        recyclerView1.layoutManager = GridLayoutManager(this, 1)
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(LinearLayoutManager(this));
        listData = ArrayList()

        var arrow_back = findViewById(R.id.menu_specific) as ImageView;
        arrow_back.setOnClickListener {
            finish()
        }

        val pm1 = FirebaseDatabase.getInstance().reference
        val submainref: DatabaseReference = pm1.child(DATABASE_POST_UPLOADS)
        //    val lastQuery = pm1.child(DATABASE_POST_UPLOADS).orderByKey().limitToLast(1)
        submainref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {

                    val postKey = postSnapshot.key as String
                    var DATABASE_POST_UPLOADS_1 = "/influencer_users/${postKey}/posts"


                    val nm = FirebaseDatabase.getInstance().getReference(DATABASE_POST_UPLOADS_1)
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
