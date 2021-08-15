package com.droppyn.database.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.droppyn.database.entity.DatabaseProfileAndSize
//import com.droppyn.database.entity.DatabaseProfileAndSize
import com.droppyn.database.entity.DatabaseUserAndSize

@Dao
interface UserAndSizeDao {
    @Transaction
    @Query("SELECT * FROM user")
    fun getUsersAndSize(): LiveData<List<DatabaseUserAndSize>>

    @Transaction
    @Query("SELECT * FROM user limit 1")
    fun getUserAndSize(): DatabaseUserAndSize?


    @Transaction
    @Query("SELECT * FROM profile limit 1")
    fun getProfileAndSize(): DatabaseProfileAndSize?
}