package project.thirdYear.countdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.widget.Toast
=======
import com.google.firebase.database.FirebaseDatabase
>>>>>>> 62467d34e960d9e38620767015874846239cec15
import kotlinx.android.synthetic.main.activity_scoreboard.*

class ScoreboardActivity : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance()

    private val letRdRef = database.getReference("games/matchID/rounds/0")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)

        setTitle("Scoreboard")

        var numsScore = intent.getStringExtra("numsScore")
        var conundrumScore = intent.getStringExtra("conundrumScore")

        var letterScore = intent.getStringExtra("letterScore")
        //Toast.makeText(this,letterScore, Toast.LENGTH_LONG).show()
        user1Score1.text = letterScore
        numsScore = intent.getStringExtra("numsScore")
        user1Score2.text =  numsScore
        user1Score3.text = conundrumScore
        var totalScore = numsScore.toInt() + letterScore.toInt() + conundrumScore.toInt()
        user1ScoreTotal.text = totalScore.toString()


        returnMenu.setOnClickListener{
            val intent = Intent(this, SinglePlayerMenu::class.java)
            startActivity(intent)
        }
    }

    fun readRoundOneScore(){
        //var opponentScore =
        //user2Score1.setText()
    }
}
