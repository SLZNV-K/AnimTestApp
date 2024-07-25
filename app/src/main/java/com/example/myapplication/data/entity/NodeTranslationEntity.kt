package com.example.myapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.dto.Node

@Entity(tableName = "node_translations")
data class NodeTranslationEntity(
    @PrimaryKey(autoGenerate = true)
    val translationId: Int,
    val nodeId: Int,
    val languageCode: String,
    val message: String
)

fun List<NodeEntity>.toDto(translations: List<NodeTranslationEntity>): List<Node> {
    return map { nodeEntity ->
        val nodeTranslations = translations.filter { it.nodeId == nodeEntity.id }
        nodeEntity.toDto(nodeTranslations)
    }
}