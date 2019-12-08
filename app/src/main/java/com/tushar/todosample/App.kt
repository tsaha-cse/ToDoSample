package com.tushar.todosample

import android.app.Application
import com.tushar.todosample.di.dataModule
import com.tushar.todosample.di.domainModule
import com.tushar.todosample.di.presentationModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(
                dataModule,
                domainModule,
                presentationModule
            )
        )
    }
}
