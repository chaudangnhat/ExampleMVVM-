package com.gauravgoyal.mvvm_with_testing.application

import android.app.Application
import com.gauravgoyal.mvvm_with_testing.application.di.component.ApplicationComponent
import com.gauravgoyal.mvvm_with_testing.application.di.component.DaggerApplicationComponent
import com.gauravgoyal.mvvm_with_testing.application.di.module.ApplicationModule

class BaseApp : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        setup()
    }

    private fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()

        component.inject(this)
    }
}