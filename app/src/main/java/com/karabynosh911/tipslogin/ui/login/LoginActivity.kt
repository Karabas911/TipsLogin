package com.karabynosh911.tipslogin.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.karabynosh911.tipslogin.R
import com.karabynosh911.tipslogin.injection.FactoryInjection
import com.karabynosh911.tipslogin.ui.profile.ProfileActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    //Views
    private lateinit var btnLogin: Button
    private lateinit var edtPhone: EditText
    private lateinit var edtPassword: EditText
    private lateinit var listener :View.OnClickListener
    private lateinit var pBar :ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val viewModelFactory = FactoryInjection.provideViewModelFactory(this)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel::class.java)
        initUI()
        observeData()
    }


    private fun initUI() {
        listener = View.OnClickListener {
            viewModel.loginUser("+380",edtPhone.text.toString(),edtPassword.text.toString()) }

        pBar = findViewById(R.id.progress)
        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener(listener)
        edtPhone = findViewById(R.id.edtPhone)
        edtPassword = findViewById(R.id.edtPassword)

    }

    private fun observeData() {
        viewModel.loadingVisibility.observe(this, Observer {if(it!=null) pBar.visibility = it})
        viewModel.buttonClickable.observe(this, Observer { if(it!=null) btnLogin.isClickable = it })
        viewModel.startActivity.observe(this, Observer {
            if(it!=null && it) {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        })
    }



}
