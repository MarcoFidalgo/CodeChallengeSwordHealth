package com.marcofidalgo.codechallenge.main.di

import android.app.Application
import androidx.room.Room
import com.marcofidalgo.codechallenge.main.Constants
import com.marcofidalgo.feature.catslist.data.local.CatBreedsDao
import com.marcofidalgo.feature.catslist.data.local.MyRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun myRoomDatabase(application: Application): MyRoomDatabase {
        return Room.databaseBuilder(
            application,
            MyRoomDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }

    @Provides
    fun catBreedsDao(database: MyRoomDatabase): CatBreedsDao {
        return database.catBreedsDao
    }
}