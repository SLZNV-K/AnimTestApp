package com.example.myapplication.app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.dto.Node
import com.example.myapplication.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    repository: GameRepository,
) : ViewModel() {

    val allNodes: LiveData<List<Node>> = repository.getNodes()
}