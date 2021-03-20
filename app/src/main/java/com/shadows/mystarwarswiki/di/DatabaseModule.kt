package com.shadows.mystarwarswiki.di

import android.content.Context
import androidx.room.Room
import com.shadows.mystarwarswiki.data.MyStarWarsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideYourDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(app,
        MyStarWarsDatabase::class.java, "starwars_database")
        .build()

    @Singleton
    @Provides
    fun provideUserDao(db: MyStarWarsDatabase) = db.getCharacterDao()
}