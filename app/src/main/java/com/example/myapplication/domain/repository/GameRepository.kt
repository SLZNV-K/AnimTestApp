package com.example.myapplication.domain.repository

import com.example.myapplication.domain.dto.Node
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun getNodes(): Flow<List<Node>>
}