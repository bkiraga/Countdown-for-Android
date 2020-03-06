package project.thirdYear.countdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_letters_rd.*
import kotlinx.android.synthetic.main.activity_letters_rd2.*

class LettersRdActivity2 : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance()

    private var mAuth = FirebaseAuth.getInstance()
    private val letRdRef = database.getReference("games/matchID/rounds/0")
    var senderID = mAuth.currentUser!!.uid


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letters_rd2)

        setTitle("Letters Round Results")
        try{
            handleMultiplayer()
        } catch (e:Exception){
            Log.d(TAG, e.toString())

        }

        val countDownTimer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisLeft: Long) {
            }
            override fun onFinish() {
                nextRound1.performClick()
            }
        }

        countDownTimer.start()
        var opponentSol = intent.getStringExtra("opsolution")
        ltOpponentSolution.setText(opponentSol)
        Log.d(TAG, "oppSOl " + opponentSol)
        Toast.makeText(this, "$opponentSol", Toast.LENGTH_LONG).show()
        var sol = intent.getStringExtra("solution")

        ltPlayerSolution.setText(sol)
        ltPlayerScore.setText(createScore(sol).toString())
        ltOpponentScore.setText(createScore(opponentSol).toString())
        var activityCount = intent.getIntExtra("activityCount",0)
        var playerAnswer = intent.getStringExtra("playerLetterSolution")
        ltPlayerSolution.text = playerAnswer
        var exists = intent.getBooleanExtra("exists",false)
        //var score: Int = intent.getStringExtra("score").toInt()

        if (exists == true){
            //score = createScore(playerAnswer)
        }
        //ltPlayerScore.text = score.toString()

        nextRound1.setOnClickListener {
            activityCount += 1
            Toast.makeText(this,activityCount.toString(), Toast.LENGTH_LONG).show()
            countDownTimer.cancel()
            //if ( == "x") {
            val intent = Intent(this, NumbersRdActivity::class.java)
            intent.putExtra("activityCount",activityCount)
            //intent.putExtra("totalScore", score)
            //startActivity(intent)
            //}
           // else if (controlFlowFlag == "y"){
           //     val intent = Intent(this, ScoreboardActivity::class.java)
           // }
        }

    }

    fun createScore(word: String):Int{
        when (word.length){
            5 -> return 5
            6 -> return 12
            7 -> return 21
            8 -> return 32
            9 -> return 45
            else -> {
                return 4
            }
        }
    }

    fun handleMultiplayer(){
        try {
            var isMultiplayer = intent.getStringExtra("Multiplayer").toBoolean()
            if (isMultiplayer){
                var opponentSolution:String
                Toast.makeText(this, "handling multiplayer", Toast.LENGTH_SHORT).show()
                //if (intent.getStringExtra("Multiplayer") == "true"){
                letRdRef.addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(dataSnap: DataSnapshot) {
                        for (child in dataSnap.children){
                            if (child.key != senderID){
                                opponentSolution = child.value.toString()
                                ltOpponentSolution.setText(opponentSolution)
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.d(TAG, "error is: $error")
                    }
                })
            }

        } catch (e:Exception){
            Log.d(TAG, e.toString())
        }


    }

    companion object{
        private val TAG = "LettersRound2"
    }
}
