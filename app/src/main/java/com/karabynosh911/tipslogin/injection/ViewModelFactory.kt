/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karabynosh911.tipslogin.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.karabynosh911.tipslogin.database.UserDao
import com.karabynosh911.tipslogin.ui.login.LoginViewModel
import com.karabynosh911.tipslogin.ui.profile.ProfileViewModel


class ViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->
                return LoginViewModel(userDao) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) ->
                return ProfileViewModel(userDao) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
