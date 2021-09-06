package com.practice.socialinfluencerr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

private lateinit var mAuth: FirebaseAuth

class AdvertiserActivity : AppCompatActivity() {
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu_home -> {
                val fragment = Home()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                val fragment = Explore()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.notification -> {
                val fragment = Notification()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                val fragment = Profile()
                addFragment(fragment)

                val intent = Intent(this, AdvertiserProfileActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent);
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    private var display_username: TextView? = null
    private var user_profile: ImageView? = null

    private var TAG = this.javaClass.name.toUpperCase()
    private var database: FirebaseDatabase? = null
    private var mDatabase: DatabaseReference? = null
    private var userMap: Map<String, String>? = null
    private var email: String? = null
    private var userid: String? = null
    private var USERS = "users"
    private var notify : Boolean = false

    private var content: FrameLayout? = null


    var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertiser)

    //receive data from login screen
    val intent = intent
     email = intent.getStringExtra("email")
        notify = intent.getBooleanExtra("notify",true)

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
                        Glide.with(this@AdvertiserActivity)
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

        var navigation = findViewById(R.id.navigation) as BottomNavigationView


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = Home()
        addFragment(fragment)
    }



    private fun initializeUI() {
        var sign_out_button = findViewById(R.id.logout) as ImageView
        sign_out_button.setOnClickListener {
            logout()
        }
    }



    private fun logout() {
        FirebaseAuth.getInstance().signOut();
        val intent = Intent(this, AdvertiserLoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TOP
                or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.navigation, menu)
        if(menu.findItem(R.id.home).isEnabled) {
            if(notify) {
                menu.findItem(R.id.notification).setIcon(R.drawable.bell_icon_red);
            }else{
                menu.findItem(R.id.notification).setIcon(R.drawable.notification);
            }
        }
        return true
    }



}


