package com.example.myapplication.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.dto.Edges
import com.example.myapplication.domain.dto.Node

@Entity(tableName = "nodes")
data class NodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val message: String,
    @Embedded
    val edges: Edges
) {
    fun toDto() = Node(
        id = id,
        message = message,
        edges = edges
    )

    companion object {
        fun fromDto(dto: Node) =
            NodeEntity(
                id = dto.id,
                message = dto.message,
                edges = dto.edges
            )
    }
}

fun List<NodeEntity>.toDto(): List<Node> = map(NodeEntity::toDto)
fun List<Node>.toEntity(): List<NodeEntity> = map(NodeEntity::fromDto)