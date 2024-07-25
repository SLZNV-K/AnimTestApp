package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.entity.NodeEntity
import com.example.myapplication.data.entity.NodeTranslationEntity

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNodes(nodes: List<NodeEntity>)

    @Query("SELECT * FROM nodes")
    fun getAllNodes(): LiveData<List<NodeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTranslations(translations: List<NodeTranslationEntity>)

    @Query("SELECT * FROM node_translations WHERE nodeId = :nodeId")
    fun getTranslationsForNode(nodeId: Int): List<NodeTranslationEntity>


    @Query("SELECT * FROM node_translations")
    fun getAllTranslations(): List<NodeTranslationEntity>
}