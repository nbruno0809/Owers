package com.example.owers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.owers.data.User
import com.example.owers.data.owersDatabase
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), NewUserDialog.NewUserDialogListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = owersDatabase.getDatabase(applicationContext)

    }

    override fun onUserCreated(newUser: User) {
        thread {
            if (database.userDao().getIdByName(newUser.name) != null) {
                return@thread
            }
            val insertId = database.userDao().insert(newUser)
            newUser.id = insertId
            runOnUiThread {
                group.getUserAdapter().addItem(newUser)
            }
        }
    }


    companion object {
        private lateinit var database: owersDatabase
        fun getDB(): owersDatabase {
            return database
        }
    }


}