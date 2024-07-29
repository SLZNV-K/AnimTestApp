package com.example.myapplication.app.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.dao.NodeDao
import com.example.myapplication.data.dao.TranslationDao
import com.example.myapplication.data.database.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DbModule {

    @Singleton
    @Provides
    fun provideDb(
        @ApplicationContext
        context: Context
    ): GameDatabase = Room.databaseBuilder(context, GameDatabase::class.java, "app.db")
        .fallbackToDestructiveMigration()
        .createFromAsset("database/nodes.db")
//        .createFromAsset("database/translation.db")
        .build()

    @Provides
    fun provideGameDao(graphDb: GameDatabase): NodeDao = graphDb.gameDao()

    @Provides
    fun provideTranslationDao(graphDb: GameDatabase): TranslationDao = graphDb.translationDao()
}