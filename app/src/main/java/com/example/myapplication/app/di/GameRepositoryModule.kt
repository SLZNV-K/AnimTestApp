package com.example.myapplication.app.di

import com.example.myapplication.data.repository.GameRepositoryImpl
import com.example.myapplication.domain.repository.GameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface PostRepositoryModule {

    @Binds
    fun bindsPostRepository(impl: GameRepositoryImpl): GameRepository
}