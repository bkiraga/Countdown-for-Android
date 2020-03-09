package project.thirdYear.countdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_scoreboard.*

class ScoreboardActivity : AppCompatActivity() {

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
}
