package com.example.myapplication.domain

data class Node(
    val id: Int,
    val message: String,
    var edges: List<Edge>
)