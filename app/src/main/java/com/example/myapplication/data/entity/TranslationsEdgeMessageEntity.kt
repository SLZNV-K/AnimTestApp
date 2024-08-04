package com.example.myapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translationsEdgeMessage")
data class TranslationsEdgeMessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val translation: String,
    val nodeId: Int,
    val locale: String = "en"
)