package com.karabynosh911.tipslogin.ui.profile

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.karabynosh911.tipslogin.R
import com.karabynosh911.tipslogin.injection.FactoryInjection
import com.karabynosh911.tipslogin.injection.ViewModelFactory
import com.rilixtech.CountryCodePicker
import java.util.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var viewModel: ProfileViewModel
    //Views
    private lateinit var tvSurname: TextView
    private lateinit var tvName: TextView
    private lateinit var tvPhone: TextView
    private lateinit var pBar : ProgressBar
    private lateinit var countryCodePicker: CountryCodePicker


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val factory: ViewModelFactory = FactoryInjection.provideViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)
        initUI()
        observeData()
        checkOrientation()
    }

    private fun initUI() {
        tvSurname = findViewById(R.id.tvSurname)
        tvName = findViewById(R.id.tvName)
        tvPhone = findViewById(R.id.tvPhone)
        pBar = this.findViewById(R.id.progress)
        countryCodePicker = findViewById(R.id.ccp)
        countryCodePicker.setCountryForNameCode(Locale.getDefault().country)
    }

    private fun observeData() {
        viewModel.loadingVisibility.observe(this, Observer {if(it!=null) pBar.visibility = it})

        viewModel.user.observe(this, Observer { user ->
            if(user!=null) {
                tvSurname.text = user.second_name
                tvName.text = user.name
                tvPhone.text = " ${user.phone_number}"

                val phoneCode = user.phone_code.substring(1, user.phone_code.length).toInt()
                countryCodePicker.setDefaultCountryUsingPhoneCode(phoneCode)
                countryCodePicker.resetToDefaultCountry()
            }
                })
    }

    private fun checkOrientation() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

}
