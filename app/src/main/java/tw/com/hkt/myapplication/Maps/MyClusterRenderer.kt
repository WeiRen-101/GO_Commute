package tw.com.hkt.myapplication.Maps

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import tw.com.hkt.myapplication.R
import tw.com.hkt.myapplication.TrafficInfo.MapsWarning.MyItem

class MyClusterRenderer(
    context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<MyItem>
) : DefaultClusterRenderer<MyItem>(context, map, clusterManager) {

    // 在這裡定義每個標記的圖示
    override fun onBeforeClusterItemRendered(item: MyItem, markerOptions: MarkerOptions) {
        // 使用自定義的圖標
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.camera54))
        super.onBeforeClusterItemRendered(item, markerOptions)
    }

    // 在這裡定義叢集的圖示（可選）
    //override fun onBeforeClusterRendered(cluster: Cluster<MyItem>, markerOptions: MarkerOptions) {
        // 使用自定義的叢集圖示
    //    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.fullmoon80))
    //    super.onBeforeClusterRendered(cluster, markerOptions)
    //}
}
