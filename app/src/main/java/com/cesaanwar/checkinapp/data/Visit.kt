package com.cesaanwar.checkinapp.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "visit", indices = [Index(value = ["visitTimeMilis"])] )
data class Visit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val localStoreId: Long,
    val storeId: String,
    val visitTimeMilis: Long
)
