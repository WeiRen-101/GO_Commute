package tw.com.hkt.myapplication.TrafficInfo.MapsWarning

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import tw.com.hkt.myapplication.R
import tw.com.hkt.myapplication.databinding.ActivityMapsWarningBinding


class MapsWarning_static : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private lateinit var map: GoogleMap

    private lateinit var binding: ActivityMapsWarningBinding
    private lateinit var clusterManager: ClusterManager<MyItem>

    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsWarningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // 定義圓形的屬性
        val circleOptionsList = listOf(
            CircleOptions()
                .center(LatLng(23.992033, 121.597597))
                .radius(30.0)
                .strokeColor(0xFFFF0000.toInt()) // 紅色邊框
                .fillColor(0x30FF0000) // 半透明紅色填充
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.98887, 121.57005))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.980264, 121.607814))
                .radius(30.0)
                .strokeColor(0xFFFF0000.toInt()) // 紅色邊框
                .fillColor(0x30FF0000) // 半透明紅色填充
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.977764, 121.606034))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.987176, 121.601399))
                .radius(30.0)
                .strokeColor(0xFFFF0000.toInt()) // 紅色邊框
                .fillColor(0x30FF0000) // 半透明紅色填充
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.97577, 121.60516))
                .radius(30.0)
                .strokeColor(0xFFFF0000.toInt()) // 紅色邊框
                .fillColor(0x30FF0000) // 半透明紅色填充
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.972495, 121.604594))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.987762, 121.600961))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.994774, 121.59545))
                .radius(30.0)
                .strokeColor(0xFFFF0000.toInt()) // 紅色邊框
                .fillColor(0x30FF0000) // 半透明紅色填充
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.979361, 121.608506))
                .radius(30.0)
                .strokeColor(0xFFFF0000.toInt()) // 紅色邊框
                .fillColor(0x30FF0000) // 半透明紅色填充
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.981601, 121.605705))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(24.001218, 121.613423))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.983266, 121.604455))
                .radius(30.0)
                .strokeColor(0xFFFF0000.toInt()) // 紅色邊框
                .fillColor(0x30FF0000) // 半透明紅色填充
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.976606, 121.585022))
                .radius(30.0)
                .strokeColor(0xFFFF0000.toInt()) // 紅色邊框
                .fillColor(0x30FF0000) // 半透明紅色填充
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.981166, 121.605079))
                .radius(30.0)
                .strokeColor(0xFFFF0000.toInt()) // 紅色邊框
                .fillColor(0x30FF0000) // 半透明紅色填充
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.98626, 121.56836))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.972306, 121.605818))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.977647, 121.590588))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.979433, 121.610675))
                .radius(30.0)
                .strokeColor(0xFFFF0000.toInt()) // 紅色邊框
                .fillColor(0x30FF0000) // 半透明紅色填充
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.97444, 121.603071))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.970894, 121.5977))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.966055, 121.591782))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.981629, 121.607769))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.981224, 121.592795))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.987062, 121.587841))
                .radius(30.0)
                .strokeColor(0xFFFF0000.toInt()) // 紅色邊框
                .fillColor(0x30FF0000) // 半透明紅色填充
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.984147, 121.599931))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.989775, 121.570609))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.977218, 121.611247))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.98808, 121.606182))
                .radius(30.0)
                .strokeColor(0xFFFF0000.toInt()) // 紅色邊框
                .fillColor(0x30FF0000) // 半透明紅色填充
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.974217, 121.611545))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.979579, 121.604667))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.970698, 121.592433))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f),
            CircleOptions()
                .center(LatLng(23.975255, 121.574357))
                .radius(30.0)
                .strokeColor(0xFF0000FF.toInt())
                .fillColor(0x300000FF)
                .strokeWidth(5f)
        )

        // 添加所有圓形到地圖上
        for (circleOptions in circleOptionsList) {
            map.addCircle(circleOptions)
        }

        // 可選：移動地圖鏡頭至第一個圓形中心
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(circleOptionsList[0].center, 10f))

        // 將圓形加入地圖
       // val circle = map.addCircle(circleOptions)
        // 移動地圖鏡頭至圓形中心
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(circleCenter, 13f))

        // Initialize the cluster manager and set listeners
        setUpClusterer()

        // Set up map click listener
        //map.setOnMapClickListener { latlng ->
        //   Log.d("MapsWarning", "Map clicked at: $latlng")
        //    map.clear()  // Clear all markers and clusters
        //    map.animateCamera(CameraUpdateFactory.newLatLng(latlng))
        //    map.addMarker(MarkerOptions().position(latlng).title("Marker at ${latlng.latitude}, ${latlng.longitude}"))
        //}

        // Configure map settings
        val uiSettings = map.uiSettings
        uiSettings.isZoomControlsEnabled = true

        // Set initial location
        //起始點設為東華大學志學門
        val locate = LatLng(23.903850, 121.536340)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(locate, 15f))
        //map.addMarker(MarkerOptions().position(locate).title("花蓮市府前、北興路口（南下）"))

        // Check and enable location services if permissions are granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        } else {
            Toast.makeText(this, "Location permissions are required", Toast.LENGTH_SHORT).show()
        }

        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
    }

    private fun setUpClusterer() {

        clusterManager = ClusterManager(this, map)
        clusterManager.renderer = MyClusterRenderer(this, map, clusterManager)
        map.setOnCameraIdleListener(clusterManager)
        map.setOnMarkerClickListener(clusterManager)

        // Clear existing items and add new ones
        clusterManager.clearItems()  // Clear items from ClusterManager
        addItems()  // Add new items
    }

    private fun addItems() {
        // Define new coordinates with titles and snippets
        val newLocations = listOf(
            Triple(LatLng(24.302226, 121.750710), "臺9線146.27K\n", "測速(雷達)\n"),
            Triple(LatLng(24.117233, 121.639168), "臺9線166.5K\n", "測速(雷達)\n"),
            Triple(LatLng(24.157761, 121.650539), "臺9線167.5K\n", "測速(雷達)\n"),
            Triple(LatLng(24.072620, 121.608481), "臺9線179.2K\n", "測速(雷達)\n"),
            Triple(LatLng(23.982121, 121.617169), "臺9線190.37K\n", "測速(雷達)\n"),
            Triple(LatLng(23.859121, 121.503419), "臺9線209.7K\n", "測速(雷達)\n"),
            Triple(LatLng(23.845267, 121.493235), "臺9線211.57K\n", "測速(雷達)\n"),
            Triple(LatLng(23.678440, 121.419488), "臺9線233.92K\n", "測速(雷達)\n"),
            Triple(LatLng(23.561153, 121.381945), "臺9線248.1K\n", "測速(雷達)\n"),
            Triple(LatLng(23.537652, 121.377357), "臺9線250.8K\n", "測速(雷達)\n"),
            Triple(LatLng(23.495084, 121.367132), "臺9線255.45K\n", "測速(雷達)\n"),
            Triple(LatLng(23.506977, 121.372087), "臺9線269.3K\n", "測速(雷達)\n"),
            Triple(LatLng(23.173569, 121.242745), "臺9線299.82K\n", "測速(雷達)\n"),
            Triple(LatLng(23.954102, 121.550678), "臺9丙線7K\n", "測速(雷達)\n"),
            Triple(LatLng(23.942872, 121.541958), "臺9丙線9.159K\n", "測速(雷達)\n"),
            Triple(LatLng(23.780465, 121.561007), "臺11線23.4K\n", "測速(雷達)\n"),
            Triple(LatLng(23.647062, 121.534387), "臺11線43.95K\n", "測速(雷達)\n"),
            Triple(LatLng(23.898794, 121.560796), "臺11線丙4.4K\n", "測速(雷達)\n"),
            Triple(LatLng(23.829117, 121.505658), "臺11線丙14.2K\n", "測速(雷達)\n"),
            Triple(LatLng(23.267388, 121.369487), "臺30線27.2K〈玉長公路〉\n", "測速(雷達)\n"),
            Triple(LatLng(23.491855, 121.396059), "縣道193線89.12K（東往西）\n", "測速(雷達)\n"),
            Triple(LatLng(23.491786, 121.395921), "縣道193線88.15K（西往東）\n", "測速(雷達)\n"),
            Triple(LatLng(23.445364, 121.387221), "縣道193線95.4k\n", "測速(雷達)\n"),
            Triple(LatLng(23.989210, 121.610020), "花蓮市尚志路18-1號前\n", "測速(雷達)\n"),
            Triple(LatLng(23.940967, 121.580169), "花蓮縣吉安鄉知卡宣大道與永華二街口\n", "測速(雷達)\n"),
            Triple(LatLng(23.744761, 121.452420), "花蓮縣鳳林鎮信義、公正路口（北上）\n", "測速、闖紅燈及未依標誌標線號誌(線圈式暨雷達)\n"),
            Triple(LatLng(23.335528, 121.320093), "花蓮縣玉里鎮興國路與自由街口（南下）\n", "測速、闖紅燈及未依標誌標線號誌(線圈式暨雷達)\n"),
            Triple(LatLng(23.964906, 121.567181), "花蓮縣吉安鄉中央路與稻香路口\n", "測速、闖紅燈及未依標誌標線號誌(線圈式暨雷達)\n"),
            Triple(LatLng(23.907053, 121.530014), "花蓮縣壽豐鄉志學村中山路與中正路口\n", "測速、闖紅燈及未依標誌標線號誌(線圈式暨雷達)\n"),
            Triple(LatLng(23.958730, 121.569227), "花蓮縣吉安鄉吉豐路與吉興路口〈南下車道〉\n", "測速、闖紅燈及未依標誌標線號誌(線圈式暨雷達)\n"),
            Triple(LatLng(23.958728, 121.569231), "花蓮縣吉安鄉吉豐路與吉興路口〈北上車道〉\n", "測速、闖紅燈及未依標誌標線號誌(線圈式暨雷達)\n"),
            Triple(LatLng(24.006307, 121.618314), "花蓮市府前、北興路口（南下）\n", "闖紅燈及未依標誌標線號誌(線圈式)\n"),
            Triple(LatLng(23.987072, 121.587882), "花蓮縣吉安鄉中央路與建國路口（南下）\n", "闖紅燈及未依標誌標線號誌(線圈式)\n"),
            Triple(LatLng(24.018665, 121.609807), "花蓮縣臺9線與新城鄉嘉里二街口（南下）\n", "闖紅燈及未依標誌標線號誌(線圈式)\n"),
            Triple(LatLng(24.002084, 121.599727), "花蓮市中央路與豐村路口（北上）\n", "闖紅燈及未依標誌標線號誌(線圈式)\n"),
            Triple(LatLng(24.002161, 121.619677), "花蓮市府前路與化道路口\n", "闖紅燈及未依標誌標線號誌(線圈式)\n"),
            Triple(LatLng(23.977262, 121.609136), "花蓮市中山路與公正街口（東西向）\n", "違規停車(自動偵測辨識)\n"),
            Triple(LatLng(23.976332, 121.609911), "花蓮市中山路與中華路口（東西向）\n", "違規停車(自動偵測辨識)\n"),
            Triple(LatLng(23.976332, 121.609873), "花蓮市中山路與中華路口（南北向）\n", "違規停車(自動偵測辨識)\n"),
            Triple(LatLng(23.977994, 121.608618), "花蓮市中山路與中正路口（東西向）\n", "違規停車(自動偵測辨識)\n"),
            Triple(LatLng(23.977974, 121.608529), "花蓮市中山路與中正路口（南北向）\n", "違規停車(自動偵測辨識)\n"),
            Triple(LatLng(23.986779, 121.601691), "花蓮市中山路與建中街口（東西向）\n", "違規停車(自動偵測辨識)\n"),
            Triple(LatLng(23.984588, 121.603449), "花蓮市中山路與明智街口（東西向）\n", "違規停車(自動偵測辨識)\n"),
            Triple(LatLng(23.992989, 121.601909), "花蓮火車站前站\n", "違規停車(自動偵測辨識)\n"),
            Triple(LatLng(23.935270, 121.590270), "花蓮縣兩潭自行車道12.33K\n", "禁行車種(自動偵測辨識)\n"),
            Triple(LatLng(23.936570, 121.595634), "花蓮縣兩潭自行車道12.9K\n", "禁行車種(自動偵測辨識)\n"),
            Triple(LatLng(23.936844, 121.598847), "花蓮縣兩潭自行車道13.26K\n", "禁行車種(自動偵測辨識)\n"),
            Triple(LatLng(23.962678, 121.607367), "花蓮縣兩潭自行車道16.61K\n", "禁行車種(自動偵測辨識)\n"),
            Triple(LatLng(24.245505, 121.723622), "臺9丁線64K+13-59K+524.2(北上) (4,488.8公尺)\n", "區間測速 科技執法(自動偵測辨識)\n"),
            Triple(LatLng(24.245582, 121.723557), "臺9丁線59K+572.7-64K+45.1(南下) (4,472.4公尺)\n", "區間測速 科技執法(自動偵測辨識)\n"),
            Triple(LatLng(24.267053, 121.724862), "臺9線151K+434-149K+066(北上)中仁隧道 (2,368公尺)\n", "區間測速 科技執法(自動偵測辨識)\n"),
            Triple(LatLng(24.267053, 121.724862), "臺9線153K+718-151K+434(北上)中仁隧道 (2,284公尺)\n", "區間測速 科技執法(自動偵測辨識)\n"),
            Triple(LatLng(24.267238, 121.724205), "臺9線149K+257-151K+756(南下)中仁隧道 (2,499公尺)\n", "區間測速 科技執法(自動偵測辨識)\n"),
            Triple(LatLng(24.267238, 121.724205), "臺9線151K+756-153K+987(南下)中仁隧道 (2,231公尺)\n", "區間測速 科技執法(自動偵測辨識)\n"),
            Triple(LatLng(24.229001, 121.700718), "臺9線157K+888-155K+122(北上)仁水隧道 (2,766公尺)\n", "區間測速 科技執法(自動偵測辨識)\n"),
            Triple(LatLng(24.228604, 121.700400), "臺9線155K+207-157K+976(南下)仁水隧道 (2,769公尺)\n", "區間測速 科技執法(自動偵測辨識)\n"),
            Triple(LatLng(23.767652, 121.560927), "臺11線26K+581.92-24K+127.73(北上) (2,454.2公尺)\n", "區間測速 科技執法(自動偵測辨識)\n"),
            Triple(LatLng(23.778926, 121.560679), "臺11線24K+176.14-26K+623.9(南下) (2,447.8公尺)\n", "區間測速 科技執法(自動偵測辨識)\n"),
            Triple(LatLng(23.996199, 121.594557), "花蓮縣花蓮市中央路與中山路口（南下）\n", "闖紅燈、測速、跨越雙白實線、不依行向專用車道行駛、紅燈越線等(非線圈式暨雷達)\n"),
            Triple(LatLng(23.986965, 121.587990), "花蓮縣吉安鄉中央路與建國路口（北上）\n", "闖紅燈、測速、跨越雙白實線、不依行向專用車道行駛、紅燈越線等(非線圈式暨雷達)\n"),
            Triple(LatLng(23.983286, 121.604415), "花蓮市中山路與建國路口\n", "闖紅燈、機車未依規定兩段式左轉、跨越雙白實線、紅燈越線等(線圈式暨自動偵測辨識)\n"),
            Triple(LatLng(23.992020, 121.597619), "花蓮市中山路與富安路口\n", "闖紅燈、機車未依規定兩段式左轉、紅燈越線等(線圈式暨自動偵測辨識)\n"),
            Triple(LatLng(23.974200, 121.611575), "花蓮市中山路、軒轅路、花崗街及重慶路口\n", "闖紅燈、汽機車不禮讓行人、紅燈越線等(線圈式暨自動偵測辨識)\n"),
            Triple(LatLng(23.979390, 121.610634), "花蓮市中正路與明禮路口\n", "闖紅燈、機車未依規定兩段式左轉、紅燈越線等(線圈式暨自動偵測辨識)\n"),
            Triple(LatLng(23.975399, 121.575031), "吉安鄉中山路3段與中央路2段路口\n", "不依行向專用車道行駛、機車未依規定兩段式左轉、跨越雙白實線等(自動偵測辨識)\n")

        )

        for ((location, title, snippet) in newLocations) {
            val offsetItem = MyItem(location.latitude, location.longitude, title, snippet)
            clusterManager.addItem(offsetItem)

            // Create a MarkerOptions object with a custom icon
           // val markerOptions = MarkerOptions()
            //    .position(LatLng(offsetItem.position.latitude, offsetItem.position.longitude))
             //   .title(offsetItem.title)
            //    .snippet(offsetItem.snippet)
             //   .icon(BitmapDescriptorFactory.fromResource(R.drawable.camera4040))  // 使用自定義圖標

            // Adding the marker to the map using ClusterManager
            //clusterManager.addItem(offsetItem)
            //map.addMarker(markerOptions)  // Add marker with custom icon
        }
    }

    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this, "Current location:\n$location", Toast.LENGTH_LONG).show()
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(this, "Location permission required to display your location.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
