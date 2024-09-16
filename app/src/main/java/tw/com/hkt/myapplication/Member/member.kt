package tw.com.hkt.myapplication.Member

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.databinding.ActivityMemberBinding
import android.content.Context
import tw.com.hkt.myapplication.databinding.ActivityLoginedMemberBinding
import tw.com.hkt.myapplication.setting

class member : AppCompatActivity() {
    private lateinit var binding: ActivityMemberBinding
    private lateinit var binding2: ActivityLoginedMemberBinding
    //private var loginF:Boolean = false  //登入確認號誌
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding2 = ActivityLoginedMemberBinding.inflate(layoutInflater)
        setContentView(binding2.root)    //已登入
        binding = ActivityMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)    //未登入

        //檢查登入狀態
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("is_logged_in", false)

        //測試用
        //Toast.makeText(this, "isLoggedIn2: $isLoggedIn", Toast.LENGTH_SHORT).show()

        if (isLoggedIn) {
            val intent = Intent(this, logined_member::class.java)
            startActivity(intent)
        }

//        binding.button26.setOnClickListener {
//            val intent = Intent(this, setting::class.java)
//            startActivity(intent)
//        }
        binding.button25.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)

        }
        binding.imageButton.setOnClickListener {
            finish()
        }
    }
}
    
