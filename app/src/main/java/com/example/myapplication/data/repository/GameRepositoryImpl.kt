package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.myapplication.data.dao.NodeDao
import com.example.myapplication.data.entity.toDto
import com.example.myapplication.domain.dto.Node
import com.example.myapplication.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val nodeDao: NodeDao
) : GameRepository {

    override fun getNodes(): LiveData<List<Node>> = nodeDao.getAllNodes().map { it.toDto() }
}