package com.droppyn.database.entity.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droppyn.database.entity.DatabaseToken

@Dao
interface TokenDao {

    @Query("select * from token limit 1")
    fun getToken(): DatabaseToken

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(token: DatabaseToken)

    @Query("DELETE FROM token")
    fun deleteAll()
}