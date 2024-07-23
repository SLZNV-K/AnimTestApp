package com.example.myapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nodes")
data class NodeDao(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val message: String,
    val edges: List<EdgesDao>
)