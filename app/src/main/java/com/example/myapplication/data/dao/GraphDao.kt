package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.entity.EdgesDao
import com.example.myapplication.data.entity.NodeDao

@Dao
interface GraphDao {
    @Insert
    suspend fun insertNode(node: NodeDao): Long

    @Insert
    suspend fun insertEdge(edge: EdgesDao): Long

    @Query("SELECT * FROM nodes")
    suspend fun getAllNodes(): List<NodeDao>

    @Query("SELECT * FROM edges")
    suspend fun getAllEdges(): List<EdgesDao>
}