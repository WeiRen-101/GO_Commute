package tw.com.hkt.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tw.com.hkt.myapplication.databinding.ActivityLawSearchBinding
import tw.com.hkt.myapplication.databinding.ActivitySettingBinding

class setting : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener{
            finish()
        }
    }
}