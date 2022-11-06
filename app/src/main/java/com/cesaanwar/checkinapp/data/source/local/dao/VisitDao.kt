package com.cesaanwar.checkinapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cesaanwar.checkinapp.data.Visit

@Dao
interface VisitDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertVisit(vararg visits: Visit)

    @Query("select * from visit where storeId = :storeId and visitTimeMilis > :visitDateMilis")
    suspend fun getVisitsByStoreAndTime(storeId: String, visitDateMilis: Long): List<Visit>

}