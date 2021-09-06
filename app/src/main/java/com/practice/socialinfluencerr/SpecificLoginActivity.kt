package com.practice.socialinfluencerr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_specific_login.*


class SpecificLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_login)

        var arrow_back = findViewById(R.id.menu_specific) as ImageView;
        arrow_back.setOnClickListener {
            finish()
        }

        adv_signIn.setOnClickListener {
            val intent = Intent(this, AdvertiserLoginActivity::class.java)
            startActivity(intent);
        }

        inf_signIn.setOnClickListener {
            val intent = Intent(this, InfluencerLoginActivity::class.java)
            startActivity(intent);
        }
    }
}
