package tw.com.hkt.myapplication.Maps

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.google.android.gms.location.Geofence

class GeofenceTransitionsJobIntentService : JobIntentService() {

    companion object {
        private const val JOB_ID = 573
        fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, GeofenceTransitionsJobIntentService::class.java, JOB_ID, work)
        }

        fun hasGeofenceTransition(intent: Intent): Boolean {
            return intent.hasExtra("geofenceTransition")
        }

        fun getGeofenceTransition(intent: Intent): Int {
            return intent.getIntExtra("geofenceTransition", -1)
        }
    }

    override fun onHandleWork(intent: Intent) {
        // Handle the geofence transition
        val geofenceTransition = getGeofenceTransition(intent)
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            // Handle the transition event, show notification
            val broadcastReceiver = GeofenceBroadcastReceiver()
            broadcastReceiver.showNotification(this, "您已接近測速照相機")
        }
    }
}