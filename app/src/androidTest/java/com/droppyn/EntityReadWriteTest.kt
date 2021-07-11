package com.droppyn

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droppyn.database.DroppynDatabase
import com.droppyn.database.entity.DatabaseBrand
import com.droppyn.database.entity.dao.BrandDao
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class EntityReadWriteTest {

    private lateinit var brandDao: BrandDao
    private lateinit var db: DroppynDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, DroppynDatabase::class.java
        ).build()
        brandDao = db.brandDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeBrandAndReadInList() = runBlocking{
        val brand: DatabaseBrand =
                TestUtil.createDatabseBrand()
        brandDao.insert(brand)

        val brands = brandDao.getBrands()
        brands.observeForever{}


        assertThat(brands.value?.get(0)?.id, equalTo(brand.id))
    }
}