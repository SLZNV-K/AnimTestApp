package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.myapplication.data.dao.GameDao
import com.example.myapplication.data.entity.NodeEntity
import com.example.myapplication.data.entity.toDto
import com.example.myapplication.domain.dto.Node
import com.example.myapplication.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameDao: GameDao
) : GameRepository {

    override fun getNodes(): LiveData<List<Node>> = gameDao.getAllNodes().map { it.toDto() }

    override suspend fun insertNodes(nodes: List<NodeEntity>) {
        gameDao.insertNodes(nodes)
    }
}