package dk.itu.moapd.copenhagenbuzz.buoe.controller

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.telecom.TelecomManager.EXTRA_LOCATION
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class LocationService : Service() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        // Start and stop location client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                val currentLocation = locationResult.lastLocation
                val intent = Intent(EXTRA_LOCATION)
                intent.putExtra(EXTRA_LOCATION, currentLocation)
                LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
            }
        }
    }

    companion object {
        const val EXTRA_LOCATION: String = "dk.itu.moapd.copenhagenbuzz.action.LOCATION_BROADCAST"
    }
}