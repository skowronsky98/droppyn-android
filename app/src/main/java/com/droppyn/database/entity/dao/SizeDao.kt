package com.droppyn.database.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droppyn.database.entity.DatabaseSize

@Dao
interface SizeDao {
    @Query("select * from sizechart")
    fun getSizeChart(): LiveData<List<DatabaseSize>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg databaseSize: DatabaseSize)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseSize: DatabaseSize)

    @Query("DELETE FROM sizechart")
    fun deleteAll()
}