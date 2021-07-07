package com.droppyn.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.droppyn.database.entity.*

@Database(entities = [DatabaseBrand::class, DatabaseShoe::class], version = 1)
abstract class DroppynDatabase : RoomDatabase() {
    abstract val brandDao : BrandDao
    abstract val shoeDao : ShoeDao

    //relation tables
    abstract val shoesAndBrandDao : ShoesAndBrandDao


}

private lateinit var INSTANCE : DroppynDatabase

fun getDatabase(context : Context) : DroppynDatabase{
    synchronized(DroppynDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                DroppynDatabase::class.java,
                "droppyn").build()
        }
    }
    return INSTANCE
}