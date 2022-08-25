package com.example.test2.persistance.database

import android.content.Context
import androidx.room.Room

class DatabaseManager(context: Context) {

    private val databaseName = "image_items_database"

    private val db: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        databaseName
    ).build()

    suspend fun getAllItems(): List<ImageItem> = db.userDao().getAll()

    suspend fun storeItems(vararg items: ImageItem) = db.userDao().insertAll(*items)

    suspend fun deleteItems() = db.userDao().deleteAll()

}