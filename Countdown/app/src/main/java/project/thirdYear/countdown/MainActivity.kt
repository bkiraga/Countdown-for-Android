package project.thirdYear.countdown

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val singlePlayerButton : Button = findViewById(R.id.singlePlayerButton)

        singlePlayerButton.setOnClickListener {
            val intent = Intent(this, SinglePlayerMenu::class.java)
            startActivity(intent)
        }
    }
    
}