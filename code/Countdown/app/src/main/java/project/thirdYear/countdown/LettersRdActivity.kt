package project.thirdYear.countdown

import android.content.ClipData
import android.graphics.Color
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.DragEvent
import android.view.View
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_letters_rd.*
import kotlinx.android.synthetic.main.activity_letters_rd2.*
import java.util.*
import project.thirdYear.countdown.DatabaseManager
import java.io.File
import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.system.measureTimeMillis

class LettersRdActivity : AppCompatActivity() {

    private var mAuth = FirebaseAuth.getInstance()

    private val database = FirebaseDatabase.getInstance()
    private val match = database.getReference("games/")

    private val isFull = database.getReference("games/matchID/")

    private val letRdRef = database.getReference("games/matchID/rounds/0")

    private val scoresRef = database.getReference("games/matchID/scores")

    var senderID = mAuth.currentUser!!.uid

    var theWord: String = ""
    var activityCount = 0
    val scoreIntentKey = "score"
    var userScore = 0

    var usedTiles = arrayListOf<TextView>()
    var allLetters = arrayListOf<String>()

    var currentTime: Int = 30

    val countDownTimer = object : CountDownTimer(30000, 1000) {
        var mutex = Mutex()
        override fun onTick(millisLeft: Long) {
            currentTime = letterTimer.getText().toString().toInt()
            currentTime -= 1
            letterTimer.text = currentTime.toString()

        }
        override fun onFinish() {
            CoroutineScope(Dispatchers.Main).launch{
                mutex.withLock{
                    //solveLts.performClick()
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letters_rd)
        if (intent.getStringExtra("flag") == "letters") {
            setTitle("Letters Round")
            //controlFlowFlag = "x"
            Toast.makeText(this, "letters", Toast.LENGTH_LONG).show()
        }
        else if (intent.getStringExtra("flag") == "conundrum"){
            setTitle("Conundrum Round")
            //controlFlowFlag = "y"
            Toast.makeText(this, "conundrum", Toast.LENGTH_LONG).show()
        }


        setUp()
        var trie:LettersSolver.Trie
        var letters:String
        CoroutineScope(Dispatchers.Main).launch {
            trie = checkUserWord(usedTiles)
            findSolution(trie, allLetters)
        }

    }

    fun checkOpponentSolution():String{
        var opponentSolution = ""
        //
        letRdRef.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(dataSnap: DataSnapshot) {
                for (child in dataSnap.children){
                    if (child.key != senderID){
                        opponentSolution = child.value.toString()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "error is: $error")
            }
        })
        return opponentSolution
    }

    fun checkAvailableGames(){
        Toast.makeText(this, "wwwhat", Toast.LENGTH_LONG).show()
        val fullValue = ""
        isFull.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(databaseSnap: DataSnapshot) {
                for (child in databaseSnap.children){
                    Log.d(TAG, "child is $child")
                    Toast.makeText(this@LettersRdActivity, "DB says: $child", Toast.LENGTH_LONG).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "error is $error")
            }
        })

    }

    fun sendUserSolution(word: String){

        letRdRef.child(senderID).setValue(word)
    }

    fun checkMultiplayer(intent: Intent, word: String){
        val isMultiplayer = intent.getStringExtra("Multiplayer")
        Toast.makeText(this, "checking multiplayer", Toast.LENGTH_LONG).show()
        if (isMultiplayer == "true"){

            sendUserSolution(word)
            writeScore(word)
        }
    }

    fun writeScore(word:String){
        var score = createScore(word)
        scoresRef.child(senderID).setValue(score)
    }

    fun gameHandler(){
        val gameType = intent.getStringExtra("gameType")
        if (gameType == "multiplayer"){
            startMultiPlayer()
        }
        startNormalGame()
    }

    fun startMultiPlayer(){
        val multiPlayerV = "MultiplayerLetters"
        val multiPlayerT = "gameType"
        solveLts.setOnClickListener {
            val intent = Intent(this, NumbersRdActivity::class.java)
            intent.putExtra(multiPlayerT,multiPlayerV)
            intent.putExtra(scoreIntentKey, userScore)
            startActivity(intent)
        }
    }

    fun startNormalGame(){
        val gameTypeT = "gameType"
        val gameTypeV = "NormalGameLetters"
        solveLts.setOnClickListener {
            val intent = Intent(this, NumbersRdActivity::class.java)
            intent.putExtra(gameTypeT, gameTypeV)
            intent.putExtra(scoreIntentKey, userScore)
            startActivity(intent)
        }
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

    fun setListenersOnTiles(){
        lt1.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt1)
            var clipdata = ClipData.newPlainText("", "  ")
            lt1.startDrag(clipdata, dragShadow, lt1, 0)
            Toast.makeText(this, "is this what? ${lt1.text}", Toast.LENGTH_LONG).show()
            true
        }
        lt2.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt2)
            var clipdata = ClipData.newPlainText("", "  ")
            lt2.startDrag(clipdata, dragShadow, lt2, 0)
            Toast.makeText(this, "is this what? ${lt2.text}", Toast.LENGTH_LONG).show()
            true
        }
        lt3.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt3)
            var clipdata = ClipData.newPlainText("", "  ")
            lt3.startDrag(clipdata, dragShadow, lt3, 0)
            Toast.makeText(this, "is this what? ${lt3.text}", Toast.LENGTH_LONG).show()
            true
        }
        lt4.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt4)
            var clipdata = ClipData.newPlainText("", "  ")
            lt4.startDrag(clipdata, dragShadow, lt4, 0)
            Toast.makeText(this, "is this what? ${lt4.text}", Toast.LENGTH_SHORT).show()
            true
        }
        lt5.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt5)
            var clipdata = ClipData.newPlainText("", "  ")
            lt5.startDrag(clipdata, dragShadow, lt5, 0)
            Toast.makeText(this, "is this what? ${lt5.text}", Toast.LENGTH_SHORT).show()
            true
        }
        lt6.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt6)
            var clipdata = ClipData.newPlainText("", "  ")
            lt6.startDrag(clipdata, dragShadow, lt6, 0)
            Toast.makeText(this, "is this what? ${lt6.text}", Toast.LENGTH_SHORT).show()
            true
        }
        lt7.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt7)
            var clipdata = ClipData.newPlainText("", "  ")
            lt7.startDrag(clipdata, dragShadow, lt7, 0)
            Toast.makeText(this, "is this what? ${lt7.text}", Toast.LENGTH_SHORT).show()
            true
        }
        lt8.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt8)
            var clipdata = ClipData.newPlainText("", "  ")
            lt8.startDrag(clipdata, dragShadow, lt8, 0)
            Toast.makeText(this, "is this what? ${lt8.text}", Toast.LENGTH_SHORT).show()
            true
        }
        lt9.setOnLongClickListener {
            var dragShadow = View.DragShadowBuilder(lt9)
            var clipdata = ClipData.newPlainText("", "  ")
            lt9.startDrag(clipdata, dragShadow, lt9, 0)
            Toast.makeText(this, "is this what? ${lt9.text}", Toast.LENGTH_LONG).show()
            true
        }

    }

    fun setUp(){
        clearLtTile.setVisibility(View.INVISIBLE)
        clearLtTile.setEnabled(false)
        clearAllLtButton.setVisibility(View.INVISIBLE)
        clearAllLtButton.setEnabled(false)
        checkAvailableGames()
        var vowels = arrayListOf<String>("A", "E", "I", "O", "U")
        var consonants = arrayListOf<String>("B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z")
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
                allLetters.add(vowel.trim())
                ltF.text = vowel.toString()
                counter += 1
                if (counter == 9){
                    countDownTimer.start()
                    vowelButton.setVisibility(View.INVISIBLE)
                    consonantButton.setVisibility(View.INVISIBLE)
                    vowelButton.setEnabled(false)
                    consonantButton.setEnabled(false)
                }
            }
        }
        consonantButton.setOnClickListener {
            Log.d(TAG, "AllLetters is ${allLetters.toString()}")
            if (counter < 9) {
                var index = Random().nextInt(consonants.size)
                var consonant = consonants.get(index)
                var ltF = assign(counter)
                ltF.text = consonant.toString()
                allLetters.add(consonant.toString().trim())
                counter += 1
                if (counter == 9){
                    countDownTimer.start()
                    vowelButton.setVisibility(View.INVISIBLE)
                    consonantButton.setVisibility(View.INVISIBLE)
                    vowelButton.setEnabled(false)
                    consonantButton.setEnabled(false)
                }
            }
        }

        setListenersOnTiles()

        var dragCount = 0
        //usedTiles =
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
                        view.setBackgroundColor(Color.parseColor("#add8e6"))
                    }
                    DragEvent.ACTION_DROP -> {
                        clearLtTile.setVisibility(View.VISIBLE)
                        clearLtTile.setEnabled(true)
                        clearAllLtButton.setVisibility(View.VISIBLE)
                        clearAllLtButton.setEnabled(true)
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
                        view.setBackgroundColor(Color.parseColor("#e0ffff"))
                    }
                    DragEvent.ACTION_DRAG_EXITED -> {
                        view.setBackgroundColor(Color.parseColor("#add8e6"))
                    }
                    DragEvent.ACTION_DRAG_ENDED -> {
                        view.setBackgroundColor(Color.parseColor("#47D1D4"))
                    }
                    else -> {}

                }

            }
            true

        }

        clearAllLts()
        letterTargetField.setOnDragListener(drag)

    }

    suspend fun checkUserWord(usedTiles:ArrayList<TextView>):LettersSolver.Trie{
        var trie = withContext(Dispatchers.Main){
            return@withContext getTrie().await()
        }

<<<<<<< HEAD
        var btnSolv = findViewById(R.id.solveLts) as Button

        btnSolv.setOnClickListener {
=======
        solveLts.setOnClickListener {
>>>>>>> 62467d34e960d9e38620767015874846239cec15
            countDownTimer.cancel()
            var word = ""
            for (tile in usedTiles){
                word += tile.text
            }
            theWord = word
            checkMultiplayer(intent, theWord)
            var exists = trie.search_word(word)
<<<<<<< HEAD
           // Toast.makeText(this, "Your $word exists: $exists", Toast.LENGTH_LONG).show()
           // Toast.makeText(this, "ON Click", Toast.LENGTH_LONG).show()

            CoroutineScope(Dispatchers.Main).launch{
                val mutex = Mutex()
                mutex.withLock {
                    var sol =  findSolution(trie, allTiles).second
                    Toast.makeText(this@LettersRdActivity, "best Solution is: $sol", Toast.LENGTH_LONG).show()
                }

            }
            val letterSolution = arrayListOf<TextView>(movedLt1,movedLt2,movedLt3,movedLt4,movedLt5,movedLt6,movedLt7,movedLt8,movedLt9)
            val intent = Intent(this, LettersRdActivity2 ::class.java)
            var s:String = ""
            for (letter in letterSolution){
                if (letter.getText() != ""){
                    s += letter.getText().toString()
                }
            }
            intent.putExtra("playerLetterSolution", s)
            intent.putExtra("exists",exists)
            startActivity(intent)
=======
            if (exists){
                Toast.makeText(this, "The word $word exists.", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this, "The word $word doesn't exist.", Toast.LENGTH_LONG).show()
            }
>>>>>>> 62467d34e960d9e38620767015874846239cec15

            val letterSolution = arrayListOf<TextView>(movedLt1,movedLt2,movedLt3,movedLt4,movedLt5,movedLt6,movedLt7,movedLt8,movedLt9)
            startNextRound(word)
        }
        return trie

    }

    fun startNextRound(word: String){
        //val currentRound = intent.getStringExtra("flag")
        //val isMultiPlayer = intent.getStringExtra("Multiplayer")
        val intent = Intent(this, LettersRdActivity2 ::class.java)

        var opponentSol = checkOpponentSolution()

        intent.putExtra("opsolution", opponentSol)
        intent.putExtra("solution", word)
        //if (isMultiPlayer.toBoolean()){
        //    intent.putExtra("Multiplayer", "true")
        //}
        //intent.putExtra("score", userScore)
        //if (currentRound == "letters"){
       //     intent.putExtra("flag", "letters")
        //} else if (currentRound == "conundrum"){
        //    intent.putExtra("flag", "conundrum")
        //}



        startActivity(intent)
    }

    fun createScore(word: String):Int{
        when (word.length){
            5 -> return 5
            6 -> return 12
            7 -> return 21
            8 -> return 32
            9 -> return 45
            else -> {
                return 4
            }
        }
    }

    fun findSolution(trie:LettersSolver.Trie, allLetters:ArrayList<String>):String{
        // [O, E, L, Y, Z, Q, J, S]
        for (let1 in allLetters){
            for (let2 in allLetters){
                if (trie.search_word(let1+let2)){
                    var twoLetW = let1+let2
                    var minusTwo = 
                    for (let3 in allLetters){

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

    suspend fun getNineLetterWords(words:List<String>):ArrayList<String>{
        val nineLetterWords = arrayListOf<String>()
        val mutex = Mutex()
        mutex.withLock {
            withContext(Dispatchers.IO){
                for (word in words){
                    if (word.trim().length == 9){
                        nineLetterWords.add(word)
                        Log.d(TAG, "Nine letter word: $word")
                    }
                }
            }

        }
        Log.d(TAG, "There are ${nineLetterWords.size} nine letter words")
        return nineLetterWords
    }

    suspend fun createPopulatedTrie(words:List<String>):LettersSolver.Trie{
        val mutex = Mutex()
        mutex.withLock {
            val t = LettersSolver.Trie()
            withContext(Dispatchers.Default){
                for (word in words){
                    //Log.d(TAG, "ADDING $word to Trie. SIZE is: ${word.trim().length}")
                    t.add_word(word.trim())
                    //Log.d(TAG, "man: AARDVARK :${t.search_word("AARDVARK".trim())}")
                    //Log.d(TAG, "man: AARONITE :${t.search_word("AARONITE".trim())}")
                }
            }
            return t
        }
    }

    suspend fun getTrie():Deferred<LettersSolver.Trie>{
        var populatedTrie = withContext(Dispatchers.Main){
            //running on separate thread
            var trie = async { createPopulatedTrie(readTxtFile()) }
            var size = trie.await().getTrieSize()

            Toast.makeText(this@LettersRdActivity, "Trie has $size many items", Toast.LENGTH_LONG).show()

            //var exists = trie.await().search_word("AARDVARK".trim())
            //var exits = trie.await().search_word("AARONITE".trim())

            //Toast.makeText(this@LettersRdActivity, "AARDVARK exists: $exists", Toast.LENGTH_LONG).show()
            //Toast.makeText(this@LettersRdActivity, "AARONITE exists: $exits", Toast.LENGTH_LONG).show()

            return@withContext trie
        }
        return populatedTrie
    }


    companion object{
        private val TAG = "LettersRound"
    }
}
