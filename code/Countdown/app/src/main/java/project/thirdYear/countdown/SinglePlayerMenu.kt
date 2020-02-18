package project.thirdYear.countdown

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SinglePlayerMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_player_menu)

        val numbersRdButton: Button = findViewById(R.id.numbersRdButton)
        val lettersRdButton: Button = findViewById(R.id.lettersRdButton)

        numbersRdButton.setOnClickListener {
            val intent = Intent(this, NumbersRdActivity::class.java)
            startActivity(intent)
        }
        lettersRdButton.setOnClickListener {
            val intent = Intent(this, LettersRdActivity::class.java)
            startActivity(intent)
        }
    }
}
