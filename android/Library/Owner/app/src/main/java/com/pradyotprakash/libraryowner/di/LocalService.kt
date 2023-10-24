package com.pradyotprakash.libraryowner.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.pradyotprakash.libraryowner.data.services.appconfig.AppConfigService
import com.pradyotprakash.libraryowner.data.services.appconfig.AppConfigServiceImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalService {
    private val Context.appConfigDataStore by preferencesDataStore(
        name = AppConfigServiceImplementation.PREFS_APP_CONFIG
    )

    @Singleton
    @Provides
    fun provideAppConfigService(
        @ApplicationContext context: Context
    ): AppConfigService = AppConfigServiceImplementation(context.appConfigDataStore)
}