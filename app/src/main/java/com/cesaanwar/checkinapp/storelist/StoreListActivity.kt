package com.cesaanwar.checkinapp.storelist

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.cesaanwar.checkinapp.R
import com.cesaanwar.checkinapp.data.Result.Success
import com.cesaanwar.checkinapp.databinding.ActivityStoreListBinding
import com.cesaanwar.checkinapp.storepage.StorePageActivity
import com.cesaanwar.checkinapp.util.DateHelper
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StoreListActivity : AppCompatActivity(), OnMapReadyCallback {

    private var locationPermissionGranted = false
    private var lastKnownLocation: Location? = null

    lateinit var locationRequest: LocationRequest

    private lateinit var locationCallback: LocationCallback

    private lateinit var binding: ActivityStoreListBinding

    private lateinit var adapter: StoreAdapter

    private val viewModel: StoreListViewModel by viewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var map: GoogleMap? = null

    companion object {
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 543
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_list)
        binding.dateInfo = DateHelper.getCurrentDateInfoString()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLocationPermission()
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
        createLocationRequest()
        setupAdapter()
        setupObservables()
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.first().let {
                    lastKnownLocation = it
                }
            }
        }
    }


    @SuppressLint("MissingPermission")
    private fun setupLocation() {
        if (locationPermissionGranted) {
            fusedLocationClient.lastLocation.addOnCompleteListener {
                if (it.isSuccessful) {
                    lastKnownLocation = it.result
                    lastKnownLocation?.let { location ->
                        setupMap(location)
                    }
                }

            }
        }
    }

    private fun setupMap(location: Location) {
        val currentLocation = LatLng(location.latitude, location.longitude)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15F))
    }

    private fun setupAdapter() {
        adapter = StoreAdapter(viewModel)
        binding.rvStores.adapter = adapter
    }

    private fun setupObservables() {
        viewModel.storeListUIModelLiveData.observe(this) { result ->
            when (result) {
                is Success -> {
                    val data = result.data
                    adapter.submitList(data)
                    data.forEach { point ->
                        val location = LatLng(point.latitude, point.longitude)
                        map?.addMarker(
                            MarkerOptions()
                                .position(location)
                                .title("Marker in Sydney")
                        )
                    }
                    binding.mapView.invalidate()
                }
                else -> {

                }
            }
        }
        viewModel.storeVisitEventLiveData.observe(this) { event ->
            val store = event.peekContent()
            val distance = lastKnownLocation?.distanceTo(
                Location(LocationManager.GPS_PROVIDER).apply {
                    latitude = store.latitude
                    longitude = store.longitude
                }
            )?.toDouble() ?: Double.MAX_VALUE
            if (distance > 100.0) {
                Toast.makeText(this, "Toko terlalu jauh dari posisi", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, StorePageActivity::class.java)
                intent.putExtra(StorePageActivity.LOCAL_STORE_ID, store.localStoreId)
                intent.putExtra(StorePageActivity.STORE_ID, store.storeId)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
        startLocationUpdates()
        viewModel.getStores(lastKnownLocation)
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
        stopLocationUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(locationRequest,
            locationCallback,
            Looper.getMainLooper())
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map

        getLocationPermission()

        setupLocation()

        updateLocationUI()
    }

    fun createLocationRequest() {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        this.locationRequest = locationRequest
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener { locationSettingsResponse ->
            locationSettingsResponse.locationSettingsStates.let {

            }
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException){
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(this,
                        1)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    private fun getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    private fun updateLocationUI() {
        if (map == null) {
            return
        }
        try {
            if (locationPermissionGranted) {
                map?.isMyLocationEnabled = true
                map?.uiSettings?.isMyLocationButtonEnabled = true
            } else {
                map?.isMyLocationEnabled = false
                map?.uiSettings?.isMyLocationButtonEnabled = false
                lastKnownLocation = null
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                locationPermissionGranted = true

                setupLocation()

                updateLocationUI()
            }
        }
    }

}