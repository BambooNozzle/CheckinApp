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

    @Query("select * from visit where visitTimeMilis > :visitDateMilis")
    suspend fun getVisitsByStoreAndTime(visitDateMilis: Long): List<Visit>

    @Query("select * from visit where localStoreId = :localStoreId and storeId = :storeId order by visitTimeMilis desc limit 1")
    suspend fun getLatestVisitsByStore(localStoreId: Long, storeId: String): Visit?

}