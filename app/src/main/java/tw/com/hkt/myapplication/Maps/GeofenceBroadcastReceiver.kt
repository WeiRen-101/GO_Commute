package tw.com.hkt.myapplication.Maps
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent
import tw.com.hkt.myapplication.R

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        if (geofencingEvent != null) {
            if (geofencingEvent.hasError()) {
                val errorMessage = GeofenceStatusCodes.getStatusCodeString(geofencingEvent.errorCode)
                Log.e("GeofenceReceiver", errorMessage)
                return
            }
        }

        // 檢查是哪種類型的地理圍欄事件（進入或離開）
        val geofenceTransition = geofencingEvent?.geofenceTransition
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            // 使用者進入警示範圍，顯示通知
            showNotification(context, "您已進入警示範圍！請注意安全。")
        } else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            // 使用者離開警示範圍，可以根據需要處理
            Log.d("GeofenceReceiver", "使用者已離開警示範圍")
        }
    }

    fun showNotification(context: Context, message: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "geofence_channel_id"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "地理圍欄通知",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)  // 替換為你的應用程式圖標
            .setContentTitle("測速照相機警示")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(1, notification)
    }
}