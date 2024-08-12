package com.example.myapplication.app.presentation

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.app.presentation.Utils.PREFS_CURRENT_NODE
import com.example.myapplication.app.presentation.Utils.SHARED_PREFS_NAME
import com.example.myapplication.domain.dto.Node
import com.example.myapplication.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    application: Application,
    private val repository: GameRepository,
) : ViewModel() {
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    var allNodes: Flow<List<Node>> = MutableStateFlow(emptyList())
    var currentNode: Node? = null

    init {
        loadNodes()
    }

    fun updateCurrentNode(node: Node) {
        currentNode = node
        val editor = sharedPreferences.edit()
        editor.putInt(PREFS_CURRENT_NODE, node.id)
        editor.apply()
    }

    private fun loadNodes() = viewModelScope.launch {
        try {
            allNodes = repository.getNodes().flowOn(Dispatchers.IO)
            getCurrentNode()
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun getCurrentNode() {
        val currentNodeIndex = sharedPreferences.getInt(PREFS_CURRENT_NODE, 0)
        currentNode = allNodes.map { nodes ->
            nodes.find { it.id == currentNodeIndex }
        }.first()
    }
}