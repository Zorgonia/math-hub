package com.kyang.mathhub.domain.repo.tip

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TipRepositoryModule {
    @Binds
    fun provideTipRepositoryImpl(repository: TipRepositoryImpl): TipRepository
}