package com.example.myapplication.app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.entity.NodeEntity
import com.example.myapplication.domain.dto.Node
import com.example.myapplication.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository,
) : ViewModel() {

    val allNodes: LiveData<List<Node>> = repository.getNodes()

    fun insertNodes(nodes: List<NodeEntity>) = viewModelScope.launch {
        repository.insertNodes(nodes)
    }
}