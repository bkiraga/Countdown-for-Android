package project.thirdYear.countdown

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId


class SignUp : AppCompatActivity() {

    private var mAuth = FirebaseAuth.getInstance()
    private lateinit var googleSignInClient: GoogleSignInClient

    private val RQ_CODE = 1410

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_login2)
        setTitle("Create Account")
        signUp()
    }

    public fun signUp(){
        var btn_create_account = findViewById(R.id.btn_create_account) as Button
        var emailTxt = findViewById(R.id.email_field) as EditText
        var passwordTxt = findViewById(R.id.em_pw_field) as EditText


        btn_create_account.setOnClickListener {

            val email = emailTxt.text.toString()
            val password = passwordTxt.text.toString()

            Toast.makeText(this, "$email and $password", Toast.LENGTH_LONG).show()
            createAccount(email, password)
        }


    }

    public fun retrySignIn(){
        var btn_create_account = findViewById(R.id.btn_create_account) as Button
        var emailTxt = findViewById(R.id.email_field) as EditText
        var passwordTxt = findViewById(R.id.em_pw_field) as EditText

        emailTxt.setText("")
        passwordTxt.setText("")
    }

    public fun signedInUI(){
        val intent = Intent(this, LettersRdActivity::class.java)
        intent.putExtra("Multiplayer", "true")
        intent.putExtra("flag", "letters")
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        var user = mAuth.currentUser
        if (user != null){
            updateUI(user)
        }

    }


    private fun signIn(){
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.result
                firebaseAuthWithGoogle(account!!)
            } catch(e: ApiException){
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private  fun firebaseAuthWithGoogle(account:GoogleSignInAccount){
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.id!!)

        val credential = GoogleAuthProvider.getCredential(account.id, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this){task ->
                if (task.isSuccessful){
                    Log.d(TAG, "signInWithCredential:success")
                    val user = mAuth.currentUser
                    if (user != null){
                        updateUI(user)
                    }

                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun signOut(){
        mAuth.signOut()
    }

    private fun createAccount(email:String, password:String){
        Log.d(TAG, "in Create account")
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task: Task<AuthResult> ->
                Log.d(TAG, "in functional part")

                if (task.isSuccessful){
                    Log.d(TAG, "createUserWithEmail:success")

                    signedInUI()
                } else {
                    Log.d(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_LONG).show()
                    retrySignIn()
                }
            }
    }



    private fun updateUI(user: FirebaseUser?){
        if (user != null){
            Log.d(TAG, "$user")
        }
    }

    companion object {
        private const val TAG = "Authentication"
        private const val RC_SIGN_IN = 3011
    }

    }