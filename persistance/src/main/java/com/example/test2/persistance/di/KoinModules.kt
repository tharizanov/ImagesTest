package com.example.test2.persistance.di

import com.example.test2.persistance.database.DatabaseManager
import com.example.test2.persistance.preferences.PreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val persistanceModule = module {
    single { DatabaseManager(androidContext()) }
    single { PreferencesManager(androidContext()) }
}