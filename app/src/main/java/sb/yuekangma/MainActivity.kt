package sb.yuekangma

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.WindowInsets
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("ClickableViewAccessibility", "SdCardPath")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // check write external storage permission
        val hasWriteExternalPermission =
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (hasWriteExternalPermission != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "need READ_EXTERNAL_STORAGE permission", Toast.LENGTH_LONG).show()
        }

        // set system UIs like bars
        supportActionBar?.hide()
        window.setDecorFitsSystemWindows(false)
        window.decorView.windowInsetsController?.show(WindowInsets.Type.statusBars())
        window.decorView.windowInsetsController?.show(WindowInsets.Type.systemBars())
        setContentView(R.layout.main_activity)

        // display date and time text
        val testTimeText:TextView = findViewById(R.id.testTimeText)
        val testTime = System.currentTimeMillis() - 51123456
        testTimeText.text = DateFormat.format("yyyy-MM-dd HH:mm", testTime)

        // play background video
        videoView = findViewById(R.id.videoView)
        videoView.setVideoPath("/sdcard/code/ykm.mp4")
        videoView.setOnPreparedListener { videoView.start() }
        videoView.setOnCompletionListener{ videoView.start() }
        videoView.requestFocus()
    }
}