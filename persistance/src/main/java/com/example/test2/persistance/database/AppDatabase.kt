package com.example.test2.persistance.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ImageItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): ImageItemDao

}