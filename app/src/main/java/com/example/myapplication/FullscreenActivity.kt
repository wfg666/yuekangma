package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat



/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView

    @SuppressLint("ClickableViewAccessibility", "SdCardPath")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen)

        val hasWriteExternalPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (hasWriteExternalPermission != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "need READ_EXTERNAL_STORAGE permission", Toast.LENGTH_LONG).show()
        }

        val testTimeText:TextView = findViewById(R.id.testTimeText)
        val testTime = System.currentTimeMillis() - 51123456
        testTimeText.text = DateFormat.format("yyyy-MM-dd HH:mm", testTime)

        videoView = findViewById(R.id.videoView)
        videoView.setVideoPath("/sdcard/code/ykm.mp4")

        videoView.setOnPreparedListener { videoView.start() }
        videoView.setOnCompletionListener{ videoView.start() }

        videoView.requestFocus()

        videoView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

}