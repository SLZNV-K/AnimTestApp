package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.data.entity.NodeEntity
import com.example.myapplication.domain.dto.Edges
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDao {

    @Query("SELECT * FROM nodes")
    fun getAllNodes(): Flow<List<NodeEntity>>

    @Query("SELECT message FROM nodes WHERE id = :nodeId")
    suspend fun getMessage(nodeId: Int): String

    @Query("SELECT edges FROM nodes WHERE id = :nodeId")
    suspend fun getEdges(nodeId: Int): Edges
}