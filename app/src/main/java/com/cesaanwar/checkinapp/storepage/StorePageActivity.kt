package com.cesaanwar.checkinapp.storepage

import android.content.Intent
import android.hardware.camera2.CameraCharacteristics
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cesaanwar.checkinapp.R
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.databinding.ActivityStorePageBinding
import com.cesaanwar.checkinapp.storedashboard.StoreDashboardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorePageActivity : AppCompatActivity() {

    lateinit var binding: ActivityStorePageBinding

    private val viewModel: StorePageViewModel by viewModels()

    companion object {
        const val LOCAL_STORE_ID = "localStoreId"
        const val STORE_ID = "storeId"
        const val REQUEST_CODE_CAMERA = 7584
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_page)
        setupObservers()
        intent.apply {
            val localStoreId = getLongExtra(LOCAL_STORE_ID, -1L)
            val storeId = getStringExtra(STORE_ID) ?: ""
            fetchStoreData(localStoreId, storeId)
        }
        setupVisitButton()
    }

    private fun setupVisitButton() {
        binding.btnVisit.setOnClickListener {
            startCameraIntent()
        }
        binding.btnNoVisit.setOnClickListener {
            onBackPressed()
        }
    }

    private fun startCameraIntent() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1 && Build.VERSION.SDK_INT < Build.VERSION_CODES.O -> {
                cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", CameraCharacteristics.LENS_FACING_FRONT)  // Tested on API 24 Android version 7.0(Samsung S6)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", CameraCharacteristics.LENS_FACING_FRONT) // Tested on API 27 Android version 8.0(Nexus 6P)
                cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true)
            }
            else -> cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", 1)  // Tested API 21 Android version 5.0.1(Samsung S4)
        }
        startActivityForResult(cameraIntent, REQUEST_CODE_CAMERA)
    }

    private fun fetchStoreData(localStoreId: Long, storeId: String) {
        viewModel.getStoreByLocalStoreIdAndStoreId(localStoreId, storeId)
    }

    private fun setupObservers() {
        viewModel.storeLiveData.observe(this) {
            when (it) {
                is Result.Success -> {
                    val storeUIModel = it.data
                    binding.model = storeUIModel
                }
                else -> {
                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.storeVisitEventLiveData.observe(this) {
            val success = it.peekContent()
            if (success) {
                val intent = Intent(this, StoreDashboardActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_CAMERA -> {
                viewModel.visitStore()
            }
        }
    }
}