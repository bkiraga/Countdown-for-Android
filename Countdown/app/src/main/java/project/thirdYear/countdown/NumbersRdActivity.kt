package project.thirdYear.countdown

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class NumbersRdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers_rd)

        val largeNumberButton : Button = findViewById(R.id.largeNumberButton)
        val smallNumberButton : Button = findViewById(R.id.smallNumberButton)
        val no1 : TextView = findViewById(R.id.no1)
        val no2 : TextView = findViewById(R.id.no2)
        val no3 : TextView = findViewById(R.id.no3)
        val no4 : TextView = findViewById(R.id.no4)
        val no5 : TextView = findViewById(R.id.no5)
        val no6 : TextView = findViewById(R.id.no6)
        val targetNo : TextView = findViewById(R.id.targetNo)

        var largeNums = arrayListOf<Int>(25,50,75,100)
        var smallNums = arrayListOf<Int>(1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10)
        var counter : Int = 0

        fun assign(counter : Int) : TextView {
            var numF = when (counter) {
                0 -> no1
                1 -> no2
                2 -> no3
                3 -> no4
                4 -> no5
                5 -> no6
                else -> targetNo
            }
            return numF
        }

        smallNumberButton.setOnClickListener {
            if (smallNums.size > 0 && counter < 6) {
                var index = Random().nextInt(smallNums.size)
                var num = smallNums.get(index)
                smallNums.remove(num)
                var numF = assign(counter)
                numF.text = num.toString()
                counter += 1
            }
            if (counter == 6){
                var target = Random().nextInt(1000)
                targetNo.text = target.toString()
                smallNumberButton.setEnabled(false)
                largeNumberButton.setEnabled(false)
                }

        }
        largeNumberButton.setOnClickListener {
            if (largeNums.size > 0 && counter < 6) {
                var index = Random().nextInt(largeNums.size)
                var num = largeNums.get(index)
                largeNums.remove(num)
                var numF = assign(counter)
                numF.text = num.toString()
                counter += 1
            }
            if (counter == 6){
                var target = Random().nextInt(1000)
                targetNo.text = target.toString()
                largeNumberButton.setEnabled(false)
                smallNumberButton.setEnabled(false)
                }

        }
    }
}
