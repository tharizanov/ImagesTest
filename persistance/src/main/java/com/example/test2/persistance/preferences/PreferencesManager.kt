package com.example.test2.persistance.preferences

import android.content.Context

class PreferencesManager(context: Context) {

    private val KEY_LAST_SEARCH = "KEY_LAST_SEARCH"

    private val preferences = context.getSharedPreferences("${context.packageName}.preferences", Context.MODE_PRIVATE)

    fun getLastSearch(): String? = preferences.getString(KEY_LAST_SEARCH, null)

    fun storeLastSearch(keyword: String?) = preferences.edit().putString(KEY_LAST_SEARCH, keyword).commit()

}