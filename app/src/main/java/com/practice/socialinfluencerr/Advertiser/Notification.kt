package com.practice.socialinfluencerr

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


private var posts:Post?=null
private lateinit var mAuth: FirebaseAuth
private var pDatabase : DatabaseReference?= null
private var storageReference: StorageReference? = null



class Notification : Fragment() {

    var likes_count_db:String? = "0"
    var views_count_db : String? ="0"

    private var POSTS = "influencer_users"
    private var url : String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_notification, container, false)

         var btnLike = view.findViewById<ImageView>(R.id.like)
        var btnSend = view.findViewById<ImageView>(R.id.send)
        var likes_count_1 = view.findViewById<TextView>(R.id.likes_count)
        var views_count_1 = view.findViewById<TextView>(R.id.views_count)

        var brand_image = view.findViewById<ImageView>(R.id.main_image)
        var category_name = view.findViewById<TextView>(R.id.category_name)
        var brand_name = view.findViewById<TextView>(R.id.brand_name)
        var product_desc=view.findViewById<TextView>(R.id.product_desc)


        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        val rootRef = FirebaseDatabase.getInstance().reference


        val mDatabase = FirebaseDatabase.getInstance().reference
        // val submainref: DatabaseReference = mDatabase.child(DATABASE_POST_UPLOADS)
        val lastQuery = mDatabase.child(POSTS).orderByKey().limitToLast(1)
        lastQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {

                    val postKey = postSnapshot.key as String
                    var DATABASE_POST_UPLOADS_1 = "/influencer_users/${postKey}/posts"


                    val lastQuery = rootRef.child(DATABASE_POST_UPLOADS_1).orderByKey().limitToLast(1)
                    // Read from the database
                    lastQuery.addValueEventListener(object : ValueEventListener {
                        internal var brand_name_d: String? = null
                        internal var category_name_d: String? = null
                        internal var product_d: String? = null
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            for (keyId in dataSnapshot.children) {

                                brand_name_d = keyId.child("brand").getValue(String::class.java)
                                category_name_d = keyId.child("category_selected").getValue(String::class.java)
                                product_d = keyId.child("product_desc").getValue(String::class.java)
                                likes_count_db = keyId.child("likes").getValue(String::class.java)
                                views_count_db = keyId.child("views").getValue(String::class.java)



                                Glide.with(view.context.getApplicationContext())
                                    .load(keyId.child("url").getValue(String::class.java))
                                    .into(brand_image);
                                break

                            }
                            category_name!!.setText(category_name_d)
                            brand_name!!.setText(brand_name_d)
                            product_desc!!.setText(product_d)
                            likes_count_1!!.setText(likes_count_db)
                            views_count_1!!.setText(views_count_db)


                        }

                        override fun onCancelled(error: DatabaseError) {
                            // Failed to read value
                            //              Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
                        }
                    })
                }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    //              Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
                }
            })


        return view
    }


}
