package com.karabynosh911.tipslogin.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.karabynosh911.tipslogin.R
import com.karabynosh911.tipslogin.injection.FactoryInjection
import com.karabynosh911.tipslogin.ui.profile.ProfileActivity
import com.rilixtech.CountryCodePicker
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private val countryName: String = Locale.getDefault().country

    //Views
    private lateinit var btnLogin: Button
    private lateinit var edtPhone: EditText
    private lateinit var edtPassword: EditText
    private lateinit var listener: View.OnClickListener
    private lateinit var pBar: ProgressBar
    private lateinit var countryCodePicker: CountryCodePicker
    private var errorSnack: Snackbar? = null
    private var updateData: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val viewModelFactory = FactoryInjection.provideViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        initUI()
        observeData()
        checkOrientation()
    }

    private fun initUI() {
        listener = View.OnClickListener {
            if (isValid(edtPhone.text.toString(), edtPassword.text.toString()))
                viewModel.loginUser(
                    countryCodePicker.selectedCountryCodeWithPlus,
                    edtPhone.text.toString(),
                    edtPassword.text.toString()
                )

        }

        pBar = findViewById(R.id.progress)
        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener(listener)
        edtPhone = findViewById(R.id.edtPhone)
        edtPassword = findViewById(R.id.edtPassword)
        countryCodePicker = findViewById(R.id.ccp)
        /*
        As far as Android Api provides only Country name and code of the LANGUAGE and not REGION of device,
        default country phone code by Region was't provided. But if you comment the line of code below,
        CountryCodePicker library will set country code of the SIM card by default, which is more better
        for UX
        */
        countryCodePicker.setCountryForNameCode(countryName)
    }

    private fun observeData() {
        viewModel.loadingVisibility.observe(this, Observer { if (it != null) pBar.visibility = it })
        viewModel.buttonClickable.observe(this, Observer { if (it != null) btnLogin.isClickable = it })
        viewModel.errorMessage.observe(this, Observer { showMessage(it) })
        viewModel.errorNetwork.observe(this, Observer { showNetworkError(it) })
        viewModel.startActivity.observe(this, Observer {
            if (it != null && it) {
                updateData = true
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun isValid(phoneNumber: String, password: String): Boolean {

        return when {
            phoneNumber.length !in 9..11 -> {
                showMessage(R.string.valid_phone)
                false
            }
            password.length !in 6..10 -> {
                showMessage(R.string.valid_password)
                false
            }
            else -> true
        }
    }

    private fun showMessage(msg: Int?) {
        if (msg != null)
            Toast.makeText(this.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showNetworkError(msg: Int?) {
        if(msg!=null){
            errorSnack = Snackbar.make(findViewById(R.id.parent_layout), msg, Snackbar.LENGTH_INDEFINITE)
            errorSnack?.setAction(R.string.retry, listener)
            errorSnack?.show()
        } else
            errorSnack?.dismiss()
    }

    override fun onStop() {
        super.onStop()
        if(updateData) onUpdateData()
    }


    private fun onUpdateData() {
        edtPhone.text.clear()
        edtPassword.text.clear()
        viewModel.onUpdateData()
        updateData = false
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
