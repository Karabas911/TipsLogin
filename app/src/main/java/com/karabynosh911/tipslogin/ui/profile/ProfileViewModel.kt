package com.karabynosh911.tipslogin.ui.profile

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.karabynosh911.tipslogin.database.UserDao
import com.karabynosh911.tipslogin.model.User
import com.karabynosh911.tipslogin.ui.login.LoginViewModel.Companion.TAG
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(private val userDao: UserDao) : ViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val user: MutableLiveData<User> = MutableLiveData()
    private var subscription: Disposable


    init {
        subscription = Observable.fromCallable { userDao.getUsersList() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSelectUserStart() }
            .doOnTerminate { onSelectUserFinish() }
            .subscribe(
            {list ->
                Log.d(TAG, "User selected successfully")
                onSuccess(list)
            },
            { Log.d(TAG, "ERROR, User wasn't selected")
            onIError()}
        )
    }

    private fun onSelectUserStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onSelectUserFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onSuccess(list: List<User>) {
        if(list.isNotEmpty())
            user.postValue(list.get(0))
    }

    private fun onIError() {

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}