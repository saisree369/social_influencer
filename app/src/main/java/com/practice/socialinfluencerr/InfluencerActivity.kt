package com.practice.socialinfluencerr

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson
import com.practice.socialinfluencerr.models.Example1
import com.google.android.material.navigation.NavigationView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import com.practice.socialinfluencerr.R.drawable.menu_icon
import androidx.drawerlayout.widget.DrawerLayout
import java.io.IOException


private var display_username: TextView? = null
private var user_profile: ImageView? = null

private var content: FrameLayout? = null

//var DATABASE_PATH_UPLOADS = "/users/-MKA_GDzrNimqVXpcv2N/campaigns"
var DATABASE_PATH_UPLOADS = "/users/";




private var pDatabase : DatabaseReference?= null

private var POSTS = "/influencer_users"



var viewPager: ViewPager? = null


class InfluencerActivity : AppCompatActivity(),ExampleInterface,NavigationView.OnNavigationItemSelectedListener {

    var jsonData: Example1? = null;
    var dataFetched: Boolean = false

    private val JSON_URL = "https://www.json-generator.com/api/json/get/camUlXiDGW?indent=2"

    private var display_username: TextView? = null
    private var user_profile: ImageView? = null

    private var TAG = this.javaClass.name.toUpperCase()
    private var database: FirebaseDatabase? = null
    private var mDatabase: DatabaseReference? = null
    private var userMap: Map<String, String>? = null
    private var email: String? = null
    private var userid: String? = null
    private var USERS = "influencer_users"



    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, InfluencerActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_influencer)


        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val menuIcon = findViewById(R.id.menu_icon) as ImageView

        menuIcon.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                drawer.openDrawer(Gravity.LEFT)
            }
        })
        val navigationView = findViewById<NavigationView>(R.id.nav_view)



        navigationView.setNavigationItemSelectedListener(this)
        val intent = intent
        email = intent.getStringExtra("email")

        getResponse(JSON_URL, this)

        Handler().postDelayed(
            {

                var inf_followers = findViewById(R.id.textView_followers) as TextView
                var inf_connects = findViewById(R.id.textView_connects) as TextView


                inf_followers?.text = jsonData?.data?.influencer_menu?.get(0)?.influencers?.get(0)?.instagram?.get(0)?.followers.toString()
                inf_connects?.text = jsonData?.data?.influencer_menu?.get(5)?.influencers?.get(6)?.connects.toString()
                // get menu from navigationView
                var menu = navigationView.getMenu();

                // find MenuItem you want to change
                var nav_connects_count = menu.findItem(R.id.nav_connects_count);
                nav_connects_count.setTitle(jsonData?.data?.influencer_menu?.get(5)?.influencers?.get(6)?.connects.toString());


                // find MenuItem you want to change
                var nav_followers_count = menu.findItem(R.id.nav_followers_count);

                // set new title to the MenuItem
                nav_followers_count.setTitle(jsonData?.data?.influencer_menu?.get(0)?.influencers?.get(0)?.instagram?.get(0)?.followers.toString());


            },
            2000 // value in milliseconds
        )


        val mDatabase_1 = FirebaseDatabase.getInstance().reference
        val subref_2 : DatabaseReference  = mDatabase_1.child(POSTS)
        //      val lastQuery = pDatabase_1.child(DATABASE_PATH_UPLOADS).orderByKey().limitToLast(1)
        subref_2.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {

                    val postKey = postSnapshot.key as String
                    var DATABASE_PATH_UPLOADS_1 = "/influencer_users/${postKey}/posts"


                    val pDataBase = FirebaseDatabase.getInstance().reference
        val subref : DatabaseReference  = pDataBase.child(DATABASE_PATH_UPLOADS_1)
        subref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot_1 in dataSnapshot.children) {

               //     val postKey_1 = postSnapshot_1.key as String
                    // var posts_count = postKey_1.count().toString()

                    var posts_count = postSnapshot_1.getChildrenCount().toString()


                    var inf_posts = findViewById(R.id.textView_posts) as TextView
                    inf_posts.text = posts_count

                    var menu = navigationView.getMenu();

                    // find MenuItem you want to change
                    var nav_posts_count = menu.findItem(R.id.nav_posts_count);
                    nav_posts_count.setTitle(posts_count);

                    //                  val likes = postSnapshot.child("likes").getValue(String::class.java) as String
                    //                 var like_count = likes.toInt() + 1

                    //                 pDataBase.child("posts/${postKey}/likes").setValue("${like_count}");

                }

            }
            override fun onCancelled(databaseError: DatabaseError) {}

        })

  }
}
        override fun onCancelled(databaseError: DatabaseError) {}

})


        val pDatabase_1 = FirebaseDatabase.getInstance().reference
        val subref_1 : DatabaseReference  = pDatabase_1.child(DATABASE_PATH_UPLOADS)
  //      val lastQuery = pDatabase_1.child(DATABASE_PATH_UPLOADS).orderByKey().limitToLast(1)
        subref_1.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {

                    val postKey = postSnapshot.key as String
                    var DATABASE_PATH_UPLOADS_1 = "/users/${postKey}/campaigns"


//        mDatabase = FirebaseDatabase.getInstance().getReference(DATABASE_PATH_UPLOADS_1);


                    val mDataBase = FirebaseDatabase.getInstance().reference
                    val submainref: DatabaseReference = mDataBase.child(DATABASE_PATH_UPLOADS_1)
                    submainref.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            for (postSnapshot_2 in dataSnapshot.children) {
                                var campaigns_count = postSnapshot_2.getChildrenCount().toString()
                            //    val postKey_2 = postSnapshot_2.key as String
                              //  var campaigns_count = postKey_2.count().toString()

                                var inf_campaigns = findViewById(R.id.textView_requests) as TextView
                                inf_campaigns.text = campaigns_count

                                var menu = navigationView.getMenu();

                                // find MenuItem you want to change
                                var nav_campaigns_count = menu.findItem(R.id.nav_campaigns_count);
                                nav_campaigns_count.setTitle(campaigns_count);

                            }

                        }

                        override fun onCancelled(databaseError: DatabaseError) {}

                    })

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}

        })



        var category_d = intent.getStringExtra("category") as? String
        var brand_d = intent.getStringExtra("brand")
        var price_d = intent.getStringExtra("price")
        var reward_d = intent.getStringExtra("reward")
        var product_description_d = intent.getStringExtra("product_description") as? String
        var related_url = intent.getStringExtra("url") as? String

        var infl_image=intent.getStringExtra("inf_url") as? String
        var infl_name=intent.getStringExtra("inf_name") as? String

        val rootRef = FirebaseDatabase.getInstance().reference
        val userRef = rootRef.child(USERS)
        userRef.key?.let { Log.v("USERID", it) }

        display_username = findViewById(R.id.name) as TextView;
        user_profile = findViewById(R.id.adv_logo);


        // Read from the database
        userRef.addValueEventListener(object : ValueEventListener {
            internal var display_name: String? = null
            var pro_pic: ImageView? = null

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (keyId in dataSnapshot.children) {
                    if (keyId.child("email").value == email) {
                        display_name = keyId.child("fullName").getValue(String::class.java)
                        Glide.with(getApplicationContext())
                            .load(keyId.child("url").getValue(String::class.java))
                            .into(user_profile);
                        break
                    }
                }
                display_username!!.setText(display_name)
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                //              Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
            }
        })
        initializeUI()

        var notification_icon = findViewById(R.id.notification_logo) as ImageView
        notification_icon.setOnClickListener {
            val intent = Intent(this, SampleInfluencer::class.java)
            intent.putExtra("category",category_d)
            intent.putExtra("brand",brand_d)
            intent.putExtra("price",price_d)
            intent.putExtra("reward",reward_d)
            intent.putExtra("product_description",product_description_d)
            intent.putExtra("url",related_url)

            intent.putExtra("inf_url",infl_image)
            intent.putExtra("inf_name",infl_name)
            startActivity(intent);
        }

        var button_campaigns = findViewById<Button>(R.id.inf_campaigns)
        button_campaigns.setOnClickListener{
            val intent = Intent(this, CampaignListView::class.java)
            startActivity(intent);
        }

        var button_posts = findViewById<Button>(R.id.inf_posts)
        button_posts.setOnClickListener{
            val intent = Intent(this, PostListView::class.java)
            startActivity(intent);
        }

        var button_followers = findViewById<Button>(R.id.inf_followers)
        button_followers.setOnClickListener{
            Log.e("Followers..", "followers")
            val intent = Intent(this, Inf_Followers::class.java)
            startActivity(intent);
        }

        var button_connects = findViewById<Button>(R.id.inf_connects)
        button_connects.setOnClickListener{
            Log.e("Connects..", "connects")
            val intent = Intent(this, Inf_connects::class.java)
            startActivity(intent);
        }

        var button_amount = findViewById<Button>(R.id.inf_money)
        button_amount.setOnClickListener{
            Log.e("Connects..", "connects")
            val intent = Intent(this, Influencer_Amount::class.java)
            startActivity(intent);
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
        jsonData = gson.fromJson(response, Example1::class.java!!)
        dataFetched = true
        Log.e("Dataaa Fetched","${jsonData}")
        //   Toast.makeText(view!.context,"Fetched...",Toast.LENGTH_LONG).show()
    }

    override fun onFailure(error: String) {

    }


    private fun initializeUI() {
        var sign_out_button = findViewById(R.id.logout) as ImageView
        sign_out_button.setOnClickListener {
            logout()
        }
    }

    private fun logout() {

        FirebaseAuth.getInstance().signOut();
        val intent = Intent(this, InfluencerLoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TOP
                or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        var drawer: DrawerLayout = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()


        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_campaigns) {
            // Handle the camera action
        }else if (id == R.id.nav_campaigns_count) {


        }else if (id == R.id.nav_posts) {

        }else if (id == R.id.nav_posts_count) {

        }else if (id == R.id.nav_followers) {

        }else if (id == R.id.nav_followers_count) {

        }else if (id == R.id.nav_connects) {

        }else if (id == R.id.nav_connects_count) {

        } else if (id == R.id.nav_platforms) {

        }else if (id == R.id.nav_platforms_count) {

        } else if (id == R.id.nav_amount) {

        }else if (id == R.id.nav_amount_count) {

        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}
