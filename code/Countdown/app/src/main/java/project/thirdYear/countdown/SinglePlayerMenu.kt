package project.thirdYear.countdown

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_single_player_menu.*

class SinglePlayerMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_player_menu)

        val numbersRdButton: Button = findViewById(R.id.numbersRdButton)
        val lettersRdButton: Button = findViewById(R.id.lettersRdButton)
        val conundrumRdButton: Button = findViewById(R.id.conundrumRdButton)
        val normalGameButton: Button = findViewById(R.id.normalGameButton)


        normalGameButton.setOnClickListener {
            val intent = Intent(this,LettersRdActivity::class.java)
            intent.putExtra("GameType","NormalGame")
            intent.putExtra("flag","letters")
            startActivity(intent)
        }

        numbersRdButton.setOnClickListener {
            val intent = Intent(this, NumbersRdActivity::class.java)
            startActivity(intent)
        }
        lettersRdButton.setOnClickListener {
            val intent = Intent(this, LettersRdActivity::class.java)
            intent.putExtra("flag","letters")
            startActivity(intent)
        }
        conundrumRdButton.setOnClickListener {
            val intent = Intent(this,LettersRdActivity::class.java)
            intent.putExtra("flag","conundrum")
            startActivity(intent)
        }
    }
}