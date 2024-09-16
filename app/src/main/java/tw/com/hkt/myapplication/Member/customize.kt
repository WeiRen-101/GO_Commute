package tw.com.hkt.myapplication.Member

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import tw.com.hkt.myapplication.R

class customize : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_customize)

        // Configure the Spinner
        val spinner: Spinner = findViewById(R.id.spinner2)
        val items = listOf("spongebob", "gray", "krab")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Button to send the selected item to the logined_member Activity
        val button16: Button = findViewById(R.id.button16)
        button16.setOnClickListener {
            val selectedItem = spinner.selectedItem.toString()
            val intent = Intent(this, logined_member::class.java).apply {
                putExtra("selected_image", selectedItem)
            }
            startActivity(intent)
        }

        // Apply window insets for edge-to-edge mode
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
