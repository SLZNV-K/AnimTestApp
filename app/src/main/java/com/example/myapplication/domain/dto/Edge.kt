package com.example.myapplication.domain.dto

data class Edge(
    val id: Int,
    val message: String,
    val nextNodeId: Int
)

data class Edges(
    val edges: List<Edge> = emptyList()
)