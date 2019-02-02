package com.karabynosh911.tipslogin.injection.component

import com.karabynosh911.tipslogin.injection.module.NetworkModule
import com.karabynosh911.tipslogin.ui.login.LoginViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    //inject method for LoginViewModel
    fun inject(loginViewModel: LoginViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}