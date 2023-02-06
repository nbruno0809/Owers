package com.example.owers.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "userIdPaid") var userIdPaid: Long,
    @ColumnInfo(name = "userIdFor") var userIdFor: Long,
    @ColumnInfo(name = "amount") var amount: Int,

    ) {

}