package project.thirdYear.countdown

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_letters_rd2.*
import kotlinx.android.synthetic.main.activity_numbers_rd2.*
import java.lang.Math.sqrt
import kotlin.math.absoluteValue

class NumbersRdActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers_rd2)

        setTitle("Numbers Round")
        var letterScore = intent.getStringExtra("letterScore")
        var activityCount = intent.getIntExtra("activityCount",0)
        val countDownTimer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisLeft: Long) {
            }
            override fun onFinish() {
                nextRound2.performClick()
            }
        }

        var chosenOps = intent.getStringArrayListExtra("chosenNums")
        var target = intent.getIntExtra("target",0)
        var solution:Int = intent.getStringExtra("playerSolution").toInt()
        var lines = arrayListOf<String>()
        if (chosenOps.size >= 4) {
            var line: String = ""
            var i = 0
            while (i < chosenOps.size) {
                line =
                    chosenOps.get(i) + chosenOps.get(i + 1) + chosenOps.get(i + 2) + "=" + chosenOps.get(
                        i + 3
                    )
                lines.add(line)
                i += 4
            }
            var formatAnswer = ""
            for (l in lines) {
                formatAnswer += l + "\n"
            }

            playerAnswer.text = formatAnswer
        }

        fun getScore(target:Int, solution:Int):Int{
            var score = 0
            if (target == solution){
                score = 10
            }
            var difference = target - solution
            if (difference.absoluteValue <= 5 && difference.absoluteValue > 0){
                score = 7
            }
            if (difference.absoluteValue <= 10 && difference.absoluteValue > 5){
                score = 5
            }

            return score
        }

        scoreText2.text = getScore(target,solution).toString()
        //Toast.makeText(this,target.toString(), Toast.LENGTH_LONG).show()




        fun subResult(equation: String): String {
            val operators = arrayListOf<String>("+","-","*","/")
            var res: Int = 0
            var op: String = ""
            var num1: Int = 0
            var num2: Int = 0
            for (i in 0 until equation.length){
                if (equation.substring(i,i+1) in operators){
                    op = equation.substring(i,i+1)
                    num1 = equation.substring(0,i).toInt()
                    num2 = equation.substring(i+1).toInt()
                    break
                }
            }
            res = when (op){
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                else -> num1 / num2
            }
            return res.toString()
        }

        solve.setOnClickListener {
            solve.setVisibility(View.INVISIBLE)
            var solver: NumsSolver = NumsSolver()
            var nums = intent.getIntegerArrayListExtra("numList")
            var target = intent.getIntExtra("target",0)
            var answerList = solver.solve(nums, target)

            if (answerList.size == 0){
                var flag = 0
                var incdec = 1
                while (solver.solve(nums,target).size == 0){
                    if (flag == 0) {
                        target += incdec
                        answerList = solver.solve(nums,target)
                        flag = 0
                        incdec += 1
                    }
                    else if (flag == 1){
                        target -= incdec
                        answerList = solver.solve(nums,target)
                        flag = 1
                        incdec += 1
                    }
                }
            }
            var answer = answerList.get(0)


            val operators = arrayListOf<String>("+","-","*","/")
            var answerOps = arrayListOf<String>()
            var opIndex = arrayListOf<Int>()

            for (i in 0 until answer.length) {
                if (answer.get(i).toString() == "="){
                    break
                }
                if (answer.get(i).toString() in operators){
                    answerOps.add(answer.get(i).toString())
                    opIndex.add(i)
                }
            }

            var equation:String = ""
            var displayAnswerList = arrayListOf<String>()

            if (answerOps.size >= 1) {
                equation = answer.substring(0, opIndex.get(1))
                var subAnswer = subResult(equation)
                var line1 = equation + " = " + subAnswer
                displayAnswerList.add(line1)

                if (answerOps.size >= 2) {
                    equation = subResult(equation) + answer.substring(opIndex.get(1), opIndex.get(2))
                    subAnswer = subResult(equation)
                    var line2 = equation + " = " + subAnswer
                    displayAnswerList.add(line2)

                    if (answerOps.size >= 3) {
                        equation = subResult(equation) + answer.substring(opIndex.get(2), opIndex.get(3))
                        subAnswer = subResult(equation)
                        var line3 = equation + " = " + subAnswer
                        displayAnswerList.add(line3)

                        if (answerOps.size >= 4) {
                            var line4:String
                            if (answerOps.size == 4){
                                equation = subResult(equation) + answer.substring(opIndex.get(3))
                                line4 = equation
                            }
                            else {
                                subAnswer = subResult(equation)
                                equation = subResult(equation) + answer.substring(opIndex.get(3),opIndex.get(4))
                                line4 = equation + " = " + subAnswer
                            }
                            displayAnswerList.add(line4)

                            if (answerOps.size == 5) {
                                var line5 = subAnswer + answer.substring(opIndex.get(4))
                                displayAnswerList.add(line5)
                            }

                        }
                    }
                }
            }



            var s: String = ""
            for (answer in displayAnswerList){
                s = s + answer + "\n"
            }
            answerText.text = s

        }
        nextRound2.setOnClickListener {
            countDownTimer.cancel()
            val intent = Intent(this, ConundrumActivity::class.java)
            intent.putExtra("numsScore", getScore(target,solution).toString())
            intent.putExtra("letterScore", letterScore)
            //
            //
            // Toast.makeText(this, getScore(target,solution).toString(), Toast.LENGTH_LONG).show()
            intent.putExtra("flag", "conundrum")
            startActivity(intent)
        }
    }

}
