package com.practice.socialinfluencerr

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import androidx.databinding.adapters.TextViewBindingAdapter.setText
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.gms.common.internal.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.post_view.*


class SampleInfluencer : AppCompatActivity() {

    private var display_username: TextView? = null
    private var adv_gender: TextView? = null
    private var adv_place: TextView? = null
    private var adv_mobile: TextView? = null
    private var user_profile: ImageView? = null
    private var USERS = "users"
    private var posts:Post?=null
    private lateinit var mAuth: FirebaseAuth


    private var brand_new : String?=null
    private var category_selected_new : String? = null
    private var product_description_new: String? = null
    private var likes:String?  = null
    private var views:String?  = null


    private var url : String? = null
    private var database: FirebaseDatabase? = null


    private var pDatabase : DatabaseReference?= null

    private var POSTS = "posts"
    var DATABASE_POST_UPLOADS = "/influencer_users"

    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_influencer)



        var accept = findViewById<ImageView>(R.id.accept)
        var reject = findViewById<ImageView>(R.id.reject)
        var post_button = findViewById<Button>(R.id.buttonApply)

        var category = findViewById<TextView>(R.id.select_category_name)
        var brand =findViewById<TextView>(R.id.select_brand_name)
        var price =findViewById<TextView>(R.id.inf_insta_price)
        var reward =findViewById<TextView>(R.id.inf_inst_post)
        var project_description =findViewById<TextView>(R.id.textView_desc)
        var image=findViewById<ImageView>(R.id.related_img)

        var category_d = getIntent().getStringExtra("category") as? String
        var brand_d = getIntent().getStringExtra("brand")
        var price_d = getIntent().getStringExtra("price")
        var reward_d = getIntent().getStringExtra("reward")
        var product_description_d = getIntent().getStringExtra("product_description") as? String
        var related_url = getIntent().getStringExtra("url") as? String

        var infl_image=getIntent().getStringExtra("inf_url") as? String
        var infl_name=getIntent().getStringExtra("inf_name") as? String



        category.setText(category_d)
        brand.setText(brand_d)
        price.setText(price_d).toString()
        reward.setText(reward_d).toString()
        project_description.setText(product_description_d).toString()


        Glide.with(getApplicationContext())
            .load(related_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);

        accept.setOnClickListener {
            post_button.setBackgroundColor(Color.parseColor("#228c22"))
        }
        reject.setOnClickListener {
            post_button.setBackgroundColor(Color.parseColor("#fa8072"))
        }
        val rootRef = FirebaseDatabase.getInstance().reference
        val userRef = rootRef.child(USERS)
        userRef.key?.let { Log.v("USERID", it) }
        val lastQuery = rootRef.child(USERS).orderByKey().limitToLast(1)
        display_username = findViewById(R.id.inf_name) as TextView;
        user_profile = findViewById(R.id.inf_pic);

        adv_gender = findViewById<TextView>(R.id.inf_gender)
        adv_place = findViewById<TextView>(R.id.inf_place)
        adv_mobile = findViewById<TextView>(R.id.inf_mobile)


        // Read from the database
        lastQuery.addValueEventListener(object : ValueEventListener {
            internal var display_name: String? = null
            internal var gender: String? = null
            internal var place: String? = null
            internal var mobile: String? = null



            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (keyId in dataSnapshot.children) {

                    display_name = keyId.child("fullName").getValue(String::class.java)
                    gender =keyId.child("gender").getValue(String::class.java)
                    place = keyId.child("workplace").getValue(String::class.java)
                    mobile =keyId.child("phone").getValue(String::class.java)


                        Glide.with(getApplicationContext())
                            .load(keyId.child("url").getValue(String::class.java))
                            .into(user_profile);
                        break

                }
                display_username!!.setText(display_name)
                adv_gender!!.setText(gender)
                adv_place!!.setText(place)
                adv_mobile!!.setText(mobile)
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                //              Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
            }
        })


        val mDatabase = FirebaseDatabase.getInstance().reference
     //   val submainref: DatabaseReference = mDatabase.child(DATABASE_POST_UPLOADS)
       val lastQuery_1 = mDatabase.child(DATABASE_POST_UPLOADS).orderByKey().limitToLast(1)
        lastQuery_1.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {

                    val postKey = postSnapshot.key as String
                    var DATABASE_PATH_UPLOADS_1 = "/influencer_users/${postKey}/posts"



          //          pDatabase = FirebaseDatabase.getInstance().getReference(DATABASE_PATH_UPLOADS_1);

        pDatabase = FirebaseDatabase.getInstance().getReference(DATABASE_PATH_UPLOADS_1);
        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        var arrow_back = findViewById(R.id.menu) as ImageView;
        arrow_back.setOnClickListener {
            finish()
        }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}

        })


        post_button.setOnClickListener{
                category_selected_new=category.getText().toString()
                brand_new = brand.getText().toString();
                product_description_new = project_description.getText().toString();
                likes = "0"
                views = "0"

                val keyid = pDatabase!!.push().key
         //   Log.v("keyid", keyid)
                posts = Post(category_selected_new!!,brand_new!!,product_description_new!!,related_url!!,likes!!,views!!);
                if (keyid != null) {
                    pDatabase!!.child(keyid).setValue(posts)
                };



                val intent = Intent(this, PostPage::class.java)
                intent.putExtra("inf_url",infl_image)
                intent.putExtra("inf_name",infl_name);

                intent.putExtra("brand_name",brand_new)
                intent.putExtra("category_name",category_selected_new)
                intent.putExtra("product_description",product_description_new)
                intent.putExtra("likes",likes)
                intent.putExtra("views",views)
                intent.putExtra("url",related_url)

                startActivity(intent);
            }

        }

}
