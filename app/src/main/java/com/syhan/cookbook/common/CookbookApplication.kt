package com.syhan.cookbook.common

import android.app.Application
import com.syhan.cookbook.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CookbookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CookbookApplication)
            modules(appModule)
        }
    }
}