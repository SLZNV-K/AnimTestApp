package com.example.myapplication.app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.dto.Node
import com.example.myapplication.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository,
) : ViewModel() {

    var allNodes: Flow<List<Node>> = MutableStateFlow(emptyList())

    init {
        loadNodes()
    }

    private fun loadNodes() = viewModelScope.launch {
        try {
            allNodes = repository.getNodes().flowOn(Dispatchers.Default)
        } catch (e: Exception) {
            throw e
        }
    }
}