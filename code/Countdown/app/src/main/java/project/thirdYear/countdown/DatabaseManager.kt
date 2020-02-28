package project.thirdYear.countdown

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.provider.BaseColumns
import android.util.Log
import androidx.annotation.RequiresApi
import project.thirdYear.countdown.DatabaseManager.Words.WORDS_TABLE
import project.thirdYear.countdown.DatabaseManager.Words.COLUMN_NAME
import project.thirdYear.countdown.DatabaseManager.Words.DATABASE_NAME
import project.thirdYear.countdown.DatabaseManager.Words.DATABASE_VERSION
import java.io.BufferedReader
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths


class DatabaseManager (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

     object Words:BaseColumns{
        public final val DATABASE_NAME = "WordsDB"
        public final val WORDS_TABLE = "AllWords"
        public final val COLUMN_NAME = "words"
        public final val DATABASE_VERSION = 1
    }

    object Queries{
        val CREATE_TABLE = ("CREATE TABLE ${Words.WORDS_TABLE} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY" +
                "${Words.COLUMN_NAME} TEXT" + ")")
        val DROP_TABLE = "DROP TABLE IF EXISTS ${Words.WORDS_TABLE}"
    }

    override fun onCreate(db: SQLiteDatabase){
        db.execSQL(Queries.CREATE_TABLE)
        }
    private fun insertData(word: String){
        val onFail:Int = -1
        val db = this.writableDatabase
        val entry = ContentValues()
        entry.put(Words.COLUMN_NAME,word)
        val result = db.insert(WORDS_TABLE,null, entry)
        if (result == onFail.toLong()){
            Log.d(TAG, "Insert failed")
        }else{
            Log.d(TAG, "Insert successful")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun populateDB(){
        val path = Paths.get("C:\\Users\\George Burac\\Desktop\\p2\\NSD\\Year 3\\Year\\words.txt").toAbsolutePath().toString()
        var textBuffer:BufferedReader = File(path).bufferedReader()
        for (word:String in textBuffer.readLines()){
            insertData(word)
        }

    }

    fun getWords(word:String):List<String>{
        val wordList:MutableList<String> = ArrayList<String>()
        val wordsQuery = "SELECT ${Words.COLUMN_NAME} as words FROM ${Words.WORDS_TABLE}"
        var db = readableDatabase

        var resultSet = db.rawQuery(wordsQuery, null)
        while (resultSet.moveToNext()){
            wordList.add(resultSet.getString(resultSet.getColumnIndex(Words.COLUMN_NAME)))
        }
        return wordList

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

    companion object{
        private val TAG = "DATABASE"
    }
}