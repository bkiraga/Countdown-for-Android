package project.thirdYear.countdown

import android.content.ClipData
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_numbers_rd.*
import java.util.*

class NumbersRdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers_rd)

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

        val clearTile : Button = findViewById(R.id.clearTile)
        val clearAllTiles : Button = findViewById(R.id.clearAllTiles)

        clearTile.setEnabled(false)
        clearTile.setVisibility(View.INVISIBLE)
        clearAllTiles.setEnabled(false)
        clearAllTiles.setVisibility(View.INVISIBLE)

        var answerTiles = arrayListOf<TextView>(movedNum1,movedOperator1,movedNum2,subAnswerVar1,movedNum3,movedOperator2,movedNum4,subAnswerVar2,movedNum5,movedOperator3,movedNum6,subAnswerVar3,movedNum7,movedOperator4,movedNum8,subAnswerVar4,movedNum9,movedOperator5,movedNum10)

        fun clearAnswerField() {
            for (tile in answerTiles){
                tile.setVisibility(View.INVISIBLE)
                tile.text = ""
            }
        }

        clearAnswerField()

        fun clearAnswerTile() {
            var tiles = answerTiles.reversed()
            for (i in 0 until tiles.size){
                if (tiles[i].getText() != ""){
                    tiles[i].text = ""
                    tiles[i].setVisibility(View.INVISIBLE)
                    break
                }
            }
        }

        clearTile.setOnClickListener {
            clearAnswerTile()
        }

        clearAllTiles.setOnClickListener {
            clearAnswerField()
        }





        val targetNo : TextView = findViewById(R.id.targetNo)
        val targetField: TextView = findViewById(R.id.targetField)

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
                target = Random().nextInt(899) + 100
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
                target = Random().nextInt(899) + 100
                targetNo.text = target.toString()
                largeNumberButton.setEnabled(false)
                largeNumberButton.setVisibility(View.INVISIBLE)
                smallNumberButton.setEnabled(false)
                smallNumberButton.setVisibility(View.INVISIBLE)
                solveButton.setEnabled(true)
                clearTile.setEnabled(true)
                clearTile.setVisibility(View.VISIBLE)
                clearAllTiles.setEnabled(true)
                clearAllTiles.setVisibility(View.VISIBLE)
            }
        }
        solveButton.setOnClickListener {
            val intent = Intent(this, NumbersRdActivity2 ::class.java)
            intent.putIntegerArrayListExtra("numList",numList)
            intent.putExtra("target", target)
            startActivity(intent)
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



        var dragCount: Int = 0
        fun assignTargetNumTile(dragCount:Int): Button {
            var targetNumTile = when (dragCount){
                0 -> movedNum1
                1 -> movedNum2
                2 -> movedNum3
                3 -> movedNum4
                4 -> movedNum5
                else -> movedNum6
            }
            return targetNumTile
        }

        val operatorTiles = arrayListOf<TextView>(plusButton,minusButton,multButton,divButton)
        var dragOperatorCount: Int = 0
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

        val drag = View.OnDragListener {
            view, event ->
            val tag = "Drag and drop"
            var dragView = event.getLocalState() as TextView
            event?.let {
                when (event.action) {
                    DragEvent.ACTION_DROP -> {
                        if (dragView in operatorTiles){
                            assignTargetOperatorTile(dragOperatorCount).text = dragView.getText()
                            assignTargetOperatorTile(dragOperatorCount).setVisibility(View.VISIBLE)
                            dragOperatorCount += 1
                        }
                        else {
                            assignTargetNumTile(dragCount).text = dragView.getText()
                            assignTargetNumTile(dragCount).setVisibility(View.VISIBLE)
                            dragView.setEnabled(false)
                            dragView.setVisibility(View.INVISIBLE)
                            dragCount += 1
                        }
                        targetField.setBackgroundColor(Color.parseColor("#FFFFFF"))

                    }

                    DragEvent.ACTION_DRAG_ENTERED -> {
                        //change the target colour when drag item hovers over it
                        view.setBackgroundColor(Color.parseColor("#FEF65B"))
                    }
                    DragEvent.ACTION_DRAG_EXITED -> {
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
