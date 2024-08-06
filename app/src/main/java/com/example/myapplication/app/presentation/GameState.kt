package com.example.myapplication.app.presentation

import com.example.myapplication.domain.dto.Node
import kotlinx.coroutines.flow.Flow

data class GameState(
    val allNodes: Flow<List<Node>>? = null,
    val currentNode: Node? = null
)