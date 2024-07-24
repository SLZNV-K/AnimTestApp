package com.example.myapplication.domain.dto

data class Node(
    val id: Int,
    val message: String,
    var edges: Edges
)