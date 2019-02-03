package com.karabynosh911.tipslogin.ui.login

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import com.karabynosh911.tipslogin.base.BaseViewModel
import com.karabynosh911.tipslogin.database.UserDao
import com.karabynosh911.tipslogin.model.User
import com.karabynosh911.tipslogin.network.TipsApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class LoginViewModel(private val userDao: UserDao) : BaseViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val buttonClickable: MutableLiveData<Boolean> = MutableLiveData()
    val startActivity: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var mUser: User

    @Inject
    lateinit var tipsApi: TipsApi

    private var subscription: Disposable? = null

    init {
//        startActivity.value = false
//        buttonClickable.value = true
        loadingVisibility.value = View.GONE
    }

    fun loginUser(phoneCode: String, phoneNumber: String, password: String) {
        subscription = tipsApi.login(phoneCode, phoneNumber, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onLoginStart() }
            .doOnTerminate { onLoginFinished() }
            .subscribe(
                { response ->
                    mUser = response.user
                    insertUserToDb(mUser)
                },
                { onIError() })

    }

    private fun insertUserToDb(user: User) {
        subscription = Observable.fromCallable {
            userDao.deleteAllUsers()
            userDao.insertUser(user)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    Log.d(TAG, "User inserted successfully")
                    onSuccess()
                },
                { Log.d(TAG, "ERROR, User wasn't inserted") }
            )
    }


    private fun onLoginStart() {
        loadingVisibility.value = View.VISIBLE
        buttonClickable.value = false
    }

    private fun onLoginFinished() {
        loadingVisibility.value = View.GONE
    }


    private fun onSuccess() {
        startActivity.postValue(true)
    }

    private fun onIError() {
        buttonClickable.value = true
    }

    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }

    companion object {
        const val TAG = "user database"
    }
}