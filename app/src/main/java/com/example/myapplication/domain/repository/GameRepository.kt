package com.example.myapplication.domain.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.dto.Node

interface GameRepository {
    fun getNodes(): LiveData<List<Node>>
}