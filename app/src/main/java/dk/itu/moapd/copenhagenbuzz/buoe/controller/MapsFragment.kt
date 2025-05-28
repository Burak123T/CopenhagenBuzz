package dk.itu.moapd.copenhagenbuzz.buoe.controller

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.copenhagenbuzz.buoe.R
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.FragmentMapsBinding

/**
 * A simple [Fragment] subclass for viewing the maps.
 */
class MapsFragment : Fragment() {

    private lateinit var fragmentMapsBinding: FragmentMapsBinding

    companion object {
        const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
    }

    private fun checkPermission() =
        ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestUserPermissions() {
        if (!checkPermission())
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMapsBinding = FragmentMapsBinding.inflate(inflater, container, false)
        return fragmentMapsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestUserPermissions()
    }



    private inner class LocationBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val location = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                intent?.getParcelableExtra(
                    LocationService.EXTRA_LOCATION,
                    android.location.Location::class.java
                )
            else
                @Suppress("DEPRECATION")
                (intent?.getParcelableExtra(LocationService.EXTRA_LOCATION))
            location?.let {
                updateLocationDetails(it)
            }
        }
    }

    private fun updateLocationDetails(location: Location) {
        with(fragmentMapsBinding) {

        }
    }
}