package com.pocket.codelabsjetpackcompose.base

import android.app.Application
import com.pocket.codelabsjetpackcompose.base.di.AppContainer
import com.pocket.codelabsjetpackcompose.base.di.DefaultAppContainer

class CodeLabsApplication: Application() {
lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}