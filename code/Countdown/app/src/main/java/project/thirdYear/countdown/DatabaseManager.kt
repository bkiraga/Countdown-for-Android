package project.thirdYear.countdown

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.widget.Toast
import project.thirdYear.countdown.DatabaseManager.Words.WORDS_TABLE
import project.thirdYear.countdown.DatabaseManager.Words.DATABASE_NAME
import project.thirdYear.countdown.DatabaseManager.Words.DATABASE_VERSION

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

     object Words:BaseColumns{
        public final val DATABASE_NAME = "WordsDB"
        public final val WORDS_TABLE = "AllWords"
        public final val COLUMN_NAME = "words"
        public final val DATABASE_VERSION = 1
    }

    object Queries{
        val CREATE_TABLE = ("CREATE TABLE ${Words.WORDS_TABLE} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Words.COLUMN_NAME}" + " VARCHAR(256)"+ ")")
        val DROP_TABLE = "DROP TABLE IF EXISTS ${Words.WORDS_TABLE}"
    }

    override fun onCreate(db: SQLiteDatabase){
        db.execSQL(Queries.DROP_TABLE)
    }
/*
    public fun insertData(word:String):Long{
        val onFail:Int = -1
        val db:SQLiteDatabase = this.writableDatabase

        val dataEntry = ContentValues()

        dataEntry.put(Words.COLUMN_NAME,word)

        val result = db.insert(WORDS_TABLE,null, dataEntry)

        return result
    }

 */

    public fun readData():MutableList<String> {
        val db = this.readableDatabase

        val query = "Select ${Words.COLUMN_NAME} from ${Words.WORDS_TABLE}"

        var resultSet : MutableList<String> = ArrayList()

        var cursor = db.rawQuery(query, null)
        while(cursor.moveToNext()){

            var result = cursor.getString(cursor.getColumnIndex(Words.COLUMN_NAME))
            resultSet.add(result)
        }
        return resultSet
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

    companion object{
        private val TAG = "DatabaseManager"
    }
}