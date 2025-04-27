package com.zeni.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zeni.core.data.database.dao.ItineraryDao
import com.zeni.core.data.database.dao.TripDao
import com.zeni.core.data.database.dao.UserDao
import com.zeni.core.data.database.entities.ActivityEntity
import com.zeni.core.data.database.entities.TripEntity
import com.zeni.core.data.database.entities.TripImageEntity
import com.zeni.core.data.database.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        TripEntity::class,
        TripImageEntity::class,
        ActivityEntity::class
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class TripsDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun tripDao(): TripDao

    abstract fun itineraryDao(): ItineraryDao
}