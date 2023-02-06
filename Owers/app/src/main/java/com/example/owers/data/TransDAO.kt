package com.example.owers.data

import androidx.room.*

@Dao
interface TransDAO {
    @Query("SELECT * FROM transactions")
    fun getAll(): List<com.example.owers.data.Transaction>

    @Insert
    fun insert(t: com.example.owers.data.Transaction): Long

    @Update
    fun update(t: com.example.owers.data.Transaction)

    @Delete
    fun deleteItem(t: com.example.owers.data.Transaction)

    @Query("DELETE FROM transactions")
    fun deleteAll()

}