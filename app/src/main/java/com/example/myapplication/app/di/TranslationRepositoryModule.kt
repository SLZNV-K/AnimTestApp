package com.example.myapplication.app.di

import com.example.myapplication.data.repository.TranslationRepositoryImpl
import com.example.myapplication.domain.repository.TranslationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface TranslationRepositoryModule {

    @Binds
    fun bindsPostRepository(impl: TranslationRepositoryImpl): TranslationRepository
}