package com.pradyotprakash.notes.device.di

import android.content.Context
import androidx.room.Room
import com.pradyotprakash.notes.device.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DeviceModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "note-database"
    ).build()

    @Singleton
    @Provides
    fun provideNoteDatabase(
        appDatabase: AppDatabase,
    ) = appDatabase.noteDao()

    @Singleton
    @Provides
    fun provideUserDatabase(
        appDatabase: AppDatabase,
    ) = appDatabase.userDao()
}