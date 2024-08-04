package com.example.myapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translationsMessage")
data class TranslationNodeMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "nodeId")
    val nodeId: Int,
    val locale: String,
    val translation: String
)