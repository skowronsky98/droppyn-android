package com.droppyn.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.droppyn.database.entity.*
import com.droppyn.database.entity.dao.*

@Database(entities = [
    DatabaseBrand::class,
    DatabaseShoe::class,
    DatabaseSize::class,
    DatabaseUser::class
                     ], version = 1)
abstract class DroppynDatabase : RoomDatabase() {
    abstract val brandDao: BrandDao
    abstract val shoeDao: ShoeDao
    abstract val sizeDao: SizeDao
    abstract val userDao: UserDao

    //relation tables
    abstract val shoesAndBrandDao : ShoesAndBrandDao
    abstract val sizeAndBrandDao : SizeAndBrandDao
    abstract val userAndSizeDao : UserAndSizeDao


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