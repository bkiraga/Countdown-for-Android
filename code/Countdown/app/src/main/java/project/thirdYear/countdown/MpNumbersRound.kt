package project.thirdYear.countdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_mp_numbers_round.*
import java.util.*

import kotlin.collections.ArrayList

class MpNumbersRound : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance()
    private val gamesRef = database.getReference("games/matchID/messages")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mp_numbers_round)
        sendMessage()
        getMessage()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun sendMessage(){
        //val senderID = FirebaseAuth.getInstance().currentUser.toString()
        //Log.d(TAG, "currentUser is $senderID")

        btn_chat_send.setOnClickListener{

            var messageInput = findViewById(R.id.chat_input) as EditText
            var textM = messageInput.text.toString()
            Toast.makeText(this, "$textM", Toast.LENGTH_LONG).show()
            Log.d(TAG, "text is $textM")
            //val message = Message(textM, System.currentTimeMillis())

            Toast.makeText(this, "message", Toast.LENGTH_LONG).show()

            //Log.d(TAG, "message is $message")


            gamesRef.child("senderID").setValue(textM)
            messageInput.setText("")
        }
    }

    private fun getMessage(){
        //var textsRecv = findViewById(R.id.texts_received) as EditText
        var messages = arrayListOf<String>()

        gamesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(databaseSnap: DataSnapshot) {
             for (child in databaseSnap.children){
                 Log.d(TAG, "child is $child")
                 //messages.p

                 //textsRecv.setText(child.toString())

             }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "error is $error")
            }
        })
    }

    companion object{
        private const val TAG = "NumbersRound"
    }
}
