package com.karabynosh911.tipslogin.ui.login

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.karabynosh911.tipslogin.R
import com.karabynosh911.tipslogin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    //Views
    private lateinit var btnLogin: Button
    private lateinit var edtPhone: EditText
    private lateinit var edtPassword: EditText
    private lateinit var listener :View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel

        initUI()
    }

    private fun initUI() {
        listener = View.OnClickListener {
            viewModel.loginUser("+380",edtPhone.text.toString(),edtPassword.text.toString()) }

        btnLogin = binding.btnLogin
        btnLogin.setOnClickListener(listener)
        edtPhone = binding.edtPhone
        edtPassword = binding.edtPassword

    }


}
