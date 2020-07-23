package ua.ck.zabochen.android.drawview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    private fun initUi() {
        btnCanvas.setOnClickListener {
            startActivity(Intent(this, CanvasActivity::class.java))
        }

        btnPaint.setOnClickListener {
            startActivity(Intent(this, PaintActivity::class.java))
        }
    }
}