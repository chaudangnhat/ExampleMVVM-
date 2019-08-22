package com.gauravgoyal.mvvm_with_testing.application.di.module

import android.app.Application
import com.gauravgoyal.mvvm_with_testing.application.BaseApp
import com.gauravgoyal.mvvm_with_testing.application.di.scope.PerApplication
import com.gauravgoyal.mvvm_with_testing.features.userinfo.db.UsersDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }

    @Provides
    @Singleton
    fun provideUserDatabase(): UsersDatabase {
        return UsersDatabase.getInstance(baseApp)
    }
}