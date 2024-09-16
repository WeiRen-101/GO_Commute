package tw.com.hkt.myapplication.TrafficInfo.StatisticView

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.R
import tw.com.hkt.myapplication.databinding.ActivityVehicalBinding


class vehical : AppCompatActivity() {
    private lateinit var binding: ActivityVehicalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener { // 退出
            finish()
        }

        // 定義 Spinner 1 的選項列表
        val spinner1Items = arrayOf("2019", "2020", "2021", "2022", "2023")
        val adapter1 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            spinner1Items
        )
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner1.adapter = adapter1

        // 設置 Spinner 的選擇事件監聽器
        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateImage() // 更新圖片
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }

    private fun updateImage() {
        val selectedYear = binding.spinner1.selectedItem.toString()

        // 更新 imageView4 的圖片
        val imageResId4 = when (selectedYear) {
            "2019" -> R.drawable._019v // 替換成你相應的圖片資源ID
            "2020" -> R.drawable._020v
            "2021" -> R.drawable._021v
            "2022" -> R.drawable._022v
            "2023" -> R.drawable._023v
            else -> R.drawable.btn_shape_gray // 默认圖片
        }

        // 更新 imageView5 的圖片
        val imageResId5 = when (selectedYear) {
            "2019" -> R.drawable._019vf // 替換成你相應的圖片資源ID
            "2020" -> R.drawable._020vf
            "2021" -> R.drawable._021vf
            "2022" -> R.drawable._022vf
            "2023" -> R.drawable._023vf
            else -> R.drawable.btn_shape_gray // 默认圖片
        }

        // 設置圖片到相應的 ImageView
        binding.imageView4.setImageResource(imageResId4)
        binding.imageView5.setImageResource(imageResId5)
    }
}