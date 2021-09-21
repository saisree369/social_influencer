package com.practice.socialinfluencerr

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseError
import java.nio.file.Files.exists
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.post_view.*

var DATABASE_POST_UPLOADS = "/influencer_users"


private var posts:Post?=null
private lateinit var mAuth: FirebaseAuth
private var pDatabase : DatabaseReference?= null
private var storageReference: StorageReference? = null

lateinit var notificationManager : NotificationManager
lateinit var notificationChannel : NotificationChannel
lateinit var builder : NotificationCompat.Builder



private val channelId = "com.practice.socialinfluencer"
private val description = "Posted you request...PLease view"

class PostPage : AppCompatActivity() {

    var likes_count_db:String? = "0"
    var views_count_db:String? = "0"
    private var POSTS = "posts"
    private var url : String? = null
    internal var brand_name_d: String? = null
    internal var category_name_d: String? = null
    internal var product_d: String? = null
    private var notify : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_page)

        var btnLike = findViewById<ImageView>(R.id.like)
        var btnSend = findViewById<ImageView>(R.id.send)
        var likes_count_1 = findViewById<TextView>(R.id.likes_count)
        var views_count_1 = findViewById<TextView>(R.id.views_count)

        var brand_image = findViewById<ImageView>(R.id.main_image)
        var category_name = findViewById<TextView>(R.id.category_name)
        var brand_name = findViewById<TextView>(R.id.brand_name)
        var product_desc=findViewById<TextView>(R.id.product_desc)



        var influencer_name1 = findViewById<TextView>(R.id.influencer_name)
        var influencer_image=findViewById<ImageView>(R.id.influencer_pic)

        var infl_url=getIntent().getStringExtra("inf_url") as String
        var infl_name=getIntent().getStringExtra("inf_name") as String
        influencer_name1.setText(infl_name)
         Glide.with(getApplicationContext())
            .load(infl_url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(influencer_image);




        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        btnLike.setOnClickListener {
            //           btnLike.setBackgroundColor(Color.parseColor("#FF0000"))


            val mDatabase = FirebaseDatabase.getInstance().reference
           // val submainref: DatabaseReference = mDatabase.child(DATABASE_POST_UPLOADS)
           val lastQuery = mDatabase.child(DATABASE_POST_UPLOADS).orderByKey().limitToLast(1)
            lastQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshot in dataSnapshot.children) {

                        val postKey = postSnapshot.key as String
                        var DATABASE_POST_UPLOADS_1 = "/influencer_users/${postKey}/posts"



               //         pDatabase = FirebaseDatabase.getInstance().getReference(DATABASE_PATH_UPLOADS_1);


                        val pDataBase = FirebaseDatabase.getInstance().reference
                      //  val submainref_1: DatabaseReference = pDataBase.child(DATABASE_POST_UPLOADS_1)
                    val lastQuery = pDataBase.child(DATABASE_POST_UPLOADS_1).orderByKey().limitToLast(1)
                        lastQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (postSnapshot in dataSnapshot.children) {

                                    val postKey_1 = postSnapshot.key as String


                                    var likes = postSnapshot.child("likes").getValue(String::class.java) as String
                                    var like_count = likes.toInt() + 1
                                    Log.v("likes",likes)

                                    pDataBase.child("influencer_users/${postKey}/posts/${postKey_1}/likes").setValue("${like_count}");


                                }

                            }

                            override fun onCancelled(databaseError: DatabaseError) {}

                        })
                    }


                }

                override fun onCancelled(databaseError: DatabaseError) {}

            })
        }

        var arrow_back = findViewById(R.id.menu_specific) as ImageView;
        arrow_back.setOnClickListener {
            finish()
        }

        btnSend.setOnClickListener {

            val notificationManager= this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(
                    channelId,description, NotificationManager.IMPORTANCE_HIGH)

                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(true)
                notificationChannel.vibrationPattern = longArrayOf(1000, 2000)

                notificationManager.createNotificationChannel(notificationChannel)

                builder = NotificationCompat.Builder(this,channelId)

                    .setContentTitle("Posted you request...Please view")
   //                 .setContentText(brand.toString())
                    .setSmallIcon(R.drawable.ic_launcher_background)

                val intent = Intent(this,AdvertiserActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.putExtra("notify",true)

                val stackBuilder = TaskStackBuilder.create(this) as TaskStackBuilder
                stackBuilder.addParentStack(AdvertiserActivity::class.java)
                stackBuilder.addNextIntent(intent);


                val pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                notificationManager.notify(234, builder.build());
            }
        }



        val mDatabase = FirebaseDatabase.getInstance().reference
       // val submainref: DatabaseReference = mainrefernce.child(DATABASE_PATH_UPLOADS)
          val lastQuery = mDatabase.child(DATABASE_POST_UPLOADS).orderByKey().limitToLast(1)
        lastQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {

                    val postKey = postSnapshot.key as String
                    var DATABASE_PATH_UPLOADS_1 = "/influencer_users/${postKey}/posts"


                    val pDataBase = FirebaseDatabase.getInstance().reference
                  //  val subref: DatabaseReference = rootRef.child(DATABASE_PATH_UPLOADS_1)
                       val lastQuery = pDataBase.child(DATABASE_PATH_UPLOADS_1).orderByKey().limitToLast(1)
                    // Read from the database
                    lastQuery.addValueEventListener(object : ValueEventListener {
                        /*          internal var brand_name_d: String? = null
            internal var category_name_d: String? = null
            internal var product_d: String? = null*/
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            for (keyId in dataSnapshot.children) {

                                brand_name_d = keyId.child("brand").getValue(String::class.java)
                                category_name_d = keyId.child("category_selected").getValue(String::class.java)
                                product_d = keyId.child("product_desc").getValue(String::class.java)
                                likes_count_db = keyId.child("likes").getValue(String::class.java)
                                views_count_db = keyId.child("views").getValue(String::class.java)


                                Glide.with(getApplicationContext())
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

            override fun onCancelled(databaseError: DatabaseError) {}

        })
}
}
