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
            var nums = arrayListOf(75,8,6,10,25,7)
            var answer = solver.solve(nums, 785)
            answerText.text = "abc"
        }
    }
}
