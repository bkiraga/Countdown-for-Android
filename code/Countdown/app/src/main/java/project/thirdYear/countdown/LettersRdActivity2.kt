package project.thirdYear.countdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_letters_rd.*
import kotlinx.android.synthetic.main.activity_letters_rd2.*

class LettersRdActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letters_rd2)

        val countDownTimer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisLeft: Long) {
            }
            override fun onFinish() {
                nextRound1.performClick()
            }
        }

        countDownTimer.start()

        fun getScore(word: String):Int{
            var score = when (word.length){
                2 -> 1
                3 -> 2
                4 -> 3
                5 -> 5
                6 -> 6
                7 -> 9
                8 -> 12
                9 -> 18
                else -> 0
            }
            return score
        }

        var playerAnswer = intent.getStringExtra("playerLetterSolution")
        ltPlayerSolution.text = playerAnswer
        var exists = intent.getBooleanExtra("exists",false)
        var score: Int = 0
        if (exists == true){
            score = getScore(playerAnswer)
        }
        ltPlayerScore.text = score.toString()

        nextRound1.setOnClickListener {
            countDownTimer.cancel()
            //if ()
            val intent = Intent(this, NumbersRdActivity::class.java)
            intent.putExtra("totalScore",score)
            startActivity(intent)
        }

    }
}
