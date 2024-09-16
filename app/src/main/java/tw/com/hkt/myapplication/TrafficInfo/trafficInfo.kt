//package tw.com.hkt.myapplication.TrafficInfo
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.setupActionBarWithNavController
//import androidx.navigation.ui.setupWithNavController
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import tw.com.hkt.myapplication.R
//import tw.com.hkt.myapplication.databinding.ActivityTrafficInfoBinding
//
//class trafficInfo : AppCompatActivity() {
//
//    private lateinit var binding: ActivityTrafficInfoBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityTrafficInfoBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        //bottomNavigation的view
//        val navView: BottomNavigationView = binding.navView
//
//        //navController，讓我們控制Fragment之間的跳轉
//        val navController = findNavController(R.id.nav_host_fragment_activity_traffic_info)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//
//        //navigationUI使用AppBarConfiguration來控制導航按鈕，而因為BottomNavigation的Fragment彼此沒有層級關係，所以我們這邊傳入id。
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//    }
//}