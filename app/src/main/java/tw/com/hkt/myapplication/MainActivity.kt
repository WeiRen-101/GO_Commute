package tw.com.hkt.myapplication

import android.Manifest
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.webkit.GeolocationPermissions
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import tw.com.hkt.myapplication.databinding.ActivityMainBinding
//import tw.com.hkt.myapplication.Maps.GeofenceBroadcastReceiver
import tw.com.hkt.myapplication.Maps.MapsWarning_dynamic
import tw.com.hkt.myapplication.Member.member
import tw.com.hkt.myapplication.TrafficInfo.traffic_information
import android.location.LocationListener
import android.net.Uri
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import tw.com.hkt.myapplication.Maps.GeofenceBroadcastReceiver

class MainActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityMainBinding
    private lateinit var webView: WebView
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private val NOTIFICATION_PERMISSION_REQUEST_CODE = 2
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var locationManager: LocationManager
    private lateinit var geofencingClient: GeofencingClient
    private val geofenceRadius = 30.0f // 圍欄半徑
    //測速照相位置
    private val cameraLocations = listOf(
//        LatLng(23.89735, 121.5349510),
        LatLng(24.302226, 121.75071),
        LatLng(24.117233, 121.639168),
        LatLng(24.157761, 121.650539),
        LatLng(24.07262, 121.608481),
        LatLng(23.982121, 121.617169),
        LatLng(23.859121, 121.503419),
        LatLng(23.845267, 121.493235),
        LatLng(23.67844, 121.419488),
        LatLng(23.561153, 121.381945),
        LatLng(23.537652, 121.377357),
        LatLng(23.495084, 121.367132),
        LatLng(23.506977, 121.372087),
        LatLng(23.173569, 121.242745),
        LatLng(23.954102, 121.550678),
        LatLng(23.942872, 121.541958),
        LatLng(23.780465, 121.561007),
        LatLng(23.647062, 121.534387),
        LatLng(23.898794, 121.560796),
        LatLng(23.829117, 121.505658),
        LatLng(23.267388, 121.369487),
        LatLng(23.491855, 121.396059),
        LatLng(23.491786, 121.395921),
        LatLng(23.445364, 121.387221),
        LatLng(23.98921, 121.61002),
        LatLng(23.940967, 121.580169),
        LatLng(23.744761, 121.45242),
        LatLng(23.335528, 121.320093),
        LatLng(23.964906, 121.567181),
        LatLng(23.907053, 121.530014),
        LatLng(23.95873, 121.569227),
        LatLng(23.958728, 121.569231),
        LatLng(24.006307, 121.618314),
        LatLng(23.987072, 121.587882),
        LatLng(24.018665, 121.609807),
        LatLng(24.002084, 121.599727),
        LatLng(24.002161, 121.619677),
        LatLng(23.977262, 121.609136),
        LatLng(23.976332, 121.609911),
        LatLng(23.976332, 121.609873),
        LatLng(23.977994, 121.608618),
        LatLng(23.977974, 121.608529),
        LatLng(23.986779, 121.601691),
        LatLng(23.984588, 121.603449),
        LatLng(23.992989, 121.601909),
        LatLng(23.93527, 121.59027),
        LatLng(23.93657, 121.595634),
        LatLng(23.936844, 121.598847),
        LatLng(23.962678, 121.607367),
        LatLng(24.245505, 121.723622),
        LatLng(24.245582, 121.723557),
        LatLng(24.267053, 121.724862),
        LatLng(24.267053, 121.724862),
        LatLng(24.267238, 121.724205),
        LatLng(24.267238, 121.724205),
        LatLng(24.229001, 121.700718),
        LatLng(24.228604, 121.7004),
        LatLng(23.767652, 121.560927),
        LatLng(23.778926, 121.560679),
        LatLng(23.996199, 121.594557),
        LatLng(23.986965, 121.58799),
        LatLng(23.983286, 121.604415),
        LatLng(23.99202, 121.597619),
        LatLng(23.9742, 121.611575),
        LatLng(23.97939, 121.610634),
        LatLng(23.975399, 121.575031)
    )
    //ＧＰＳ定位
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var lastNotificationTime: Long = 0 // 上次通知時間
    private val notificationInterval =  30 * 1000 // 通知間隔時間（以毫秒為單位），例如 30秒
    private val minDistanceChangeForUpdate: Float = 30f // 30 米
    private var lastLocation: Location? = null
    private val minSpeedForNotification = 10f // 10 米/秒


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWebView()
        setupDrawer()
        setupBottomNavigationView()


        binding.button1.setOnClickListener{
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
        }
        // 檢查定位權限
//        checkLocationPermission()

        // 初始化 GeofencingClient 並建立地理圍欄
        geofencingClient = LocationServices.getGeofencingClient(this)
        createGeofences()

        // 初始化 LocationManager
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        // 初始化 FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        startLocationUpdates()

        // 在程式啟動時通知使用者警示系統已啟用
//        showNotification("警示系統已啟用")

        // 檢查通知權限
        checkPermissions()

    }


    private fun setupWebView() {
        webView = findViewById(R.id.webview)

        webView.settings.apply {
            javaScriptEnabled = true
            setGeolocationEnabled(true)
        }
        webView.webChromeClient = object : WebChromeClient() {
            override fun onGeolocationPermissionsShowPrompt(
                origin: String?,
                callback: GeolocationPermissions.Callback?
            ) {
                callback?.invoke(origin, true, false)
            }
        }
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    when {
                        url.startsWith("http://") || url.startsWith("https://") -> {
                            // 對於普通的 HTTP/HTTPS URL，讓 WebView 加載該頁面
                            view?.loadUrl(url)
                            return false
                        }
                        url.startsWith("geo:") -> {
                            // 對於地理位置 URL，使用地圖應用程式打開
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            startActivity(intent)
                            return true
                        }
                        url.startsWith("tel:") -> {
                            // 對於電話 URL，使用撥號應用程式打開
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
                            startActivity(intent)
                            return true
                        }
                        else -> {
                            // 對於未知協議的 URL，使用預設的瀏覽器或應用程式處理
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                startActivity(intent)
                            } catch (e: Exception) {
//                                Toast.makeText(this@MainActivity, "無法處理的連結: $url", Toast.LENGTH_SHORT).show()
                            }
                            return true
                        }
                    }
                }
                return false
            }
        }

        webView.loadUrl("https://www.google.com/maps")
    }

    private fun checkPermissions() {
        var allPermissionsGranted = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {  // API 33 或更高版本
            // 檢查通知權限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.POST_NOTIFICATIONS)) {
                    showPermissionExplanationDialog()
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        NOTIFICATION_PERMISSION_REQUEST_CODE
                    )
                }
            }
        }

        // 檢查位置權限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            allPermissionsGranted = false
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                showLocationPermissionExplanationDialog()
            } else {
                requestLocationPermission()
            }
        }

        // 如果所有權限都已授權，顯示通知
        if (allPermissionsGranted) {
            showNotification("警示系統已啟用")
            enableLocationInWebView()
            startLocationUpdates()
        }
    }



    private fun enableLocationInWebView() {
        webView.settings.setGeolocationEnabled(true)
    }

    private fun setupDrawer() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    finish()
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setupBottomNavigationView() {
        val bottomNavigationView: BottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.action_trafficInfo -> {
                    startActivity(Intent(this, traffic_information::class.java))
                    true
                }
                R.id.member -> {
                    startActivity(Intent(this, member::class.java))
                    true
                }
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun createGeofences() {
        val geofencingRequestBuilder = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)

        cameraLocations.forEachIndexed { index, location ->
            val geofence = Geofence.Builder()
                .setRequestId("geofence_id_$index")
                .setCircularRegion(location.latitude, location.longitude, geofenceRadius)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
                .build()

            geofencingRequestBuilder.addGeofence(geofence)
        }

        val geofencingRequest = geofencingRequestBuilder.build()

        val geofencePendingIntent: PendingIntent = PendingIntent.getBroadcast(
            this, 0, Intent(this, GeofenceBroadcastReceiver::class.java)
                .putExtra("geofenceTransition", Geofence.GEOFENCE_TRANSITION_ENTER),
            PendingIntent.FLAG_UPDATE_CURRENT or if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent)
            .addOnSuccessListener {
                showNotification("測速照相機警示系統已啟動")
            }
            .addOnFailureListener {
                showNotification("測速照相機警示系統啟動失敗: ${it.message}")
            }
    }


    private fun showNotification(message: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "geofence_channel_id"

        // 創建通知頻道（僅適用於 Android O 及更高版本）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "地理圍欄通知",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        // 構建通知
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)  // 設置通知圖標
            .setContentTitle("動態警示系統")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)  // 設置通知優先級
            .build()

        // 顯示通知
        notificationManager.notify(1, notification)
    }

    //定位
    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000 // 每 10 秒請求一次位置更新
            fastestInterval = 5000 // 最快 5 秒內接收位置更新
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                for (location in locationResult.locations) {
                    // 在這裡處理位置更新
                    checkUserProximity(location)
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }


//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            NOTIFICATION_PERMISSION_REQUEST_CODE -> {
//                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                    // 用戶授予通知權限，顯示通知
//                    showNotification("警示系統已啟用")
//                } else {
//                    // 用戶拒絕通知權限，顯示提示
//                    Toast.makeText(this, "通知權限被拒絕，無法顯示警示系統通知", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//        when (requestCode) {
//            LOCATION_PERMISSION_REQUEST_CODE -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // 用戶授予位置權限，啟用位置功能
//                    enableLocationInWebView()
//                    startLocationUpdates()
//                } else {
//                    // 用戶拒絕位置權限，可以提示用戶授予權限以獲取完整功能
//                    Toast.makeText(this, "位置權限被拒絕，無法提供警示通知", Toast.LENGTH_SHORT).show()
//                }
//            }
//            NOTIFICATION_PERMISSION_REQUEST_CODE -> {
//                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                    // 用戶授予通知權限，顯示通知
//                    showNotification("警示系統已啟用")
//                } else {
//                    Toast.makeText(this, "通知權限被拒絕，無法顯示警示系統通知", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }

    private fun showPermissionExplanationDialog() {
        AlertDialog.Builder(this)
            .setTitle("通知權限請求")
            .setMessage("為了向您提供重要的警示系統通知，應用需要您的通知權限。請允許應用發送通知。")
            .setPositiveButton("允許") { _, _ ->
                // 再次請求權限
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_REQUEST_CODE
                )
            }
            .setNegativeButton("拒絕") { dialog, _ ->
                // 用戶拒絕，關閉對話框
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun checkUserProximity(currentLocation: Location) {
        val currentTime = System.currentTimeMillis()

        // 檢查通知間隔時間
        if (currentTime - lastNotificationTime < notificationInterval) {
            return
        }

        // 檢查位置變化
        if (lastLocation != null) {
            val distanceMoved = lastLocation!!.distanceTo(currentLocation)
            if (distanceMoved < minDistanceChangeForUpdate) {
                // 使用者移動的距離不足，忽略這次更新
                return
            }
        }
        // 檢查速度
        if (currentLocation.speed < minSpeedForNotification) {
            // 速度不足，忽略這次更新
            return
        }
        lastLocation = currentLocation

        val currentLatLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        for (cameraLocation in cameraLocations) {
            val distance = FloatArray(1)
            Location.distanceBetween(
                currentLatLng.latitude,
                currentLatLng.longitude,
                cameraLocation.latitude,
                cameraLocation.longitude,
                distance
            )

            if (distance[0] <= geofenceRadius) {
                showNotification("您已接近測速照相機")
                lastNotificationTime = currentTime
                break
            }
        }
    }

    private fun showLocationPermissionExplanationDialog() {
        AlertDialog.Builder(this)
            .setTitle("位置權限請求")
            .setMessage("為了提供準確的測速照相機警示通知，應用需要存取您的位置信息。請允許應用存取位置。")
            .setPositiveButton("允許") { _, _ ->
                // 再次請求權限
                requestLocationPermission()
            }
            .setNegativeButton("拒絕") { dialog, _ ->
                // 用戶拒絕，關閉對話框
                dialog.dismiss()
            }
            .create()
            .show()
    }
    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }
}