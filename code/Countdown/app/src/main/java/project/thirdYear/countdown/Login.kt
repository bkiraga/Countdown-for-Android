package project.thirdYear.countdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.w3c.dom.Text

class Login : AppCompatActivity() {

    private var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signIn()
        hasAccount()
    }

    private fun signIn(){
        var signIn_emailTxt = findViewById(R.id.signIn_email_field) as EditText
        var signIn_passwTxt = findViewById(R.id.signIn_passw_field) as EditText

        btn_signIn.setOnClickListener {
            val passw = signIn_passwTxt.text.toString()
            val email = signIn_emailTxt.text.toString()
            if (validateCredentials(email, passw)){
                mAuth.signInWithEmailAndPassword(email, passw)
                    .addOnCompleteListener(this) { task: Task<AuthResult> ->
                        if (task.isSuccessful){
                            Log.d(TAG, "signInWithEmail:success")
                            signedInUI()
                        } else {
                            Toast.makeText(this, "Sign in failed. Try again.", Toast.LENGTH_LONG).show()
                            resetFields(signIn_emailTxt, signIn_passwTxt)
                            Log.w(TAG, "SignInWithEmail:failure", task.exception)
                        }
                    }
            } else{
                resetFields(signIn_emailTxt, signIn_passwTxt)
                Toast.makeText(this, "Enter a valid email and a password longer than 8 characters.", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun hasAccount(){
        var hasAcc = findViewById(R.id.signIn_txt) as TextView
        hasAcc.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun resetFields(email:EditText, password: EditText){
        email.setText("")
        password.setText("")
    }

    public fun signedInUI(){
        val intent = Intent(this, NumbersRdActivity::class.java)
        intent.putExtra("Multiplayer", "Multiplayer")
        startActivity(intent)
    }

    private fun validateCredentials(email: String, password: String) : Boolean {
        var valid = false
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)
            && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 8){
            valid = true
        }
        return valid
    }
    companion object{
        private const val TAG = "Login"
    }
}
