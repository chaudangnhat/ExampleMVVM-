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

package com.gauravgoyal.mvvm_with_testing.features.userinfo.di

import android.content.Context
import com.gauravgoyal.mvvm_with_testing.features.userinfo.db.UserDao

import com.gauravgoyal.mvvm_with_testing.features.userinfo.db.UsersDatabase
import com.gauravgoyal.mvvm_with_testing.features.userinfo.viewmodel.UserViewModel

/**
 * Enables injection of data sources.
 */
object Injection {

    fun provideUserDataSource(context: Context): UserDao {
        val database = UsersDatabase.getInstance(context)
        return database.userDao()
    }

    fun provideViewModelFactory(context: Context): UserViewModel.ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return UserViewModel.ViewModelFactory(dataSource)
    }
}
