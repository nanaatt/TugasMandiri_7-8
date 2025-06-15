package com.project.tugaslanjutan

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PostDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "PostDB"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "posts"
        const val COLUMN_ID = "id"
        const val COLUMN_TEXT = "text"
        const val COLUMN_TIMESTAMP = "timestamp"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TEXT TEXT,
                $COLUMN_TIMESTAMP DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertPost(text: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TEXT, text)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllPosts(): List<String> {
        val posts = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_TEXT FROM $TABLE_NAME ORDER BY $COLUMN_TIMESTAMP DESC", null)
        cursor.use {
            while (it.moveToNext()) {
                posts.add(it.getString(0))
            }
        }
        return posts
    }
}