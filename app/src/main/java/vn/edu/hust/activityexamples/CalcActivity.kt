package vn.edu.hust.activityexamples

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalcActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_calc)

    val value1 = intent.getStringExtra("param1")
    val value2 = intent.getStringExtra("param2")

    val textResult = findViewById<TextView>(R.id.text_result)
    if (value1 == null || value2 == null) {
      textResult.text = "Cannot add"
      setResult(Activity.RESULT_CANCELED)
    } else if (value1.isEmpty() || value2.isEmpty()) {
      textResult.text = "Cannot add"
      setResult(Activity.RESULT_CANCELED)
    } else {
      val result = value1.toInt() + value2.toInt()
      textResult.text = "$value1 + $value2 = $result"
      intent.putExtra("result", result)
      setResult(Activity.RESULT_OK, intent)
    }

    findViewById<Button>(R.id.button_close).setOnClickListener {
      finish()
    }
  }
}