package tw.com.hkt.myapplication.Member

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.MainActivity
import tw.com.hkt.myapplication.R
import tw.com.hkt.myapplication.databinding.ActivityLoginedMemberBinding
import tw.com.hkt.myapplication.setting

class logined_member : AppCompatActivity() {
    private lateinit var binding: ActivityLoginedMemberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginedMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the selected image from the intent
        val selectedImage = intent.getStringExtra("selected_image")
        Log.d("LoginedMember", "Selected Image: $selectedImage") // Debugging

        val imageResId = when (selectedImage) {
            "spongebob" -> R.drawable.spongebob_squarepants
            "gray" -> R.drawable.gray
            "krab" -> R.drawable.krab
            else -> {
                Log.e("LoginedMember", "Unknown image: $selectedImage")
                R.drawable.icons8_member_24 // Ensure this resource exists or handle the case appropriately
            }
        }
        binding.imageView2.setImageResource(imageResId)

        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

//        binding.button36.setOnClickListener {
//            val intent = Intent(this, customize::class.java)
//            startActivity(intent)
//        }

//         Logout functionality
        binding.button2.setOnClickListener {
            editor.putBoolean("is_logged_in", false)
            editor.apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
//
        binding.TextView1.setOnClickListener{
            val intent = Intent(this, customize::class.java)
            startActivity(intent)
        }
//        binding.button26.setOnClickListener {
//            val intent = Intent(this, setting::class.java)
//            startActivity(intent)
//        }f
//
        //個人化設定
//        binding.TextView1.setOnClickListener {
//
//        }
        binding.imageButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

