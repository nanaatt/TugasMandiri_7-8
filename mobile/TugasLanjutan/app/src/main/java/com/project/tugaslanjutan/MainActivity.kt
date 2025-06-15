package com.project.tugaslanjutan

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengaktifkan UI full-screen edge-to-edge
        enableEdgeToEdge()

        // Set layout utama
        setContentView(R.layout.activity_main)

        // Sembunyikan ActionBar (jika ingin tampilan minimalis)
        // supportActionBar?.hide()

        // Terapkan padding sesuai dengan system bar (status bar & navigation bar)
        val rootView = findViewById<View>(R.id.main)
        rootView?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}
