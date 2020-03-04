package project.thirdYear.countdown

import android.content.ClipData
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_numbers_rd.*
import java.util.*

class NumbersRdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers_rd)

        //Get id of UI elements
        val largeNumberButton : Button = findViewById(R.id.largeNumberButton)
        val smallNumberButton : Button = findViewById(R.id.smallNumberButton)
        val solveButton : Button = findViewById(R.id.solveButton)
        solveButton.setEnabled(false)

        val no1 : Button = findViewById(R.id.no1)
        val no2 : Button = findViewById(R.id.no2)
        val no3 : Button = findViewById(R.id.no3)
        val no4 : Button = findViewById(R.id.no4)
        val no5 : Button = findViewById(R.id.no5)
        val no6 : Button = findViewById(R.id.no6)

        val movedNum1 : Button = findViewById(R.id.movedNum1)
        val movedNum2 : Button = findViewById(R.id.movedNum2)
        val movedNum3 : Button = findViewById(R.id.movedNum3)
        val movedNum4 : Button = findViewById(R.id.movedNum4)
        val movedNum5 : Button = findViewById(R.id.movedNum5)
        val movedNum6 : Button = findViewById(R.id.movedNum6)
        val movedNum7 : Button = findViewById(R.id.movedNum7)
        val movedNum8 : Button = findViewById(R.id.movedNum8)
        val movedNum9 : Button = findViewById(R.id.movedNum9)
        val movedNum10 : Button = findViewById(R.id.movedNum10)

        val subAnswerVar1 : Button = findViewById(R.id.subAnswerVar1)
        val subAnswerVar2 : Button = findViewById(R.id.subAnswerVar2)
        val subAnswerVar3 : Button = findViewById(R.id.subAnswerVar3)
        val subAnswerVar4 : Button = findViewById(R.id.subAnswerVar4)

        val targetNo : TextView = findViewById(R.id.targetNo)
        val targetField: TextView = findViewById(R.id.targetField)

        // required variables
        var dragOperatorCount: Int = 0
        var dragCount: Int = 0

        var largeNums = arrayListOf<Int>(25,50,75,100)
        var smallNums = arrayListOf<Int>(1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10)
        var numList = arrayListOf<Int>()
        var counter : Int = 0
        var target : Int = 0

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
                numList.add(num)
                numF.text = num.toString()
                counter += 1
            }
            if (counter == 6){
                target = Random().nextInt(898) + 101
                targetNo.text = target.toString()
                smallNumberButton.setEnabled(false)
                smallNumberButton.setVisibility(View.INVISIBLE)
                largeNumberButton.setEnabled(false)
                largeNumberButton.setVisibility(View.INVISIBLE)
                solveButton.setEnabled(true)
                clearTile.setEnabled(true)
                clearTile.setVisibility(View.VISIBLE)
                clearAllTiles.setEnabled(true)
                clearAllTiles.setVisibility(View.VISIBLE)
            }

        }
        largeNumberButton.setOnClickListener {
            if (largeNums.size > 0 && counter < 6) {
                var index = Random().nextInt(largeNums.size)
                var num = largeNums.get(index)
                largeNums.remove(num)
                var numF = assign(counter)
                numList.add(num)
                numF.text = num.toString()
                counter += 1
            }
            if (counter == 6){
                target = Random().nextInt(898) + 101
                targetNo.text = target.toString()
                largeNumberButton.setVisibility(View.INVISIBLE)
                largeNumberButton.setEnabled(false)
                smallNumberButton.setEnabled(false)
                smallNumberButton.setVisibility(View.INVISIBLE)
                solveButton.setEnabled(true)
                clearTile.setEnabled(true)
                clearTile.setVisibility(View.VISIBLE)
                clearAllTiles.setEnabled(true)
                clearAllTiles.setVisibility(View.VISIBLE)
            }
        }

        val clearTile : Button = findViewById(R.id.clearTile)
        val clearAllTiles : Button = findViewById(R.id.clearAllTiles)

        clearTile.setEnabled(false)
        clearTile.setVisibility(View.INVISIBLE)
        clearAllTiles.setEnabled(false)
        clearAllTiles.setVisibility(View.INVISIBLE)

        var answerTiles = arrayListOf<TextView>(movedNum1,movedOperator1,movedNum2,subAnswerVar1,movedNum3,movedOperator2,movedNum4,subAnswerVar2,movedNum5,movedOperator3,movedNum6,subAnswerVar3,movedNum7,movedOperator4,movedNum8,subAnswerVar4,movedNum9,movedOperator5,movedNum10)
        var usedTiles = arrayListOf<TextView>()
        var subAnswerTiles = arrayListOf<TextView>(subAnswerVar1,subAnswerVar2,subAnswerVar3,subAnswerVar4)


        solveButton.setOnClickListener {
            val intent = Intent(this, NumbersRdActivity2 ::class.java)
            intent.putIntegerArrayListExtra("numList",numList)
            intent.putExtra("target", target)
            var playerAnswer = arrayListOf<String>()
            for (tile in answerTiles){
                if (tile.getText() != ""){
                    playerAnswer.add(tile.getText().toString())
                }
            }
            intent.putStringArrayListExtra("chosenNums",playerAnswer)
            startActivity(intent)
        }


        fun clearAllAnswerTiles() {
            for (tile in answerTiles){
                tile.setVisibility(View.INVISIBLE)
                tile.text = ""
            }
            for (tile in usedTiles){
                if (!(tile in subAnswerTiles)) {
                    tile.setVisibility(View.VISIBLE)
                    tile.setEnabled(true)
                }
            }
            dragOperatorCount = 0
            dragCount = 0
        }

        clearAllAnswerTiles()

        fun clearAnswerTile() {
            var tiles = answerTiles.reversed()
            for (i in 0 until tiles.size){
                if (tiles[i].getText() != ""){
                    tiles[i].text = ""
                    tiles[i].setVisibility(View.INVISIBLE)
                    break
                }
            }
            if (usedTiles.size != 0) {
                var tile = usedTiles.get(usedTiles.lastIndex)
                tile.setVisibility(View.VISIBLE)
                tile.setEnabled(true)
                usedTiles.removeAt(usedTiles.lastIndex)
            }

        }

        clearTile.setOnClickListener {
            clearAnswerTile()
        }

        clearAllTiles.setOnClickListener {
            clearAllAnswerTiles()
        }


        no1.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(no1)
            var clipdata = ClipData.newPlainText("", "  ")
            no1.startDrag(clipdata, dragShadow, no1, 0)
            true
        }

        no2.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(no2)
            var clipdata = ClipData.newPlainText("", "  ")
            no2.startDrag(clipdata, dragShadow, no2, 0)
            true
        }

        no3.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(no3)
            var clipdata = ClipData.newPlainText("", "  ")
            no3.startDrag(clipdata, dragShadow, no3, 0)
            true
        }

        no4.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(no4)
            var clipdata = ClipData.newPlainText("", "  ")
            no4.startDrag(clipdata, dragShadow, no4, 0)
            true
        }

        no5.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(no5)
            var clipdata = ClipData.newPlainText("", "  ")
            no5.startDrag(clipdata, dragShadow, no5, 0)
            true
        }

        no6.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(no6)
            var clipdata = ClipData.newPlainText("", "  ")
            no6.startDrag(clipdata, dragShadow, no6, 0)
            true
        }

        plusButton.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(plusButton)
            var clipdata = ClipData.newPlainText("", "  ")
            plusButton.startDrag(clipdata, dragShadow, plusButton, 0)
            true
        }
        minusButton.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(minusButton)
            var clipdata = ClipData.newPlainText("", "  ")
            minusButton.startDrag(clipdata, dragShadow, minusButton, 0)
            true
        }
        multButton.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(multButton)
            var clipdata = ClipData.newPlainText("", "  ")
            multButton.startDrag(clipdata, dragShadow, multButton, 0)
            true
        }
        divButton.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(divButton)
            var clipdata = ClipData.newPlainText("", "  ")
            divButton.startDrag(clipdata, dragShadow, divButton, 0)
            true
        }
        subAnswerVar1.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(subAnswerVar1)
            var clipdata = ClipData.newPlainText("", "  ")
            subAnswerVar1.startDrag(clipdata, dragShadow, subAnswerVar1, 0)
            true
        }
        subAnswerVar2.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(subAnswerVar2)
            var clipdata = ClipData.newPlainText("", "  ")
            subAnswerVar2.startDrag(clipdata, dragShadow, subAnswerVar2, 0)
            true
        }
        subAnswerVar3.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(subAnswerVar3)
            var clipdata = ClipData.newPlainText("", "  ")
            subAnswerVar3.startDrag(clipdata, dragShadow, subAnswerVar3, 0)
            true
        }
        subAnswerVar4.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(subAnswerVar4)
            var clipdata = ClipData.newPlainText("", "  ")
            subAnswerVar4.startDrag(clipdata, dragShadow, subAnswerVar4, 0)
            true
        }




        fun assignTargetNumTile(dragCount:Int): Button {
            var targetNumTile = when (dragCount){
                0 -> movedNum1
                1 -> movedNum2
                2 -> movedNum3
                3 -> movedNum4
                4 -> movedNum5
                5 -> movedNum6
                6 -> movedNum7
                7 -> movedNum8
                8 -> movedNum9
                else -> movedNum10
            }
            return targetNumTile
        }

        val operatorTiles = arrayListOf<TextView>(plusButton,minusButton,multButton,divButton)
        fun assignTargetOperatorTile(dragOperatorCount: Int): Button {
            var targetOperatorTile = when (dragOperatorCount){
                0 -> movedOperator1
                1 -> movedOperator2
                2 -> movedOperator3
                3 -> movedOperator4
                else -> movedOperator5
            }
            return targetOperatorTile
        }

        fun calAnswerTile(numTile1:TextView,opTile:TextView,numTile2:TextView): Int {
            var num1 = numTile1.getText().toString().toInt()
            var num2 = numTile2.getText().toString().toInt()
            var answer: Int = 0

            if (opTile.getText() == "+"){
                answer = num1 + num2
            }
            if (opTile.getText() == "*"){
                answer = num1 * num2
            }
            if (opTile.getText() == "-"){
                if (num1 > num2){
                    answer = num1 - num2
                }
                else {
                    answer = 0
                }
            }
            if (opTile.getText() == "/"){
                if (num1 % num2 == 0){
                    answer = num1 / num2
                }
                else {
                    answer = 0
                }
            }
            return answer
        }

        // Target field for the drag object

        val drag = View.OnDragListener {
            view, event ->
            var dragView = event.getLocalState() as TextView
            event?.let {
                when (event.action) {
                    DragEvent.ACTION_DRAG_STARTED -> {
                        view.setBackgroundColor(Color.parseColor("#FED88F"))
                    }

                    DragEvent.ACTION_DROP -> {
                        if (dragView in operatorTiles){
                            assignTargetOperatorTile(dragOperatorCount).text = dragView.getText()
                            assignTargetOperatorTile(dragOperatorCount).setVisibility(View.VISIBLE)
                            dragOperatorCount += 1
                        }
                       // if ()
                        else {
                            assignTargetNumTile(dragCount).text = dragView.getText()
                            assignTargetNumTile(dragCount).setVisibility(View.VISIBLE)
                            if (dragView in subAnswerTiles){
                             Toast.makeText(this,dragView.getText().toString(),Toast.LENGTH_LONG).show()

                            }
                            dragView.setEnabled(false)
                            dragView.setVisibility(View.INVISIBLE)
                            usedTiles.add(dragView)
                            dragCount += 1
                        }
                        if (movedOperator1.getText() != "" && movedNum2.getText() != "" && subAnswerVar1.getText() == "") {
                            subAnswerVar1.setVisibility(View.VISIBLE)
                            subAnswerVar1.setEnabled(true)
                            var answer = calAnswerTile(movedNum1,movedOperator1,movedNum2)
                            if (answer != 0) {
                                subAnswerVar1.text = answer.toString()
                            }
                            else {
                                subAnswerVar1.text = "X"
                                subAnswerVar1.setEnabled(false)
                            }
                        }
                        if (movedOperator2.getText() != "" && movedNum4.getText() != "" && subAnswerVar2.getText() == "") {
                            subAnswerVar2.setVisibility(View.VISIBLE)
                            subAnswerVar2.setEnabled(true)
                            var answer = calAnswerTile(movedNum3,movedOperator2,movedNum4)
                            if (answer != 0){
                                subAnswerVar2.text = answer.toString()
                            }
                            else {
                                subAnswerVar2.text = "X"
                                subAnswerVar2.setEnabled(false)
                            }
                        }
                        if (movedOperator3.getText() != "" && movedNum6.getText() != "" && subAnswerVar3.getText() == "") {
                            subAnswerVar3.setVisibility(View.VISIBLE)
                            subAnswerVar3.setEnabled(true)
                            var answer = calAnswerTile(movedNum5,movedOperator3,movedNum6)
                            if (answer != 0){
                                subAnswerVar3.text = answer.toString()
                            }
                            else {
                                subAnswerVar3.text = "X"
                                subAnswerVar3.setEnabled(false)
                            }
                        }
                        if (movedOperator4.getText() != "" && movedNum8.getText() != "" && subAnswerVar4.getText() == "") {
                            subAnswerVar4.setVisibility(View.VISIBLE)
                            subAnswerVar4.setEnabled(true)
                            var answer = calAnswerTile(movedNum7,movedOperator4,movedNum8)
                            if (answer != 0){
                                subAnswerVar4.text = answer.toString()
                            }
                            else {
                                subAnswerVar4.text = "X"
                                subAnswerVar4.setEnabled(false)
                            }
                        }

                        view.setBackgroundColor(Color.parseColor("#FFFFFF"))

                    }

                    DragEvent.ACTION_DRAG_ENTERED -> {
                        //change the target colour when drag item hovers over it
                        view.setBackgroundColor(Color.parseColor("#FEF65B"))
                    }
                    DragEvent.ACTION_DRAG_EXITED -> {
                        view.setBackgroundColor(Color.parseColor("#FED88F"))
                    }
                    DragEvent.ACTION_DRAG_ENDED -> {
                        view.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    }
                    else -> { }
                }
            }
            true
        }

        targetField.setOnDragListener(drag)

    }


    }
