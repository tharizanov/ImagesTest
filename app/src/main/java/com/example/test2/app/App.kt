package com.example.test2.app

import android.app.Application
import com.example.test2.app.di.*
import com.example.test2.persistance.di.persistanceModule
import com.example.test2.repository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            fragmentFactory()
            modules(
                fragmentModule,
                persistanceModule,
                repositoryModule,
                viewModelModule,
                toolModule
            )
        }
    }

}