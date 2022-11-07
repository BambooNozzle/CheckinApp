package com.cesaanwar.checkinapp.storepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.cesaanwar.checkinapp.R
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.databinding.ActivityStorePageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorePageActivity : AppCompatActivity() {

    lateinit var binding: ActivityStorePageBinding

    private val viewModel: StorePageViewModel by viewModels()

    companion object {
        const val LOCAL_STORE_ID = "localStoreId"
        const val STORE_ID = "storeId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_page)
        intent.apply {
            val localStoreId = getLongExtra(LOCAL_STORE_ID, -1L)
            val storeId = getStringExtra(STORE_ID) ?: ""
            fetchStoreData(localStoreId, storeId)
        }
        binding.button2.setOnClickListener {
            viewModel.visitStore()
        }
        setupObservers()
    }

    private fun fetchStoreData(localStoreId: Long, storeId: String) {
        viewModel.getStoreByLocalStoreIdAndStoreId(localStoreId, storeId)
    }

    private fun setupObservers() {
        viewModel.storeLiveData.observe(this) {
            when (it) {
                is Result.Success -> {
                    Toast.makeText(this, "Sukses", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(this, "Gagal", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}