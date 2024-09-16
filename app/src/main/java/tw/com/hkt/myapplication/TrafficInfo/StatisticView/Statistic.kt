package tw.com.hkt.myapplication.TrafficInfo.StatisticView

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.databinding.ActivityStatisticBinding

class statistic : AppCompatActivity() {

    private lateinit var binding: ActivityStatisticBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 返回按鈕點擊事件
        binding.imageButtonReturn.setOnClickListener {
            finish()
        }

        // 跳轉到 yearsold Activity
        binding.cardview1.setOnClickListener {
            val intent = Intent(this, years_old::class.java)
            startActivity(intent)
        }
        // 跳轉到 vehical Activity
        binding.cardview2.setOnClickListener {
            val intent = Intent(this, vehical::class.java)
            startActivity(intent)
        }
        // 跳轉到 area Activity
        binding.cardview3.setOnClickListener {
            val intent = Intent(this, area::class.java)
            startActivity(intent)
        }
        // 跳轉到 zhaoshi Activity
        binding.cardview4.setOnClickListener {
            val intent = Intent(this, Zhaoshi::class.java)
            startActivity(intent)
        }


    }
}