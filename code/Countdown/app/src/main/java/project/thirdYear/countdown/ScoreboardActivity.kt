package project.thirdYear.countdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_scoreboard.*

class ScoreboardActivity : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance()

    private val letRdRef = database.getReference("games/matchID/rounds/0")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)

        setTitle("Scoreboard")

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
