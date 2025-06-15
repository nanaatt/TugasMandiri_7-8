package com.project.tugaslanjutan

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class PostListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        val dbHelper = PostDatabaseHelper(this)
        val posts = dbHelper.getAllPosts().toMutableList() // FIX: ubah jadi MutableList

        if (posts.isEmpty()) {
            posts.add("Belum ada postingan.") // Sekarang .add tidak error
        }

        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, posts)
    }
}