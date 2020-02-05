package com.example.countdown

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    val REQUEST_CODE: Int = 7

    lateinit var auth : List<AuthUI.IdpConfig>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (FirebaseAuth.getInstance().getCurrentUser() === null){
            auth = Arrays.asList<AuthUI.IdpConfig>(
                AuthUI.IdpConfig.EmailBuilder().build() //email login functionality based on firebaseUI
                //AuthUI.IdpConfig.FacebookBuilder().build()
            )
            showSignIn()
            setupListener()

        } //else { if the user is already signed in, we go to next screen (choose game type M vs S)

        //}




    }

    private fun setupListener(){
        btn_sign_out.setOnClickListener{
            AuthUI.getInstance().signOut(this@MainActivity)
                .addOnCompleteListener {
                    btn_sign_out.isEnabled = false
                    showSignIn()
                }
                .addOnFailureListener {
                        e-> Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE){
            val responseData = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK){
                val user = FirebaseAuth.getInstance().currentUser // firebase returns user
                Toast.makeText(this, ""+user!!.email, Toast.LENGTH_SHORT).show()
                btn_sign_out.isEnabled = true

            } else{
                Toast.makeText(this, responseData!!.error!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun showSignIn(){
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(auth)
            .setTheme(R.style.AppTheme)
            .build(),REQUEST_CODE)
    }
}
