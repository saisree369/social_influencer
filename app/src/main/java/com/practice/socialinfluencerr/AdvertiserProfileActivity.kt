package com.practice.socialinfluencerr

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.TextView
import com.practice.socialinfluencerr.R.string.email
import android.content.Intent
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseError
import com.practice.socialinfluencerr.R.string.email
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener




class AdvertiserProfileActivity : AppCompatActivity() {

    private var occupationTxtView: TextView? = null
    private var nameTxtView: TextView? = null
    private var workTxtView: TextView? = null
    private var emailTxtView: TextView? = null
    private var phoneTxtView:TextView? = null
    private var genderTxtView:TextView? = null
    private var userImageView: ImageView? = null
    private var emailImageView: ImageView? = null
    private var phoneImageView: ImageView? = null
    private var genderImageView: ImageView? = null


    private var TAG = this.javaClass.name.toUpperCase()
    private var database: FirebaseDatabase? = null
    private var mDatabase: DatabaseReference? = null
    private var userMap: Map<String, String>? = null
    private var email: String? = null
    private var user_profile:ImageView? = null
    private var userid: String? = null
    private var USERS = "users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertiser_profile)
        //receive data from advertiser screen
        val intent = intent
        email = intent.getStringExtra("email")

        val rootRef = FirebaseDatabase.getInstance().reference
        val userRef = rootRef.child(USERS)
        Log.v("USERID", "Stringg : ${userRef.key}")

        occupationTxtView = findViewById(R.id.occupation_textview) as TextView;
        nameTxtView = findViewById(R.id.name_textview);
        workTxtView = findViewById(R.id.workplace_textview);
        emailTxtView = findViewById(R.id.email_textview);
        phoneTxtView = findViewById(R.id.phone_textview);
        genderTxtView = findViewById(R.id.gender_textview);

        userImageView = findViewById(R.id.user_imageview);
        emailImageView = findViewById(R.id.email_imageview);
        phoneImageView = findViewById(R.id.phone_imageview);
        genderImageView = findViewById(R.id.gender_imageview);
        user_profile    = findViewById(R.id.user_imageview);



        // Read from the database
        userRef.addValueEventListener(object : ValueEventListener {
            internal var fname: String? = null
            internal var gender:String? = null
            internal var mail: String? = null
            internal var profession: String? = null
            internal var workplace: String? = null
            internal var phone: String? = null

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (keyId in dataSnapshot.children) {
                    if (keyId.child("email").value == email) {

                        fname = keyId.child("fullName").getValue(String::class.java)


                        gender = keyId.child("gender").getValue(String::class.java)
                        profession = keyId.child("profession").getValue(String::class.java)
                        workplace = keyId.child("workplace").getValue(String::class.java)
                        phone = keyId.child("phone").getValue(String::class.java)
                        Glide.with(this@AdvertiserProfileActivity)
                            .load(keyId.child("url").getValue(String::class.java))
                            .into(user_profile);
                        break
                    }
                }
                nameTxtView!!.setText(fname)
                emailTxtView!!.setText(email)
                occupationTxtView!!.setText(profession)
                workTxtView!!.setText(workplace)
                phoneTxtView!!.setText(phone)
                genderTxtView!!.setText(gender)

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                //              Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
            }
        })


    }
}
