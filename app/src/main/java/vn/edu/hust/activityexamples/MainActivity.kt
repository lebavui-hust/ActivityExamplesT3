package vn.edu.hust.activityexamples

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

//    supportActionBar?.title = "App Bar"
//    supportActionBar?.setDisplayShowHomeEnabled(true)
//    supportActionBar?.setIcon(R.mipmap.ic_launcher)
//    supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient_background, null))

    findViewById<Button>(R.id.button_open).setOnClickListener {
//      val intent = Intent(this, SecondActivity::class.java)
//      intent.putExtra("param1", 123)
//      intent.putExtra("param2", 3.14)
//      intent.putExtra("param3", "value3")
//      startActivity(intent)


//      val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:098654321"))
//      val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://hust.edu.vn"))
//      val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Hanoi"))

      val intent = Intent(Intent.ACTION_SEND)
      intent.type = "text/plain"
      intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("test@gmail.com"))
      intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject")
      intent.putExtra(Intent.EXTRA_TEXT, "Email content")

      startActivity(intent)
    }

    val textResult = findViewById<TextView>(R.id.text_result)

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
      {
        if (it.resultCode == Activity.RESULT_CANCELED) {
          textResult.text = "Cannot add"
        } else {
          val result = it.data?.getIntExtra("result", 0)
          textResult.text = "Result: $result"
        }
      })

    findViewById<Button>(R.id.button_calc).setOnClickListener {
      val value1 = findViewById<EditText>(R.id.edit_1).text.toString()
      val value2 = findViewById<EditText>(R.id.edit_2).text.toString()
      val intent = Intent(this, CalcActivity::class.java)
      intent.putExtra("param1", value1)
      intent.putExtra("param2", value2)

      launcher.launch(intent)
    // startActivity(intent)
    }

//    val imageView = findViewById<ImageView>(R.id.imageView)
//    registerForContextMenu(imageView)

    val items = mutableListOf<String>()
    for (i in 1..50)
      items.add("Item $i")

    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
    val listView = findViewById<ListView>(R.id.list_view)
    listView.adapter = adapter

    registerForContextMenu(listView)
  }

  override fun onCreateContextMenu(
    menu: ContextMenu?,
    v: View?,
    menuInfo: ContextMenu.ContextMenuInfo?
  ) {
    menuInflater.inflate(R.menu.main_menu, menu)
    super.onCreateContextMenu(menu, v, menuInfo)
  }

  override fun onContextItemSelected(item: MenuItem): Boolean {
    val pos = (item.menuInfo as AdapterContextMenuInfo).position
    when (item.itemId) {
      R.id.action_share -> {Toast.makeText(this, "Share action $pos", Toast.LENGTH_LONG).show()}
      R.id.action_download -> {Toast.makeText(this, "Download action $pos", Toast.LENGTH_LONG).show()}
      R.id.action_settings -> {Toast.makeText(this, "Settings action $pos", Toast.LENGTH_LONG).show()}
    }

    return super.onContextItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_share -> {Toast.makeText(this, "Share action", Toast.LENGTH_LONG).show()}
      R.id.action_download -> {Toast.makeText(this, "Download action", Toast.LENGTH_LONG).show()}
      R.id.action_settings -> {Toast.makeText(this, "Settings action", Toast.LENGTH_LONG).show()}
    }

    return super.onOptionsItemSelected(item)
  }
}