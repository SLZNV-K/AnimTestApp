package com.example.myapplication.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.dto.CharacterType
import com.example.myapplication.domain.dto.Edges
import com.example.myapplication.domain.dto.Node
import java.util.Locale

@Entity(tableName = "nodes")
data class NodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Embedded
    val edges: Edges,
    val foreground: Int = 0,
    val emilyAnim: Int = 0,
    val characterType: CharacterType = CharacterType.VOICE_OVER
) {
    fun toDto(translations: List<NodeTranslationEntity>): Node {
        val currentLanguage = Locale.getDefault().language
        val message = translations.find { it.languageCode == currentLanguage }?.message ?: ""
        return Node(
            id = id,
            message = message,
            edges = edges,
            foreground = foreground,
            emilyAnim = emilyAnim,
            characterType = characterType
        )
    }

    companion object {
        fun fromDto(dto: Node): NodeEntity {
            return NodeEntity(
                id = dto.id,
                edges = dto.edges,
                foreground = dto.foreground,
                emilyAnim = dto.emilyAnim,
                characterType = dto.characterType
            )
        }
    }
}
//fun List<NodeEntity>.toDto(): List<Node> = map(NodeEntity::toDto)
//fun List<Node>.toEntity(): List<NodeEntity> = map(NodeEntity::fromDto)