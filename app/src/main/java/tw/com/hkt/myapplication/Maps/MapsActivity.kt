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
//package tw.com.hkt.myapplication.Maps
//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.os.Bundle
//import android.webkit.GeolocationPermissions
//import android.webkit.WebChromeClient
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.google.android.gms.maps.GoogleMap
//import tw.com.hkt.myapplication.R
//
//// [START maps_android_sample_my_location]
////class MapsActivity : AppCompatActivity(),
////    OnMyLocationButtonClickListener,
////    OnMyLocationClickListener, OnMapReadyCallback,
////    OnRequestPermissionsResultCallback {
//
//class MapsActivity : AppCompatActivity() {
//
//    private lateinit var map: GoogleMap
//    private lateinit var webView: WebView
//    private val LOCATION_PERMISSION_REQUEST_CODE = 1
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_maps_navigation)
//
//        webView = findViewById(R.id.webview)
//        webView.webViewClient = WebViewClient()
//
//        // 啟用 JavaScript
//        webView.settings.javaScriptEnabled = true
//        webView.webChromeClient = object : WebChromeClient(){
//            override fun onGeolocationPermissionsShowPrompt(
//                origin: String?,
//                callback: GeolocationPermissions.Callback?
//            ) {
//                callback?.invoke(origin, true, false)
//            }
//        }
//
//        // 使 WebView 在本應用內打開鏈接
//        webView.webViewClient = WebViewClient()
//
//        // 載入 Google Maps 網址
//        webView.loadUrl("https://www.google.com/maps")
//
//    }
//
//    private fun checkLocationPermission() {
//        if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                LOCATION_PERMISSION_REQUEST_CODE
//            )
//        } else {
//            // 已有權限，啟用 WebView 定位功能
//            enableLocationInWebView()
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        when (requestCode) {
//            LOCATION_PERMISSION_REQUEST_CODE -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // 使用者同意了權限請求
//                    enableLocationInWebView()
//                } else {
//                    // 使用者拒絕了權限請求
//                    // 可以在此處顯示相關訊息或執行替代方案
//                }
//            }
//        }
//    }
//
//    private fun enableLocationInWebView() {
//        // 此處可以額外執行 WebView 定位相關的設置
//    }
//}