package thanaoat.android.countdowncounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var count = 26

    private lateinit var tvCount: TextView
    private lateinit var btnCountDown: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCount = findViewById(R.id.tvCount)
        btnCountDown = findViewById(R.id.btnCountDown)

        tvCount.text = count.toString()
        btnCountDown.setOnClickListener {
            count -= 1

            // TODO: Play sound
            updateTvCount()
        }

    }

    private fun updateTvCount() {
        tvCount.setText(count.toString())
    }
}
