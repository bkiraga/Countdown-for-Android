package project.thirdYear.countdown

import android.content.ClipData
import android.graphics.Color
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_letters_rd.*
import java.util.*

class LettersRdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letters_rd)

        val vowelButton: Button = findViewById(R.id.vowelButton)
        val consonantButton: Button = findViewById(R.id.consonantButton)
        val lt1: TextView = findViewById(R.id.lt1)
        val lt2: TextView = findViewById(R.id.lt2)
        val lt3: TextView = findViewById(R.id.lt3)
        val lt4: TextView = findViewById(R.id.lt4)
        val lt5: TextView = findViewById(R.id.lt5)
        val lt6: TextView = findViewById(R.id.lt6)
        val lt7: TextView = findViewById(R.id.lt7)
        val lt8: TextView = findViewById(R.id.lt8)
        val lt9: TextView = findViewById(R.id.lt9)
        val clearLtTile : TextView = findViewById(R.id.clearLtTile)

        var vowels = arrayListOf<String>("A", "E", "I", "O", "U")
        var consonants = arrayListOf<String>(
            "B",
            "C",
            "D",
            "F",
            "G",
            "H",
            "J",
            "K",
            "L",
            "M",
            "N",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "V",
            "W",
            "X",
            "Y",
            "Z"
        )
        var counter: Int = 0

        fun assign(counter: Int): TextView {
            var ltF = when (counter) {
                0 -> lt1
                1 -> lt2
                2 -> lt3
                3 -> lt4
                4 -> lt5
                5 -> lt6
                6 -> lt7
                7 -> lt8
                else -> lt9
            }
            return ltF
        }
        vowelButton.setOnClickListener {
            if (counter < 9) {
                var index = Random().nextInt(vowels.size)
                var vowel = vowels.get(index)
                var ltF = assign(counter)
                ltF.text = vowel.toString()
                counter += 1
            }
        }
        consonantButton.setOnClickListener {
            if (counter < 9) {
                var index = Random().nextInt(consonants.size)
                var consonant = consonants.get(index)
                var ltF = assign(counter)
                ltF.text = consonant.toString()
                counter += 1
            }
        }

        lt1.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt1)
            var clipdata = ClipData.newPlainText("", "  ")
            lt1.startDrag(clipdata, dragShadow, lt1, 0)
            true
        }
        lt2.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt2)
            var clipdata = ClipData.newPlainText("", "  ")
            lt2.startDrag(clipdata, dragShadow, lt2, 0)
            true
        }
        lt3.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt3)
            var clipdata = ClipData.newPlainText("", "  ")
            lt3.startDrag(clipdata, dragShadow, lt3, 0)
            true
        }
        lt4.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt4)
            var clipdata = ClipData.newPlainText("", "  ")
            lt4.startDrag(clipdata, dragShadow, lt4, 0)
            true
        }
        lt5.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt5)
            var clipdata = ClipData.newPlainText("", "  ")
            lt5.startDrag(clipdata, dragShadow, lt5, 0)
            true
        }
        lt6.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt6)
            var clipdata = ClipData.newPlainText("", "  ")
            lt6.startDrag(clipdata, dragShadow, lt6, 0)
            true
        }
        lt7.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt7)
            var clipdata = ClipData.newPlainText("", "  ")
            lt7.startDrag(clipdata, dragShadow, lt7, 0)
            true
        }
        lt8.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt8)
            var clipdata = ClipData.newPlainText("", "  ")
            lt8.startDrag(clipdata, dragShadow, lt8, 0)
            true
        }
        lt9.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt9)
            var clipdata = ClipData.newPlainText("", "  ")
            lt9.startDrag(clipdata, dragShadow, lt9, 0)
            true
        }

        var dragCount = 0
        var usedTiles = arrayListOf<TextView>()
        var movedLtTiles = arrayListOf<TextView>(movedLt1,movedLt2,movedLt3,movedLt4,movedLt5,movedLt6,movedLt7,movedLt8,movedLt9)

        fun clearAllLts(){
            for (tile in usedTiles) {
                tile.setVisibility(View.VISIBLE)
                tile.setEnabled(true)
            }
            for (movedTile in movedLtTiles) {
                movedTile.setVisibility(View.INVISIBLE)
                movedTile.text = ""
            }
            dragCount = 0
        }


        clearAllLtButton.setOnClickListener {
            clearAllLts()
        }

         clearLtTile.setOnClickListener {
            for (movedTile in movedLtTiles.reversed()){
                if (movedTile.getText() != ""){
                    movedTile.text = ""
                    movedTile.setVisibility(View.INVISIBLE)
                    var tile = usedTiles.get(usedTiles.lastIndex)
                    usedTiles.removeAt(usedTiles.lastIndex)
                    dragCount -= 1
                    tile.setVisibility(View.VISIBLE)
                    tile.setEnabled(true)
                    break
                }
            }
        }



        fun assignTargetLtTile(dragCount:Int): TextView {
            var targetLtTile = when (dragCount){
                0 -> movedLt1
                1 -> movedLt2
                2 -> movedLt3
                3 -> movedLt4
                4 -> movedLt5
                5 -> movedLt6
                6 -> movedLt7
                7 -> movedLt8
                else -> movedLt9
            }
            return targetLtTile
        }

        val drag = View.OnDragListener{
            view, event ->
            var dragView = event.getLocalState() as TextView
            event?.let{
                when (event.action){
                    DragEvent.ACTION_DRAG_STARTED -> {
                    }
                    DragEvent.ACTION_DROP -> {
                        var targetTile = assignTargetLtTile(dragCount)
                        targetTile.text = dragView.getText()
                        targetTile.setVisibility(View.VISIBLE)
                        dragView.setVisibility(View.INVISIBLE)
                        dragView.setEnabled(false)
                        usedTiles.add(dragView)
                        dragCount += 1

                        view.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    }
                    DragEvent.ACTION_DRAG_ENTERED -> {
                        view.setBackgroundColor(Color.parseColor("#FED88F"))
                    }
                    DragEvent.ACTION_DRAG_EXITED -> {

                    }
                    DragEvent.ACTION_DRAG_ENDED -> {

                    }
                    else -> {}

                }

            }
            true

        }
        clearAllLts()
        letterTargetField.setOnDragListener(drag)
    }
}