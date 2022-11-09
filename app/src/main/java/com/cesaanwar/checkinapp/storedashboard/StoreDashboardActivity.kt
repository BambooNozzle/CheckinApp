package com.cesaanwar.checkinapp.storedashboard

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cesaanwar.checkinapp.R
import com.cesaanwar.checkinapp.data.Result.Success
import com.cesaanwar.checkinapp.databinding.ActivityStoreDashboardBinding
import com.cesaanwar.checkinapp.storedashboard.adapter.DashboardInfoAdapter
import com.cesaanwar.checkinapp.storedashboard.adapter.DashboardMenuAdapter
import com.cesaanwar.checkinapp.uimodel.mapper.DummyMapper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StoreDashboardActivity : AppCompatActivity() {

    private val viewModel: StoreDashboardViewModel by viewModels()
    lateinit var binding: ActivityStoreDashboardBinding
    lateinit var menuAdapter: DashboardMenuAdapter
    lateinit var infoAdapter: DashboardInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_dashboard)
        setupObservables()
        setupMenuAdapter()
        setupInfoAdapter()
        setupEndButton()
    }

    private fun setupEndButton() {
        binding.btnEndVisit.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupInfoAdapter() {
        infoAdapter = DashboardInfoAdapter()
        binding.rvStatistic.adapter = infoAdapter
        infoAdapter.submitList(
            DummyMapper.getDashboardInfo()
        )
    }

    private fun setupMenuAdapter() {
        menuAdapter = DashboardMenuAdapter()
        binding.rvMenu.adapter = menuAdapter
        menuAdapter.submitList(
            DummyMapper.getDashboardMenus()
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchActiveVisit()
    }

    private fun setupObservables() {
        viewModel.activeVisitLiveData.observe(this) {
            when (it) {
                is Success -> {
                    val data = it.data
                    if (data.isNotEmpty()) {
                        val activeVisit = data.first()
                        viewModel.getActiveVisitData(activeVisit.localStoreId, activeVisit.storeId)
                    } else {
                        goBackToListPage()
                    }
                }
                else -> {
                    goBackToListPage()
                }
            }
        }
        viewModel.visitDataLiveData.observe(this) {
            if (it is Success) {
                val data = it.data
                binding.storeDashboardUIModel = data
            }
        }
        viewModel.leaveVisitEventLiveData.observe(this) {
            val data = it.peekContent()
            if (data) {
                goBackToListPage()
            }
        }
    }

    private fun goBackToListPage() {
        finish()
    }

    override fun onBackPressed() {
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        viewModel.leaveVisit()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        dialog.dismiss()
                    }
                }
            }

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
            .setNegativeButton("No", dialogClickListener).show()
    }

}