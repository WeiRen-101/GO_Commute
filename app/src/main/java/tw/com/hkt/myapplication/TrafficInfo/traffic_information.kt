package tw.com.hkt.myapplication.TrafficInfo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.Maps.MapsWarning_dynamic
import tw.com.hkt.myapplication.TrafficInfo.LargeCar.LargeCar
import tw.com.hkt.myapplication.TrafficInfo.LawSearch.law_search
import tw.com.hkt.myapplication.TrafficInfo.StatisticView.statistic
import tw.com.hkt.myapplication.databinding.ActivityTrafficInformationBinding

class traffic_information : AppCompatActivity() {
    private lateinit var binding: ActivityTrafficInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrafficInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.cardview1.setOnClickListener{       //統計圖表
            val intent = Intent(this, statistic::class.java)
            startActivity(intent)
        }
        binding.cardview2.setOnClickListener {      //交通法規查詢
            val intent = Intent(this, law_search::class.java)
            startActivity(intent)
        }
        binding.cardview3.setOnClickListener {      //靜態警示系統
            val intent = Intent(this, MapsWarning_dynamic::class.java)
            startActivity(intent)
        }
        binding.cardview4.setOnClickListener {      //砂石車重點資訊
            val intent = Intent(this, LargeCar::class.java)
            startActivity(intent)
        }
        binding.imageButton.setOnClickListener {    //返回鍵
            finish()
        }



    }
}