package com.example.myapplication.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "edges",
    foreignKeys = [
        ForeignKey(entity = NodeDao::class, parentColumns = ["id"], childColumns = ["nexNode"])
    ]
)
data class EdgesDao(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nexNode: Int,
    val message: String
)