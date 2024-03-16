package com.kyang.mathhub.repo.math

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MathQuestionRepositoryModule {
    @Binds
    fun provideMathQuestionRepositoryImpl(repository: MathQuestionRepositoryImpl): MathQuestionRepository
}