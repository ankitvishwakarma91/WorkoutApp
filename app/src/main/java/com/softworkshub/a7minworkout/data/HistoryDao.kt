package com.softworkshub.a7minworkout.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM `HISTORY-TABLE` ")
    fun fetchAllDate(): Flow<List<HistoryEntity>>
}