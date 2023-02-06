package com.example.owers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Transaction::class], version = 1)
//@TypeConverters(value = [ShoppingItem.Category::class])
abstract class owersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun transDao(): TransDAO

    companion object {
        fun getDatabase(applicationContext: Context): owersDatabase {
            return Room.databaseBuilder(
                applicationContext,
                owersDatabase::class.java,
                "Owers"
            ).build();
        }
    }
}