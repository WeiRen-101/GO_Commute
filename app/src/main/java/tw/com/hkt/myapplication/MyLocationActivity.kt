//// Copyright 2020 Google LLC
////
//// Licensed under the Apache License, Version 2.0 (the "License");
//// you may not use this file except in compliance with the License.
//// You may obtain a copy of the License at
////
////      http://www.apache.org/licenses/LICENSE-2.0
////
//// Unless required by applicable law or agreed to in writing, software
//// distributed under the License is distributed on an "AS IS" BASIS,
//// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//// See the License for the specific language governing permissions and
//// limitations under the License.
//package tw.com.hkt.myapplication
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.content.pm.PackageManager
//import android.location.Location
//import android.os.Bundle
//import android.widget.EditText
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
//import androidx.core.content.ContextCompat
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
//import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.CameraPosition
//import com.google.android.gms.maps.model.LatLng
//import tw.com.hkt.myapplication.PermissionUtils.PermissionDeniedDialog.Companion.newInstance
//import tw.com.hkt.myapplication.PermissionUtils.isPermissionGranted
//
//// [START maps_android_sample_my_location]
//class MyLocationActivity : AppCompatActivity(),
//    OnMyLocationButtonClickListener,
//    OnMyLocationClickListener, OnMapReadyCallback,
//    OnRequestPermissionsResultCallback {
//
//    private var permissionDenied = false
//    private lateinit var map: GoogleMap
//    private lateinit var editText: EditText
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_maps_navigation)
//        val mapFragment =
//            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
//        mapFragment?.getMapAsync(this)
//
//
//    }
//
//
//    override fun onMapReady(googleMap: GoogleMap) {
//        map = googleMap
//        googleMap.setOnMyLocationButtonClickListener(this)
//        googleMap.setOnMyLocationClickListener(this)
//        enableMyLocation()
//
//        // 建立位置的座標物件
//        val place = LatLng(25.033408, 121.564099)
//        // 移動地圖
//        moveMap(place)
//
//        //地圖縮放功能
//        val uiSettings = map.uiSettings
//        uiSettings.isZoomControlsEnabled = true
//
//    }
//
//    /**
//     * Enables the My Location layer if the fine location permission has been granted.
//     */
//    @SuppressLint("MissingPermission")
//    private fun enableMyLocation() {
//
//        // [START maps_check_location_permission]
//        // 1. Check if permissions are granted, if so, enable the my location layer
//        if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            map.isMyLocationEnabled = true
//            return
//        }
//
//        // 2. If if a permission rationale dialog should be shown
//        if (ActivityCompat.shouldShowRequestPermissionRationale(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) || ActivityCompat.shouldShowRequestPermissionRationale(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            )
//        ) {
//            PermissionUtils.RationaleDialog.newInstance(
//                LOCATION_PERMISSION_REQUEST_CODE, true
//            ).show(supportFragmentManager, "dialog")
//            return
//        }
//
//        // 3. Otherwise, request permission
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ),
//            LOCATION_PERMISSION_REQUEST_CODE
//        )
//        // [END maps_check_location_permission]
//    }
//
//
//    override fun onMyLocationButtonClick(): Boolean {
//        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT)
//            .show()
//        // Return false so that we don't consume the event and the default behavior still occurs
//        // (the camera animates to the user's current position).
//
//        return false
//    }
//
//    override fun onMyLocationClick(location: Location) {
//        Toast.makeText(this, "Current location:\n$location", Toast.LENGTH_LONG)
//            .show()
//    }
//
//    // [START maps_check_location_permission_result]
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
//            super.onRequestPermissionsResult(
//                requestCode,
//                permissions,
//                grantResults
//            )
//            return
//        }
//
//        if (isPermissionGranted(
//                permissions,
//                grantResults,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) || isPermissionGranted(
//                permissions,
//                grantResults,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            )
//        ) {
//            // Enable the my location layer if the permission has been granted.
//            enableMyLocation()
//        } else {
//            // Permission was denied. Display an error message
//            // [START_EXCLUDE]
//            // Display the missing permission error dialog when the fragments resume.
//            permissionDenied = true
//            // [END_EXCLUDE]
//        }
//    }
//
//    // [END maps_check_location_permission_result]
//    override fun onResumeFragments() {
//        super.onResumeFragments()
//        if (permissionDenied) {
//            // Permission was not granted, display error dialog.
//            showMissingPermissionError()
//            permissionDenied = false
//        }
//    }
//
//    /**
//     * Displays a dialog with error message explaining that the location permission is missing.
//     */
//    private fun showMissingPermissionError() {
//        newInstance(true).show(supportFragmentManager, "dialog")
//    }
//
//    companion object {
//        /**
//         * Request code for location permission request.
//         *
//         * @see .onRequestPermissionsResult
//         */
//        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
//    }// [END maps_android_sample_my_location]
//
//
//    // 移動地圖到參數指定的位置
//    private fun moveMap(place: LatLng) {
//        // 建立地圖攝影機的位置物件
//        val cameraPosition = CameraPosition.Builder()
//            .target(place)
//            .zoom(17f)
//            .build()
//
//        // 使用動畫的效果移動地圖
//        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
//    }
//}
