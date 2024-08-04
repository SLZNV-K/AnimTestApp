package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TranslationsNodeMessageDao {
    @Query("SELECT translation FROM translationsMessage WHERE nodeId = :nodeId AND locale = :language")
    suspend fun getTranslation(nodeId: Int, language: String): String?
}