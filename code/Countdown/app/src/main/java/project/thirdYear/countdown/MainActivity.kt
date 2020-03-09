package project.thirdYear.countdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_mplayer = findViewById(R.id.btn_multiplayer_choice) as Button
        btn_mplayer.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        val singlePlayerButton : Button = findViewById(R.id.singlePlayerButton)


        singlePlayerButton.setOnClickListener {
            val intent = Intent(this, SinglePlayerMenu::class.java)
            startActivity(intent)
        }
    }

    override fun onStart(){
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }




}
