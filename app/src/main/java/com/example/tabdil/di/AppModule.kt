package com.example.tabdil.di

import android.content.Context
import com.example.tabdil.data.db.TabdilDatabase
import com.example.tabdil.data.remoteservice.TabdilService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideTabdilService(): TabdilService {
        return TabdilService.create()
    }

    @Singleton
    @Provides
    fun provideTabdilDatabase(@ApplicationContext context: Context): TabdilDatabase {
        return TabdilDatabase.getDatabase(context)
    }
}