package com.gauravgoyal.mvvm_with_testing.application.di.component

import com.gauravgoyal.mvvm_with_testing.application.di.module.ApplicationModule
import com.gauravgoyal.mvvm_with_testing.application.BaseApp
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: BaseApp)

}