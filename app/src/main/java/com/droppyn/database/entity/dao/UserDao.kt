package com.droppyn.database.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droppyn.database.entity.DatabaseUser

@Dao
interface UserDao {
    @Query("select * from user")
    fun getUsers(): LiveData<List<DatabaseUser>>

    @Query("select * from user limit 1")
    fun getUser(): DatabaseUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseUser: DatabaseUser)

    @Query("DELETE FROM user")
    fun deleteAll()
}