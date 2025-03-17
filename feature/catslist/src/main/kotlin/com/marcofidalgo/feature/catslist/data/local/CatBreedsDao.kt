package com.marcofidalgo.feature.catslist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CatBreedsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(catBreed: CatBreed)


    @Query("SELECT * FROM catbreed")
    fun observe(): Flow<List<CatBreed>>
}