package com.vasilyev.aituswaptalk.di

import android.app.Application
import com.vasilyev.core.di.coreUiModule
import com.vasilyev.di.callModule
import com.vasilyev.di.firebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AITUSwapTalkApp: Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        startKoin{
            androidLogger()

            androidContext(this@AITUSwapTalkApp)

            modules(
                callModule, firebaseModule, coreUiModule
            )
        }
    }
}