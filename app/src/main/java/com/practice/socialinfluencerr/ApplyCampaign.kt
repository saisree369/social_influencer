package com.practice.socialinfluencerr

import android.app.*
import android.app.Notification
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.TextView

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.WindowManager
import android.webkit.MimeTypeMap

import androidx.core.app.NotificationCompat

import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.gson.Gson

import de.hdodenhof.circleimageview.CircleImageView

import java.io.IOException



class ApplyCampaign : Fragment(){


    val TAG = "PushNotifService"
    lateinit var name: String


    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : NotificationCompat.Builder



    private val channelId = "com.practice.socialinfluencer"
    private val description = "Campaign Request notification"

    var STORAGE_PATH_UPLOADS = "uploads/"

    private var database: FirebaseDatabase? = null
    private var mDatabase: DatabaseReference? = null
    private var cameraButton: ImageView? = null


    private var password:String? =null
    private var campaigns:Campaign?=null

    private var passwordEditText:EditText? = null
    private var phoneEditText:EditText? = null
    private var emailEditText:EditText? = null

    private var url : String? = null

    private var iname : String?=null
    private var age : String?=null
    private var brand : String?=null
    private var price : String? = null
    private var reward : String?=null
    private var product_description: String? = null
    private var platforms_social:String? = null
    private var category_selected : String? = null

    var DATABASE_PATH_UPLOADS = "/users/"
    private var PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null

    private var related_imageview:ImageView? = null
    private var storageReference: StorageReference? = null
    private lateinit var mAuth: FirebaseAuth

  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


  //    getActivity()?.getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_apply_campaign, container, false)
   //   getActivity()?.getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        // Create an ArrayAdapter
        val categories = resources.getStringArray(R.array.category_list)
        val spinner = view.findViewById<Spinner>(R.id.spinner_categoryselect)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                view.context,
                android.R.layout.simple_spinner_item, categories
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        view.context,
//                        getString(R.string.selected_item) + " " +
//                                "" + categories[position], Toast.LENGTH_SHORT
//                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }


      var data = ExploreInfluencerAdapter.getData()
      var position:Int = ExploreInfluencerAdapter.getPostion()


              var inf_pic = view.findViewById(R.id.select_inf_pic) as CircleImageView
              var inf_name = view.findViewById(R.id.select_inf_name) as TextView

              var inf_age = view.findViewById(R.id.select_inf_age) as TextView

              Glide.with(view.context.getApplicationContext())
                  .load(data?.get(position)?.profile_url)
                  .into(inf_pic)
              inf_name?.text = data.get(position)?.name
              inf_age?.text =  data.get(position)?.age

        var brand_selected = view.findViewById(R.id.select_brand_name) as EditText
        var select_price = view.findViewById(R.id.select_price) as EditText
        var select_reward = view.findViewById(R.id.select_reward) as EditText
        var product_desc=view.findViewById(R.id.textView_desc) as TextView
        var platforms=view.findViewById(R.id.platform_names) as TextView

        var button_apply=view.findViewById<Button>(R.id.buttonApply)

        var button_choose= view.findViewById<Button>(R.id.buttonchoose)
        var button_upload = view.findViewById<Button>(R.id.buttonupload)

        related_imageview = view.findViewById<ImageView>(R.id.pic_imageview)


        button_choose!!.setOnClickListener {
          launchGallery()
        };
        button_upload!!.setOnClickListener{
          uploadImage()
        };
  /*
      val email = "someemail@domain.com"
      val credential = EmailAuthProvider.getCredentialWithLink(email, emailLink)
        mAuth = FirebaseAuth.getInstance();
        mAuth.currentUser!!.linkWithCredential(credential)
              .addOnCompleteListener { task ->
                  if (task.isSuccessful) {
                      Log.d(TAG, "Successfully linked emailLink credential!")
                      val result = task.result
                      // You can access the new user via result.getUser()
                      // Additional user info profile *not* available via:
                      // result.getAdditionalUserInfo().getProfile() == null
                      // You can check if the user is new or existing:
                      // result.getAdditionalUserInfo().isNewUser()
                  } else {
                      Log.e(TAG, "Error linking emailLink credential", task.exception)
                  }
              }
      // Construct the email link credential from the current URL.
      var credential = EmailAuthProvider.getCredentialWithLink(email, emailLink)

// Link the credential to the current user.
      mAuth.currentUser!!.linkWithCredential(credential)
              .addOnCompleteListener { task ->
                  if (task.isSuccessful) {
                      Log.d(TAG, "Successfully linked emailLink credential!")
                      val result = task.result
                      // You can access the new user via result.getUser()
                      // Additional user info profile *not* available via:
                      // result.getAdditionalUserInfo().getProfile() == null
                      // You can check if the user is new or existing:
                      // result.getAdditionalUserInfo().isNewUser()
                  } else {
                      Log.e(TAG, "Error linking emailLink credential", task.exception)
                  }
              }*/
        storageReference = FirebaseStorage.getInstance().getReference();
        button_apply.setOnClickListener(object : View.OnClickListener {

          override fun onClick(v: View?) {

              brand = brand_selected.getText().toString();
              price = select_price.getText().toString();
              reward = select_reward.getText().toString();
              product_description = product_desc.text.toString();
              platforms_social = platforms.text.toString()

              category_selected = spinner.getSelectedItem().toString();
              Log.e(TAG,"Brand:$brand, Category_selected:$category_selected")
              if((brand == null) || (price == null) || (reward == null) || (category_selected == null) || (url == null)) {
                  Toast.makeText(view.context, "Please fill all fields", Toast.LENGTH_SHORT).show()
              }
              else{
              //insert data into firebase database
              val pDatabase = FirebaseDatabase.getInstance().reference
              //val submainref: DatabaseReference = pDatabase.child(DATABASE_PATH_UPLOADS)
              val lastQuery = pDatabase.child(DATABASE_PATH_UPLOADS).orderByKey().limitToLast(1)
              lastQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                  override fun onDataChange(dataSnapshot: DataSnapshot) {
                      for (postSnapshot in dataSnapshot.children) {

                          val postKey = postSnapshot.key as String
                          var DATABASE_PATH_UPLOADS_1 = "/users/${postKey}/campaigns"

                          mDatabase = FirebaseDatabase.getInstance().getReference(DATABASE_PATH_UPLOADS_1);


                          val keyid = mDatabase!!.push().key
                          campaigns = Campaign(
                                  brand!!,
                                  price!!,
                                  reward!!,
                                  product_description!!,
                                  platforms_social!!,
                                  category_selected!!,
                                  url!!
                          );
                          if (keyid != null) {
                              mDatabase!!.child(keyid).setValue(campaigns)
                          };

                          val notificationManager =
                                  view.context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                              notificationChannel = NotificationChannel(
                                      channelId, description, NotificationManager.IMPORTANCE_HIGH
                              )

                              notificationChannel.lightColor = Color.GREEN
                              notificationChannel.enableVibration(true)
                              notificationChannel.vibrationPattern = longArrayOf(1000, 2000)

                              notificationManager.createNotificationChannel(notificationChannel)

                              builder = NotificationCompat.Builder(view.context, channelId)

                                      .setContentTitle("Campaign Request")
                                      .setContentText(brand.toString())
                                      .setSmallIcon(R.drawable.ic_launcher_background)
                                      .addPerson(inf_name.toString())
                          }

                          val intent = Intent(view.context, InfluencerActivity::class.java)
                          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                          val stackBuilder = TaskStackBuilder.create(view.context) as TaskStackBuilder
                          stackBuilder.addParentStack(InfluencerActivity::class.java)
                          intent.putExtra("category", category_selected)
                          intent.putExtra("brand", brand)
                          intent.putExtra("price", price)
                          intent.putExtra("reward", reward)
                          intent.putExtra("product_description", product_description)
                          intent.putExtra("url", url)

                          intent.putExtra(
                                  "inf_url",
                                  data.get(position)?.profile_url
                          )
                          intent.putExtra(
                                  "inf_name",
                                  data.get(position)?.name
                          );
                          stackBuilder.addNextIntent(intent);


                          val pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                          builder.setContentIntent(pendingIntent);
                          notificationManager.notify(234, builder.build());

                      }
                  }

                  override fun onCancelled(databaseError: DatabaseError) {}

              })
          }
        }
    });
    return view
}



    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
        related_imageview!!.setBackgroundResource(R.drawable.round_1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(context?.getContentResolver(), filePath)
                related_imageview!!.setImageBitmap(bitmap)
                related_imageview!!.setBackgroundResource(R.drawable.round_1)


            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun getFileExtension(uri: Uri): String? {
        val cR = context?.contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR?.getType(uri))
    }

    private fun uploadImage(){
        if(filePath != null){

            val ref = storageReference!!.child(DATABASE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(filePath!!));
            val uploadTask = ref?.putFile(filePath!!)

            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {

                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "File Uploaded ", Toast.LENGTH_LONG).show();
                    val downloadUri = task.result
                    url = downloadUri.toString()

                } else {
                    // Handle failures

                    Log.e("Error","Error: ${task.result}");

                }
            }?.addOnFailureListener{

            }
        }else{

        }
    }
}
