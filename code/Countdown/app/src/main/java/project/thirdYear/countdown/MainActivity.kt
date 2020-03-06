package project.thirdYear.countdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_multiplayer_choice.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        singlePlayerButton.setOnClickListener {
            val intent = Intent(this, SinglePlayerMenu::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStart(){
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
