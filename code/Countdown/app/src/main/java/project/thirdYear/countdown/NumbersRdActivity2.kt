package project.thirdYear.countdown

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_numbers_rd2.*

class NumbersRdActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers_rd2)

        solve.setOnClickListener {
            var solver: NumsSolver = NumsSolver()
            var nums = intent.getIntegerArrayListExtra("numList")
            var target = intent.getIntExtra("target",0)
            var answer = solver.solve(nums, target)
            answerText.text = answer.toString()//answer.toString()
        }
    }
}
