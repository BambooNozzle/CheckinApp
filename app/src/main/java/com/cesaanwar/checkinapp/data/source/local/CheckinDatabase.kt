package com.cesaanwar.checkinapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cesaanwar.checkinapp.data.Store
import com.cesaanwar.checkinapp.data.Visit
import com.cesaanwar.checkinapp.data.source.local.dao.StoreDao
import com.cesaanwar.checkinapp.data.source.local.dao.VisitDao

@Database(
    entities = [
        Store::class,
        Visit::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CheckinDatabase: RoomDatabase() {

    abstract fun storeDao(): StoreDao

    abstract fun visitDao(): VisitDao

}