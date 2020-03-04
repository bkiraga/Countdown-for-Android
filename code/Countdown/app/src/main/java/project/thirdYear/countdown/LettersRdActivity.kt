package project.thirdYear.countdown

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_letters_rd.*
import java.util.*
import project.thirdYear.countdown.DatabaseManager
import java.io.File
import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


class LettersRdActivity : AppCompatActivity() {

    //@RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letters_rd)
        setUp()
        //dbManager("PARLIAMENT")

        CoroutineScope(Dispatchers.Main).launch {
            var duration = measureTimeMillis {
                var trie = createPopulatedTrie(readTxtFile())
            }
            Toast.makeText(this@LettersRdActivity, "Time: $duration", Toast.LENGTH_LONG).show()
            Toast.makeText(this@LettersRdActivity, "Time: $duration", Toast.LENGTH_LONG).show()
            Toast.makeText(this@LettersRdActivity, "Time: $duration", Toast.LENGTH_LONG).show()
        }


        //populateDB()
    }


    override fun onStart() {
        super.onStart()
        //test()
    }

    public suspend fun readTxtFile():List<String>{
        var text:List<String> = withContext(Dispatchers.IO){
            var res = applicationContext.assets.open("words.txt").bufferedReader().use {
                it.readText()
            }.split("\n")
            // returns by context coroutine
            return@withContext res
        }
        return text
    }

    fun setUp(){
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
    }

    public fun dbManager(word:String):MutableList<String> {

        val dbRef = DatabaseManager(this)

        var results = dbRef.readData()

        Toast.makeText(this, "results size: ${results.size}", Toast.LENGTH_LONG).show()




/*
        val writableDb = dbRef.writableDatabase
        populateDB(writableDb)


        //var result = dbRef.insertData(word)
        //if (result == -1.toLong()){
        //    Toast.makeText(this, "Failed: $result", Toast.LENGTH_SHORT).show()
        //}
        //else{
        //    Toast.makeText(this, "Success: $result, added: $word", Toast.LENGTH_SHORT).show()
        //}

 */
        return results

    }

    fun populateDB(db:SQLiteDatabase):String{

        Toast.makeText(this, "populating", Toast.LENGTH_SHORT).show()
        var text = applicationContext.assets.open("words.txt").bufferedReader().use {
            it.readText()
        }.split("\n")

        Toast.makeText(this, "WordList length: ${text.size}", Toast.LENGTH_LONG).show()
        return ""
/*
        val table = DatabaseManager.Words.WORDS_TABLE
        val column = DatabaseManager.Words.COLUMN_NAME

        db.beginTransaction()

        var values = ContentValues()
        for (word in text){
            values.put(column, word)
            db.insert(table, null, values)
            //Toast.makeText(this, "w: $word", Toast.LENGTH_SHORT).show()
            //dbManager(word)
        }

        db.setTransactionSuccessful()
        db.endTransaction()
        return text.toString()

 */

    }


    suspend fun createPopulatedTrie(words:List<String>):LettersSolver.Trie{

        val t = LettersSolver.Trie()
        withContext(Dispatchers.Default){
                for (word in words){
                    t.add_word(word)
                }
                for (word in words){
                    Log.d(TAG, "word: $word is present: ${t.search_word(word)}")
                }

        }
        return t
    }


    companion object{
        private val TAG = "LettersRound"
    }
}