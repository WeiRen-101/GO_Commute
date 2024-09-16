package tw.com.hkt.myapplication.TrafficInfo.StatisticView

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.R
import tw.com.hkt.myapplication.databinding.ActivityZhaoshiBinding


class Zhaoshi : AppCompatActivity() {
    private lateinit var binding: ActivityZhaoshiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityZhaoshiBinding.inflate(layoutInflater)
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
        val spinner2Items = arrayOf("全部", "機車", "小型車", "大型車", "人", "慢車")
        val adapter2 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            spinner2Items
        )
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner2.adapter = adapter2

        // 設置 Spinner 的選擇事件監聽器
        val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateImages() // 更新圖片
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        binding.spinner1.onItemSelectedListener = onItemSelectedListener
        binding.spinner2.onItemSelectedListener = onItemSelectedListener
    }

    private fun updateImages() {
        val selectedYear = binding.spinner1.selectedItem.toString()
        val selectedType = binding.spinner2.selectedItem.toString()

        // 根據年份和類型選擇對應的圖片資源 ID
        val imageResId4 = when {
            selectedYear == "2019" && selectedType == "全部" -> R.drawable._019z1
            selectedYear == "2020" && selectedType == "全部" -> R.drawable._020z1
            selectedYear == "2021" && selectedType == "全部" -> R.drawable._021z1
            selectedYear == "2022" && selectedType == "全部" -> R.drawable._022z1
            selectedYear == "2023" && selectedType == "全部" -> R.drawable._023z1

            selectedYear == "2019" && selectedType == "機車" -> R.drawable._019z2
            selectedYear == "2020" && selectedType == "機車" -> R.drawable._020z2
            selectedYear == "2021" && selectedType == "機車" -> R.drawable._021z2
            selectedYear == "2022" && selectedType == "機車" -> R.drawable._022z2
            selectedYear == "2023" && selectedType == "機車" -> R.drawable._023z2

            selectedYear == "2019" && selectedType == "小型車" -> R.drawable._019z5
            selectedYear == "2020" && selectedType == "小型車" -> R.drawable._020z5
            selectedYear == "2021" && selectedType == "小型車" -> R.drawable._021z5
            selectedYear == "2022" && selectedType == "小型車" -> R.drawable._022z5
            selectedYear == "2023" && selectedType == "小型車" -> R.drawable._023z5

            selectedYear == "2019" && selectedType == "大型車" -> R.drawable._019z6
            selectedYear == "2020" && selectedType == "大型車" -> R.drawable._020z6
            selectedYear == "2021" && selectedType == "大型車" -> R.drawable._021z6
            selectedYear == "2022" && selectedType == "大型車" -> R.drawable._022z6
            selectedYear == "2023" && selectedType == "大型車" -> R.drawable._023z6

            selectedYear == "2019" && selectedType == "人" -> R.drawable._019z4
            selectedYear == "2020" && selectedType == "人" -> R.drawable._020z4
            selectedYear == "2021" && selectedType == "人" -> R.drawable._021z4
            selectedYear == "2022" && selectedType == "人" -> R.drawable._022z4
            selectedYear == "2023" && selectedType == "人" -> R.drawable._023z4

            selectedYear == "2019" && selectedType == "慢車" -> R.drawable._019z3
            selectedYear == "2020" && selectedType == "慢車" -> R.drawable._020z3
            selectedYear == "2021" && selectedType == "慢車" -> R.drawable._021z3
            selectedYear == "2022" && selectedType == "慢車" -> R.drawable._022z3
            selectedYear == "2023" && selectedType == "慢車" -> R.drawable._023z3

            else -> R.drawable.btn_shape_gray // 預設圖片
        }

        // 根據年份和類型選擇對應的圖片資源 ID (後面加 f)
        val imageResId6 = when {
            selectedYear == "2019" && selectedType == "全部" -> R.drawable._019z1f
            selectedYear == "2020" && selectedType == "全部" -> R.drawable._020z1f
            selectedYear == "2021" && selectedType == "全部" -> R.drawable._021z1f
            selectedYear == "2022" && selectedType == "全部" -> R.drawable._022z1f
            selectedYear == "2023" && selectedType == "全部" -> R.drawable._023z1f

            selectedYear == "2019" && selectedType == "機車" -> R.drawable._019z2f
            selectedYear == "2020" && selectedType == "機車" -> R.drawable._020z2f
            selectedYear == "2021" && selectedType == "機車" -> R.drawable._021z2f
            selectedYear == "2022" && selectedType == "機車" -> R.drawable._022z2f
            selectedYear == "2023" && selectedType == "機車" -> R.drawable._023z2f

            selectedYear == "2019" && selectedType == "小型車" -> R.drawable._019z3f
            selectedYear == "2020" && selectedType == "小型車" -> R.drawable._020z3f
            selectedYear == "2021" && selectedType == "小型車" -> R.drawable._021z3f
            selectedYear == "2022" && selectedType == "小型車" -> R.drawable._022z3f
            selectedYear == "2023" && selectedType == "小型車" -> R.drawable._023z3f

            selectedYear == "2019" && selectedType == "大型車" -> R.drawable._019z4f
            selectedYear == "2020" && selectedType == "大型車" -> R.drawable._020z4f
            selectedYear == "2021" && selectedType == "大型車" -> R.drawable._021z4f
            selectedYear == "2022" && selectedType == "大型車" -> R.drawable._022z4f
            selectedYear == "2023" && selectedType == "大型車" -> R.drawable._023z4f

            selectedYear == "2019" && selectedType == "人" -> R.drawable._019z5f
            selectedYear == "2020" && selectedType == "人" -> R.drawable._020z5f
            selectedYear == "2021" && selectedType == "人" -> R.drawable._021z5f
            selectedYear == "2022" && selectedType == "人" -> R.drawable._022z5f
            selectedYear == "2023" && selectedType == "人" -> R.drawable._023z5f

            selectedYear == "2019" && selectedType == "慢車" -> R.drawable._019z6f
            selectedYear == "2020" && selectedType == "慢車" -> R.drawable._020z6f
            selectedYear == "2021" && selectedType == "慢車" -> R.drawable._021z6f
            selectedYear == "2022" && selectedType == "慢車" -> R.drawable._022z6f
            selectedYear == "2023" && selectedType == "慢車" -> R.drawable._023z6f

            else -> R.drawable.btn_shape_gray // 預設圖片
        }

        // 為 imageView4 和 imageView6 設置對應的圖片
        binding.imageView4.setImageResource(imageResId4)
        binding.imageView6.setImageResource(imageResId6) // imageView6 設置不同的圖片
    }
}
