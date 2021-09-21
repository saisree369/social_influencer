package com.practice.socialinfluencerr


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.File
import java.io.IOException
import java.lang.Thread.sleep


class AdvertiserRegisterActivity : AppCompatActivity() {


    private var  nameEditText:EditText? = null
    private var professionEditText:EditText? = null
    private var workEditText:EditText? = null
    private var passwordEditText:EditText? = null
    private var phoneEditText:EditText? = null
    private var emailEditText:EditText? = null
    private var genderEditText:EditText? = null
    private var picImageView:ImageView? = null
    private var  maleCheckBox:CheckBox?=null
    private var femaleCheckBox:CheckBox?=null
    private var registerButton:Button?= null
    private var database: FirebaseDatabase? = null
    private var mDatabase: DatabaseReference? = null

    private var USERS = "users"

    private var password:String? =null
    private var user:User?=null

    private lateinit var mAuth: FirebaseAuth

    private var username : String?=null
    private var fname : String?=null
    private var email : String?=null
    private var path : String?=null
    private var profession : String?=null
    private var gender : String? = null
    private var phone : String?=null
    private var url : String? = null
    private var workplace : String?=null

    var STORAGE_PATH_UPLOADS = "uploads/"
    var DATABASE_PATH_UPLOADS = "users"
    private var cameraButton: ImageView? = null
    private var buttonChoose: Button? = null
    private var buttonUpload: Button? = null
    private var profile_pic : ImageView? =  null

    private var PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adv_register);

        nameEditText = findViewById(R.id.fullname_edittext);
        professionEditText = findViewById(R.id.profession_edittext);
        workEditText = findViewById(R.id.workplace_edittext);
        phoneEditText = findViewById(R.id.phone_edittext);
        passwordEditText = findViewById(R.id.enterpass_edittext);
        emailEditText = findViewById(R.id.email_edittext);
        genderEditText = findViewById(R.id.gender_edittext);
        picImageView = findViewById(R.id.pic_imageview);

        registerButton = findViewById<Button>(R.id.register_button);
        //       database = FirebaseDatabase.getInstance();
//        mDatabase = database!!.getReference(USERS);
        mAuth = FirebaseAuth.getInstance();

        cameraButton = findViewById<ImageView>(R.id.pic_cameraview);
        buttonChoose = findViewById<Button>(R.id.buttonChoose);
        buttonUpload =  findViewById<Button>(R.id.buttonUpload);
        profile_pic =  findViewById<ImageView>(R.id.pic_imageview);
        storageReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(DATABASE_PATH_UPLOADS);

        cameraButton!!.setOnClickListener{
            val intent = Intent(this, SampleCameraScreen::class.java)
            startActivity(intent);
        }
        buttonChoose!!.setOnClickListener{
            launchGallery();
        }
        buttonUpload!!.setOnClickListener{
            uploadImage()
        };


        registerButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //insert data into firebase database
                if (emailEditText!!.getText().toString() != null && passwordEditText!!.getText().toString() != null && nameEditText!!.getText().toString() != null &&
                        genderEditText!!.getText().toString() != null && phoneEditText!!.getText().toString() != null && professionEditText!!.getText().toString() !=null
                        && workEditText!!.getText().toString() !=null && url!!.toString() !=null) {
                    fname = nameEditText!!.getText().toString();
                    email = emailEditText!!.getText().toString()
                    gender = genderEditText!!.getText().toString();
                    phone = phoneEditText!!.getText().toString();
                    profession = professionEditText!!.getText().toString();
                    workplace = workEditText!!.getText().toString();
                    password = passwordEditText!!.getText().toString()
                    user = User(fname!!,email!!,gender!!,profession!!, workplace!!, phone!!,url!!);
                    showToast("Please fill all details");
                    registerUser();


                }
            }
        });
        val intent = intent

        path = intent.getStringExtra("filepath")
        if(path != null) {
            Log.e("Error", "Error: ${path}");

            //val profile_path: Uri? = path?.toUri()
            //profile_pic?.setImageURI(imagepath)

            val bmImg: Bitmap = BitmapFactory.decodeFile(path)
            Log.e("Error", "Error: ${path}");
            profile_pic?.setImageBitmap(bmImg)
            showToast("register path = mFile")
        }

/*
        val sd = Environment.getExternalStorageDirectory()
        val image = File(sd+path)
        val bmOptions: BitmapFactory.Options = BitmapFactory.Options()
        var bitmap: Bitmap = BitmapFactory.decodeFile(image.absolutePath, bmOptions)
        profile_pic!!.setImageBitmap(bitmap)*/
/*
        val imgFile = File(path)
        if (imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            //Drawable d = new BitmapDrawable(getResources(), myBitmap);
            profile_pic!!.setImageBitmap(myBitmap)
        }
*/
        var arrow_back = findViewById(R.id.menu_specific) as ImageView;
        arrow_back.setOnClickListener {
            finish()
        }
    }


    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
        profile_pic!!.setBackgroundResource(R.drawable.round_1)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data
            path = null

            try {
                val bitmap = MediaStore.Images.Media.getBitmap( getContentResolver(), filePath)
                profile_pic!!.setImageBitmap(bitmap)
                profile_pic!!.setBackgroundResource(R.drawable.round_1)


            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }



    fun getFileExtension(uri: Uri): String? {
        val cR = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }

    private fun showToast(text: String) {
        runOnUiThread { Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show() }
    }

    private fun uploadImage(){

        var profile_path: Uri? = null
        if(path != null){
            profile_path = Uri.fromFile(File(path))
        }

        //   showToast("filePath = ${filePath.toString()}")




        if(profile_path != null){
            filePath= profile_path;
        }

        if(filePath != null){

            val ref = storageReference!!.child(STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(filePath!!));
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
                    Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                    val downloadUri = task.result
                    url = downloadUri.toString()

                } else {
                    // Handle failures

                    Log.e("Error","Error: ${task.result}");

                }
            }?.addOnFailureListener{

            }
        }else{
            Toast.makeText(this, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }

        /*   val profile_path: Uri? = path?.toUri()
           if((profile_path != null)){

               val ref = storageReference!!.child(STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(profile_path!!));
               val uploadTask = ref?.putFile(profile_path!!)



               val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                   if (!task.isSuccessful) {
                       task.exception?.let {

                           throw it
                       }
                   }
                   return@Continuation ref.downloadUrl
               })?.addOnCompleteListener { task ->
                   if (task.isSuccessful) {
                       Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                       val downloadUri = task.result
                       url = downloadUri.toString()

                   } else {
                       // Handle failures

                       Log.e("Error","Error: ${task.result}");

                   }
               }?.addOnFailureListener{

               }
           }else{
               Toast.makeText(this, "Please Upload an Image", Toast.LENGTH_SHORT).show()
           }*/
    }



    private fun registerUser() {

        val email = emailEditText!!.text.toString()
        val password = passwordEditText!!.text.toString()

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        this@AdvertiserRegisterActivity
                ) { task ->

                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        //                       Log.d(TAG, "createUserWithEmail:success");
                        val user = mAuth.currentUser

                        if (user != null) {
                            updateUI(user)
                        };
                    } else {

                    }
                }


    }

    /**
     * adding user information to database and redirect to login screen
     * @param currentUser
     */
    fun updateUI(currentUser: FirebaseUser) {
        val keyid = mDatabase!!.push().key
        if (keyid != null) {
            mDatabase!!.child(keyid).setValue(user)
            logout();

        } //adding user info to database

    }
    private fun logout() {
        val intent = Intent(this, AdvertiserLoginActivity::class.java)
        startActivity(intent);

        FirebaseAuth.getInstance().signOut();
    }


}