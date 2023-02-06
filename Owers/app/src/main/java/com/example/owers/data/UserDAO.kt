package com.example.owers.data

import androidx.room.*

@Dao
interface UserDAO {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User): Long

    @Update
    fun update(user: User)

    @Delete
    fun deleteItem(user: User)

    @Query("SELECT id FROM users WHERE name=:n")
    fun getIdByName(n: String): Long?

    @Query("SELECT * FROM users WHERE id=:id")
    fun getUserById(id: Long): User?
}

