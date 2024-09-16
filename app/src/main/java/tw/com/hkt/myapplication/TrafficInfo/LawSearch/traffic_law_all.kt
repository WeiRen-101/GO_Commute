package tw.com.hkt.myapplication.TrafficInfo.LawSearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.databinding.ActivityTrafficLawAllBinding

class traffic_law_all : AppCompatActivity() {
    private lateinit var binding: ActivityTrafficLawAllBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrafficLawAllBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener{    //退出
            finish()
        }
    }
}