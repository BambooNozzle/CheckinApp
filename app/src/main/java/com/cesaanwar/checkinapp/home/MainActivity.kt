package com.cesaanwar.checkinapp.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.cesaanwar.checkinapp.R
import com.cesaanwar.checkinapp.databinding.ActivityMainBinding
import com.cesaanwar.checkinapp.home.adapter.MenuMenuAdapter
import com.cesaanwar.checkinapp.home.adapter.MenuStatisticsAdapter
import com.cesaanwar.checkinapp.login.LoginActivity
import com.cesaanwar.checkinapp.storelist.StoreListActivity
import com.cesaanwar.checkinapp.uimodel.mapper.DummyMapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    lateinit var statisticsAdapter: MenuStatisticsAdapter
    lateinit var menuAdapter: MenuMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupStatisticAdapter()
        setupMenuAdapter()
        setupObservable()
        setupLogoutBtn()
    }

    private fun setupLogoutBtn() {
        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    private fun setupObservable() {
        viewModel.goToStoreListEvent.observe(this) {
            val data = it.peekContent()
            if (data) {
                val intent = Intent(this, StoreListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setupMenuAdapter() {
        statisticsAdapter = MenuStatisticsAdapter()
        binding.rvStatistics.adapter = statisticsAdapter
        statisticsAdapter.submitList(
            DummyMapper.getMenuStatistics()
        )
    }

    private fun setupStatisticAdapter() {
        menuAdapter = MenuMenuAdapter(viewModel)
        binding.rvMenu.adapter = menuAdapter
        menuAdapter.submitList(
            DummyMapper.getMenuMenus()
        )
    }
}