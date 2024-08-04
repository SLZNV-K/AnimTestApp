package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TranslationsEdgeMessageDao {
    @Query(
        "SELECT translation FROM translationsEdgeMessage " +
                "WHERE nodeId = :nodeId AND locale = :language AND id = :id"
    )
    suspend fun getTranslation(id: Int, nodeId: Int, language: String): String?
}