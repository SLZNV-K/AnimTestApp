package com.example.myapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "translation",
    foreignKeys = [ForeignKey(
        entity = NodeEntity::class,
        parentColumns = ["id"],
        childColumns = ["nodeId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class TranslationNodeMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "nodeId")
    val nodeId: Int,
    val locale: String,
    val translation: String
)