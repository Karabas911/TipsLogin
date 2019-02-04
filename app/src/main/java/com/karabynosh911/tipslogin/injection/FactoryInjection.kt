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

import android.content.Context
import com.karabynosh911.tipslogin.database.AppDatabase
import com.karabynosh911.tipslogin.database.UserDao

// Not Dagger 2 Injection. Provides ViewModel with UserDao
object FactoryInjection {

    fun provideUserDataSource(context: Context): UserDao {
        val database = AppDatabase.getInstance(context)
        return database.userDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val userDao = provideUserDataSource(context)
        return ViewModelFactory(userDao)
    }
}
