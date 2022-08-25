package com.example.test2.persistance.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageItemDao {

    @Query("SELECT * FROM imageitem")
    suspend fun getAll(): List<ImageItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: ImageItem)

    @Query("DELETE FROM imageitem")
    suspend fun deleteAll()

}