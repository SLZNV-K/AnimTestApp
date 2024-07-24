package com.example.myapplication.domain.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.entity.NodeEntity
import com.example.myapplication.domain.dto.Node

interface GameRepository {
    fun getNodes(): LiveData<List<Node>>
    suspend fun insertNodes(nodes: List<NodeEntity>)
}