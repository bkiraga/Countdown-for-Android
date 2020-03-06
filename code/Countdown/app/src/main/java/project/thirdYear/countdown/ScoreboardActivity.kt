package project.thirdYear.countdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_scoreboard.*

class ScoreboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)

        setTitle("Scoreboard")

        returnMenu.setOnClickListener{
            val intent = Intent(this, SinglePlayerMenu::class.java)
            startActivity(intent)
        }
    }
}
