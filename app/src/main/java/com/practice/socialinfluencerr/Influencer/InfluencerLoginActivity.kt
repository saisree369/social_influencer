package com.practice.socialinfluencerr

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class InfluencerLoginActivity : AppCompatActivity() {

    private var titleTextView: TextView? = null
    private var registerTextView: TextView? = null
    private var forgetPassTextView: TextView? = null
    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var logoImageView: ImageView? = null
    private var loginButton: Button? = null

    private var email: String? = null
    private var password: String? = null
    private var TAG = "InfluencerLoginActivity"

    val RC_SIGN_IN: Int = 1
    lateinit var signInClient: GoogleSignInClient
    lateinit var signInOptions: GoogleSignInOptions
    private lateinit var mAuth: FirebaseAuth

    private var USERS = "influencer_users"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_influencer_login)

        titleTextView = findViewById(R.id.title_textview);
        registerTextView = findViewById(R.id.register_textview);
        emailEditText = findViewById(R.id.emailogin_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        logoImageView = findViewById(R.id.logo_imageview);
        loginButton = findViewById(R.id.button);


        mAuth = FirebaseAuth.getInstance();

        //checking if user is logged in
        if (mAuth.getCurrentUser() != null) {
            updateUI(mAuth.getCurrentUser()!!);
        }

        loginButton!!.setOnClickListener {
            userLoginIn()
        }


        var arrow_back = findViewById(R.id.menu_specific) as ImageView;
        arrow_back.setOnClickListener {
            finish()
        }

        var registerTextView = findViewById(R.id.register_textview) as TextView
        registerTextView.setOnClickListener {
            val intent = Intent(this, InfluencerRegisterActivity::class.java)
            startActivity(intent);
        }



/*
        initializeUI()
        setupGoogleLogin()
        loginButton!!.setOnClickListener { userLoginIn()

        }

        forgetPassTextView!!.setOnClickListener { userForgotPassword() }
*/
    }


    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
    }


    private fun userLoginIn() {
        val email = emailEditText!!.text.toString()
        val password = passwordEditText!!.text.toString()

        when {
            TextUtils.isEmpty(email) -> emailEditText!!.error = "Enter email address!"

            TextUtils.isEmpty(password) -> passwordEditText!!.error = "Enter password!"

            else -> {


                mAuth!!.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        this@InfluencerLoginActivity
                    ) { task ->

                        if (!task.isSuccessful) {
                            if (password.length < 6) {
                                passwordEditText!!.error = "Password too short, enter minimum 6 characters!"
                            } else {
                                passwordEditText!!.error =
                                        "Authentication failed, Check your Email and Password or Sign Up"
                            }
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(
                                getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show();
                        } else {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = mAuth.currentUser
                            val rootRef = FirebaseDatabase.getInstance().reference
                            val userRef = rootRef.child(USERS)
                            userRef.key?.let { Log.v("USERID", it) }
                            userRef.addValueEventListener(object : ValueEventListener {


                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    for (keyId in dataSnapshot.children) {
                                        if (keyId.child("email").value == email) {
                                            if (user != null) {
                                                updateUI(user)
                                            };
                                            break
                                        }
                                    }

                                }
                                override fun onCancelled(error: DatabaseError) {
                                    // Failed to read value
                                    //              Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
                                }
                            })

                        }
                    }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            //         updateUI(currentUser);

        }
    }

    override fun onResume() {
        super.onResume()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            //           updateUI(currentUser);
        }
    }

    private fun updateUI(currentUser: FirebaseUser) {
        val profileIntent = Intent(this, InfluencerActivity::class.java)
        profileIntent.putExtra("email", currentUser.getEmail())

//        Log.v("DATA", currentUser.getUid())
        startActivity(profileIntent)
    }



    private fun userForgotPassword() {
        val email = emailEditText!!.text.toString().trim { it <= ' ' }

        if (TextUtils.isEmpty(email)) {
            emailEditText!!.error = "Enter email address!"
        }

        else {

            mAuth!!.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@InfluencerLoginActivity,
                            "We have sent you instructions to reset your password!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@InfluencerLoginActivity,
                            "Failed to send reset email!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }
        }
    }

/*        override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            startActivity(InfluencerActivity.getLaunchIntent(this))
            finish()
        }
    }*/
/*
    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, InfluencerLoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }



    private fun initializeUI() {
        var google_button = findViewById(R.id.google) as ImageView;
        google_button.setOnClickListener {
            login()


        }
    }

    private fun login() {
        val loginIntent: Intent = signInClient.signInIntent
        startActivityForResult(loginIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    googleFirebaseAuth(account)
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun googleFirebaseAuth(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {

                startActivity(InfluencerActivity.getLaunchIntent(this))
            } else {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupGoogleLogin() {
        signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        signInClient = GoogleSignIn.getClient(this, signInOptions)
    }
*/
}
