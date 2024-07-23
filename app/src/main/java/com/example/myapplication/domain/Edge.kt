package com.example.myapplication.domain

data class Edge(
    val id: Int,
    val message: String,
    val nextNode: Node,
)