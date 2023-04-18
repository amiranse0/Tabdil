package com.example.tabdil.di

import com.example.tabdil.data.remoteservice.TabdilService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}