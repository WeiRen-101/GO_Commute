package tw.com.hkt.myapplication.TrafficInfo.StatisticView
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.R
import tw.com.hkt.myapplication.databinding.ActivityAreaBinding


class area : AppCompatActivity() {
    private lateinit var binding: ActivityAreaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAreaBinding.inflate(layoutInflater)
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

        // 定義 Spinner 2 的選項列表
        val spinner2Items = arrayOf("花蓮")
        val adapter2 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            spinner2Items
        )
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner2.adapter = adapter2

        // 設置選擇監聽器來更新圖片
        val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateImage()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 無需操作
            }
        }

        binding.spinner1.onItemSelectedListener = onItemSelectedListener
        binding.spinner2.onItemSelectedListener = onItemSelectedListener
    }

    private fun updateImage() {
        val selectedYear = binding.spinner1.selectedItem.toString()
        val selectedArea = binding.spinner2.selectedItem.toString()

        // 主要的 ImageView4 的圖片更新
        val imageResId = when {
            selectedYear == "2019" && selectedArea == "花蓮" -> R.drawable._019a
            selectedYear == "2020" && selectedArea == "花蓮" -> R.drawable._020a
            selectedYear == "2021" && selectedArea == "花蓮" -> R.drawable._021a
            selectedYear == "2022" && selectedArea == "花蓮" -> R.drawable._022a
            selectedYear == "2023" && selectedArea == "花蓮" -> R.drawable._023a
            else -> R.drawable.btn_shape_gray // 預設圖片
        }

        // 第二個 ImageView3 的圖片更新
        val imageResId2 = when {
            selectedYear == "2019" && selectedArea == "花蓮" -> R.drawable._019af // 假設不同圖片
            selectedYear == "2020" && selectedArea == "花蓮" -> R.drawable._020af
            selectedYear == "2021" && selectedArea == "花蓮" -> R.drawable._021af
            selectedYear == "2022" && selectedArea == "花蓮" -> R.drawable._022af
            selectedYear == "2023" && selectedArea == "花蓮" -> R.drawable._023af
            else -> R.drawable.btn_shape_gray // 預設圖片
        }

        // 更新兩個 ImageView 的圖片
        binding.imageView4.setImageResource(imageResId)  // 更新主要圖片
        binding.imageView3.setImageResource(imageResId2) // 更新第二個圖片
    }
}