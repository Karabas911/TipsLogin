package com.karabynosh911.tipslogin.ui.profile

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.karabynosh911.tipslogin.R
import com.karabynosh911.tipslogin.injection.FactoryInjection
import com.karabynosh911.tipslogin.injection.ViewModelFactory

class ProfileActivity : AppCompatActivity() {

    private lateinit var viewModel: ProfileViewModel
    //Views
    private lateinit var tvSurname: TextView
    private lateinit var tvName: TextView
    private lateinit var tvPhone: TextView
    private lateinit var pBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val factory: ViewModelFactory = FactoryInjection.provideViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)
        initUI()
        observeData()
    }

    private fun initUI() {
        tvSurname = findViewById(R.id.tvSurname)
        tvName = findViewById(R.id.tvName)
        tvPhone = findViewById(R.id.tvPhone)
        pBar = findViewById(R.id.progress)
    }

    private fun observeData() {
        viewModel.loadingVisibility.observe(this, Observer {if(it!=null) pBar.visibility = it})

        viewModel.user.observe(this, Observer { user ->
            tvSurname.text = user?.second_name
            tvName.text = user?.name
            tvPhone.text = "${user?.phone_code} ${user?.phone_number}"
        })
    }

}
