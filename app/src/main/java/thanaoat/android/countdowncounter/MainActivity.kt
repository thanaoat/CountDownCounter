package thanaoat.android.countdowncounter

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

private const val TAG = "MainActivity"
private const val KEY_COUNT = "count"

class MainActivity : AppCompatActivity() {
    private var count = 0
    private val mp: MediaPlayer by lazy {
        MediaPlayer.create(this, R.raw.gong)
    }

    private lateinit var btnCountDown: Button
    private lateinit var etCount: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        count = savedInstanceState?.getInt(KEY_COUNT, 0) ?: 0

        btnCountDown = findViewById(R.id.btnCountDown)
        etCount = findViewById(R.id.etCount)

        btnCountDown.setOnClickListener {
            count -= 1

            if (count <= 0) {
                playSound()
            }
            updateTvCount()
        }

        etCount.setText(count.toString())
        val countWatcher = object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    this@MainActivity.count = s.toString().toInt()
                } catch (e: NumberFormatException) {
                    this@MainActivity.count = 0
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        }
        etCount.addTextChangedListener(countWatcher)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNT, count)
    }

    private fun updateTvCount() {
        etCount.setText(count.toString())
    }

    private fun playSound() {
        mp.seekTo(0)
        mp.start()
    }
}
