package com.karabynosh911.tipslogin.ui.login

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.karabynosh911.tipslogin.base.BaseViewModel
import com.karabynosh911.tipslogin.network.TipsApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class LoginViewModel :BaseViewModel(){

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    @Inject
    lateinit var tipsApi: TipsApi

    private lateinit var subscription: Disposable

    init {
        loginUser("+380","671112233","qwerty")
    }

    fun loginUser(phoneCode:String, phoneNumber:String, password:String){
        subscription = tipsApi.login(phoneCode,phoneNumber,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{ onLoginStart()}
            .doOnTerminate { onLoginFinished() }
            .subscribe(
                {onSuccess()},
                {onIError()})

    }


    private fun onLoginStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onLoginFinished() {
        loadingVisibility.value = View.GONE
    }


    private fun onSuccess() {

    }

    private fun onIError(){

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}