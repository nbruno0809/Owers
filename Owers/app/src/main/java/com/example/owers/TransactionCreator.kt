package com.example.owers

import com.example.owers.data.Transaction
import kotlin.concurrent.thread

object TransactionCreator {
    val db = MainActivity.getDB()

    fun createNewTransaction(newT: Transaction) {
        thread {
            val insertId = db.transDao().insert(newT)
            newT.id = insertId
            val uBy = db.userDao().getUserById(newT.userIdPaid)
            val uFor = db.userDao().getUserById(newT.userIdFor)
            if (uBy == null || uFor == null) {
                throw NullPointerException()
            }
            if (uBy.id != uFor.id) {
                uBy.balance += newT.amount
                uFor.balance -= newT.amount
                db.userDao().update(uBy)
                db.userDao().update(uFor)
            }
        }
    }

    fun removeAllTransaction() {
        thread {
            db.transDao().deleteAll()
        }
    }
}
