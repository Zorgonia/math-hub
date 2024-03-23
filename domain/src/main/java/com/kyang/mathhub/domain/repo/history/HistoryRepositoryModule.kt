package com.kyang.mathhub.domain.repo.history

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HistoryRepositoryModule {
    @Binds
    fun provideHistoryRepositoryImpl(repository: HistoryRepositoryImpl): HistoryRepository
}
