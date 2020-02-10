package project.thirdYear.countdown

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SinglePlayerMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_player_menu)

        val singlePlayerButton: Button = findViewById(R.id.numbersRdButton)

        singlePlayerButton.setOnClickListener {
            val intent = Intent(this, NumbersRdActivity::class.java)
            startActivity(intent)
        }
    }
}
