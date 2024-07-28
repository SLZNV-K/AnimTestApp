package com.example.myapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.dto.CharacterType
import com.example.myapplication.domain.dto.Edges
import com.example.myapplication.domain.dto.Node

@Entity(tableName = "nodes")
data class NodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val message: String,
    @Embedded
    val edges: Edges,
    val foreground: Int = 0,
    val anim: Int = 0,
    @ColumnInfo(name = "characterType", defaultValue = "VOICE_OVER")
    val characterType: CharacterType = CharacterType.VOICE_OVER
) {
    fun toDto() = Node(
        id = id,
        message = message,
        edges = edges,
        foreground = foreground,
        anim = anim,
        characterType = characterType
    )

    companion object {
        fun fromDto(dto: Node) =
            NodeEntity(
                id = dto.id,
                message = dto.message,
                edges = dto.edges,
                foreground = dto.foreground,
                anim = dto.anim,
                characterType = dto.characterType
            )
    }
}

fun List<NodeEntity>.toDto(): List<Node> = map(NodeEntity::toDto)