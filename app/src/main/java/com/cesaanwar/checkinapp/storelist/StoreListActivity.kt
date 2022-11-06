package com.cesaanwar.checkinapp.storelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.cesaanwar.checkinapp.R
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Result.Success
import com.cesaanwar.checkinapp.data.Result.Error
import com.cesaanwar.checkinapp.databinding.ActivityStoreListBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreListActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityStoreListBinding

    private lateinit var adapter: StoreAdapter

    private val viewModel: StoreListViewModel by viewModels()

    var map: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_list)
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
        setupAdapter()
        setupObservables()
    }

    private fun setupAdapter() {
        adapter = StoreAdapter()
        binding.rvStores.adapter = adapter
    }

    private fun setupObservables() {
        viewModel.storeUIModelLiveData.observe(this) { result ->
            when (result) {
                is Success -> {
                    val data = result.data
                    adapter.submitList(data)
                }
                else -> {

                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
        viewModel.getStores()
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
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        val sydney = LatLng(-6.2833843,106.911621)
        map.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15F))
    }
}