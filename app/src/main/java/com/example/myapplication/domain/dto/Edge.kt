package com.example.myapplication.domain.dto

data class Edge(
    val id: Int,
    val message: String,
    val nextNodeId: Int,
    val previousNodeId: Int = 0,
    val logicPoints: Int = 0,
    val empathyPoints: Int = 0,
    val equanimityPoints: Int = 0,
)

data class Edges(
    val edges: List<Edge> = emptyList()
)