package project.thirdYear.countdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.GetTokenResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import kotlin.reflect.typeOf


class JoinMatch : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val gamesRef = database.getReference("games")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_match)
        displayUser()
        checkIfLobbyAvailable()
    }

    public fun getToken() :String {
        var token = ""
        var tokenGetter = FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult -> {
            if (instanceIdResult != null){
                token = instanceIdResult.token
            }
        }
        }
        return token
    }

    public fun displayUser(){
        val token =  getToken()
        print("Token is $token")
        Toast.makeText(baseContext, "$token", Toast.LENGTH_LONG).show()

    }

    public fun joinMatch(){
        //actually add them to match
        return
    }

    public fun checkIfLobbyAvailable(){
        //read the database
        gamesRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (match in dataSnapshot.child("matchID/messages/senderID").children){
                    if (match.key == "message"){
                        Log.d(TAG, "figured out $match")
                    }
                    Log.d(TAG, "class is $match.javaClass.canonicalName")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "The error is: $error")
            }
        })


    }

    public fun createLobby(){
        return
    }

    companion object{
        private const val TAG = "Database"
    }

}