package tw.com.hkt.myapplication.TrafficInfo.LargeCar

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.R
import tw.com.hkt.myapplication.databinding.ActivityLargeCarBinding

class LargeCar : AppCompatActivity() {

    private lateinit var binding: ActivityLargeCarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLargeCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
//        binding.toolbarLayout.title = title

    }
}