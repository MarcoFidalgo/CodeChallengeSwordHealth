package com.marcofidalgo.feature.catslist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        CatBreed::class
    ],
    version = 1
)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract val catBreedsDao: CatBreedsDao
}