package com.example.myapplication.app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.entity.NodeEntity
import com.example.myapplication.data.entity.NodeTranslationEntity
import com.example.myapplication.domain.dto.Node
import com.example.myapplication.domain.repository.GameRepository
import com.example.myapplication.domain.repository.TranslationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val translationRepository: TranslationRepository
) : ViewModel() {

    private var translationId = 0
    val allNodes: LiveData<List<Node>> = gameRepository.getNodes()

    fun insertNodes(nodes: List<NodeEntity>) = viewModelScope.launch {
        gameRepository.insertNodes(nodes)
    }

    fun addTranslation(nodeId: Int, languageCode: String, message: String) {
        val translation = NodeTranslationEntity(translationId, nodeId, languageCode, message)
        translationRepository.insertTranslation(listOf(translation))
        translationId++
    }
}