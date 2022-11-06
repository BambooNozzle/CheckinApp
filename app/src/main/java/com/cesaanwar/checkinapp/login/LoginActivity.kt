package com.cesaanwar.checkinapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.cesaanwar.checkinapp.R
import com.cesaanwar.checkinapp.databinding.ActivityLoginBinding
import com.cesaanwar.checkinapp.storelist.StoreListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.button.setOnClickListener {
            viewModel.login("pitjarus", "admin")
            val intent = Intent(this, StoreListActivity::class.java)
            startActivity(intent)
        }
    }
}