package com.droppyn.database.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droppyn.database.entity.DatabaseProfile

@Dao
interface ProfileDao {
    @Query("select * from profile")
    fun getProfiles(): LiveData<List<DatabaseProfile>>

    @Query("select * from profile limit 1")
    fun getProfile(): DatabaseProfile

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: DatabaseProfile)

    @Query("DELETE FROM profile")
    fun deleteAll()
}