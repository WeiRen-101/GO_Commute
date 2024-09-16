package tw.com.hkt.myapplication.TrafficInfo.StatisticView

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.R
import tw.com.hkt.myapplication.databinding.ActivityYearsOldBinding

class years_old : AppCompatActivity() {

    private lateinit var binding: ActivityYearsOldBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYearsOldBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener { // 退出
            finish()
        }

        // 定義 Spinner 1 的選項列表
        val spinner1Items = arrayOf("2019", "2020", "2021", "2022", "2023")
        val spinner1Adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            spinner1Items
        )
        spinner1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner1.adapter = spinner1Adapter

        // 定義 Spinner 2 的選項列表
        val spinner2Items = arrayOf("全部", "兒童(0~12)", "少年(13~17)", "青年(18~23)", "壯年(24~39)", "中年(40~64)", "老年(65以上)")
        val spinner2Adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            spinner2Items
        )
        spinner2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner2.adapter = spinner2Adapter

        // 設置 Spinner 選擇監聽器
        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateImageViews() // 更新兩個圖片
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateImageViews() // 更新兩個圖片
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    /**
     * 根据 Spinner 的选择更新 ImageView
     */
    private fun updateImageViews() {
        val selectedYear = binding.spinner1.selectedItem.toString()
        val selectedAgeGroup = binding.spinner2.selectedItem.toString()

        // 更新 ImageView4
        val imageResId4 = when {
            selectedYear == "2019" && selectedAgeGroup == "全部" -> R.drawable._019
            selectedYear == "2019" && selectedAgeGroup == "兒童(0~12)" -> R.drawable._019_012
            selectedYear == "2019" && selectedAgeGroup == "少年(13~17)" -> R.drawable._019_1317
            selectedYear == "2019" && selectedAgeGroup == "青年(18~23)" -> R.drawable._019_1823
            selectedYear == "2019" && selectedAgeGroup == "壯年(24~39)" -> R.drawable._019_2439
            selectedYear == "2019" && selectedAgeGroup == "中年(40~64)" -> R.drawable._019_4064
            selectedYear == "2019" && selectedAgeGroup == "老年(65以上)" -> R.drawable._019_65up

            selectedYear == "2020" && selectedAgeGroup == "全部" -> R.drawable._020
            selectedYear == "2020" && selectedAgeGroup == "兒童(0~12)" -> R.drawable._020_012
            selectedYear == "2020" && selectedAgeGroup == "少年(13~17)" -> R.drawable._020_1317 //
            selectedYear == "2020" && selectedAgeGroup == "青年(18~23)" -> R.drawable._020_1823
            selectedYear == "2020" && selectedAgeGroup == "壯年(24~39)" -> R.drawable._020_2439
            selectedYear == "2020" && selectedAgeGroup == "中年(40~64)" -> R.drawable._020_4064
            selectedYear == "2020" && selectedAgeGroup == "老年(65以上)" -> R.drawable._020_65up

            selectedYear == "2021" && selectedAgeGroup == "全部" -> R.drawable._021
            selectedYear == "2021" && selectedAgeGroup == "兒童(0~12)" -> R.drawable._021_012
            selectedYear == "2021" && selectedAgeGroup == "少年(13~17)" -> R.drawable._021_1317
            selectedYear == "2021" && selectedAgeGroup == "青年(18~23)" -> R.drawable._021_1823
            selectedYear == "2021" && selectedAgeGroup == "壯年(24~39)" -> R.drawable._021_2439
            selectedYear == "2021" && selectedAgeGroup == "中年(40~64)" -> R.drawable._021_4064
            selectedYear == "2021" && selectedAgeGroup == "老年(65以上)" -> R.drawable._021_65up

            selectedYear == "2022" && selectedAgeGroup == "全部" -> R.drawable._022
            selectedYear == "2022" && selectedAgeGroup == "兒童(0~12)" -> R.drawable._022_012
            selectedYear == "2022" && selectedAgeGroup == "少年(13~17)" -> R.drawable._022_1317
            selectedYear == "2022" && selectedAgeGroup == "青年(18~23)" -> R.drawable._022_1823
            selectedYear == "2022" && selectedAgeGroup == "壯年(24~39)" -> R.drawable._022_2439
            selectedYear == "2022" && selectedAgeGroup == "中年(40~64)" -> R.drawable._022_4064
            selectedYear == "2022" && selectedAgeGroup == "老年(65以上)" -> R.drawable._022_65up

            selectedYear == "2023" && selectedAgeGroup == "全部" -> R.drawable._023
            selectedYear == "2023" && selectedAgeGroup == "兒童(0~12)" -> R.drawable._023_012
            selectedYear == "2023" && selectedAgeGroup == "少年(13~17)" -> R.drawable._023_1317
            selectedYear == "2023" && selectedAgeGroup == "青年(18~23)" -> R.drawable._023_1823
            selectedYear == "2023" && selectedAgeGroup == "壯年(24~39)" -> R.drawable._023_2439
            selectedYear == "2023" && selectedAgeGroup == "中年(40~64)" -> R.drawable._023_4064
            selectedYear == "2023" && selectedAgeGroup == "老年(65以上)" -> R.drawable._023_65up

            else -> R.drawable.btn_shape_gray // Default case
        }

        // 更新 ImageView3 (可以根據相同或者不同的邏輯來更改圖片)
        val imageResId3 = when {
            selectedYear == "2019" && selectedAgeGroup == "全部" -> R.drawable._019allf
            selectedYear == "2019" && selectedAgeGroup == "兒童(0~12)" -> R.drawable._019012f
            selectedYear == "2019" && selectedAgeGroup == "少年(13~17)" -> R.drawable._0191317f
            selectedYear == "2019" && selectedAgeGroup == "青年(18~23)" -> R.drawable._0191823f
            selectedYear == "2019" && selectedAgeGroup == "壯年(24~39)" -> R.drawable._0192439f
            selectedYear == "2019" && selectedAgeGroup == "中年(40~64)" -> R.drawable._0194064f
            selectedYear == "2019" && selectedAgeGroup == "老年(65以上)" -> R.drawable._01965f

            // 2020
            selectedYear == "2020" && selectedAgeGroup == "全部" -> R.drawable._020allf
            selectedYear == "2020" && selectedAgeGroup == "兒童(0~12)" -> R.drawable._020012f
            selectedYear == "2020" && selectedAgeGroup == "少年(13~17)" -> R.drawable._0201317f
            selectedYear == "2020" && selectedAgeGroup == "青年(18~23)" -> R.drawable._0201823f
            selectedYear == "2020" && selectedAgeGroup == "壯年(24~39)" -> R.drawable._0202439f
            selectedYear == "2020" && selectedAgeGroup == "中年(40~64)" -> R.drawable._0204064f
            selectedYear == "2020" && selectedAgeGroup == "老年(65以上)" -> R.drawable._02065f

            // 2021
            selectedYear == "2021" && selectedAgeGroup == "全部" -> R.drawable._021allf
            selectedYear == "2021" && selectedAgeGroup == "兒童(0~12)" -> R.drawable._021012f
            selectedYear == "2021" && selectedAgeGroup == "少年(13~17)" -> R.drawable._0211317f
            selectedYear == "2021" && selectedAgeGroup == "青年(18~23)" -> R.drawable._0211823f
            selectedYear == "2021" && selectedAgeGroup == "壯年(24~39)" -> R.drawable._0212439f
            selectedYear == "2021" && selectedAgeGroup == "中年(40~64)" -> R.drawable._0214064f
            selectedYear == "2021" && selectedAgeGroup == "老年(65以上)" -> R.drawable._02165f

            // 2022
            selectedYear == "2022" && selectedAgeGroup == "全部" -> R.drawable._022allf
            selectedYear == "2022" && selectedAgeGroup == "兒童(0~12)" -> R.drawable._022012f
            selectedYear == "2022" && selectedAgeGroup == "少年(13~17)" -> R.drawable._0221317f
            selectedYear == "2022" && selectedAgeGroup == "青年(18~23)" -> R.drawable._0221823f
            selectedYear == "2022" && selectedAgeGroup == "壯年(24~39)" -> R.drawable._0222439f
            selectedYear == "2022" && selectedAgeGroup == "中年(40~64)" -> R.drawable._0224064f
            selectedYear == "2022" && selectedAgeGroup == "老年(65以上)" -> R.drawable._02265f

            // 2023
            selectedYear == "2023" && selectedAgeGroup == "全部" -> R.drawable._023allf
            selectedYear == "2023" && selectedAgeGroup == "兒童(0~12)" -> R.drawable._023012f
            selectedYear == "2023" && selectedAgeGroup == "少年(13~17)" -> R.drawable._0231317f
            selectedYear == "2023" && selectedAgeGroup == "青年(18~23)" -> R.drawable._0231823f
            selectedYear == "2023" && selectedAgeGroup == "壯年(24~39)" -> R.drawable._0232439f
            selectedYear == "2023" && selectedAgeGroup == "中年(40~64)" -> R.drawable._0234064f
            selectedYear == "2023" && selectedAgeGroup == "老年(65以上)" -> R.drawable._02365f
            else -> R.drawable.btn_shape_gray // Default case
        }

        // 更新兩個 ImageView 的圖片
        binding.imageView4.setImageResource(imageResId4)
        binding.imageView3.setImageResource(imageResId3)
    }
}