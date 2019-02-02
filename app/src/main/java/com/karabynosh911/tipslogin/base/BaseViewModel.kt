package com.karabynosh911.tipslogin.base

import android.arch.lifecycle.ViewModel
import com.karabynosh911.tipslogin.injection.component.DaggerViewModelInjector
import com.karabynosh911.tipslogin.injection.component.ViewModelInjector
import com.karabynosh911.tipslogin.injection.module.NetworkModule
import com.karabynosh911.tipslogin.ui.login.LoginViewModel

abstract  class BaseViewModel:ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when(this){
            is LoginViewModel -> injector.inject(this)
        }
    }


}