package com.zeni.core.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.zeni.core.data.database.entities.TripEntity
import com.zeni.core.data.database.relations.TripRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {

    @Transaction
    @Query("""
        SELECT * FROM trip_table 
        WHERE user_owner = :ownerUid
        ORDER BY start_date ASC
    """)
    fun getTrips(ownerUid: String): Flow<List<TripRelation>>

    @Transaction
    @Query("SELECT * FROM trip_table WHERE name = :tripName")
    fun getTrip(tripName: String): Flow<TripRelation>

    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM trip_table 
            WHERE name = :tripName AND user_owner = :ownerUid
        )
    """)
    suspend fun existsTrip(tripName: String, ownerUid: String): Boolean

    @Upsert
    suspend fun addTrip(trip: TripEntity)

    @Delete
    suspend fun deleteTrip(trip: TripEntity)
}