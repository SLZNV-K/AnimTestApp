package com.example.myapplication.domain.dto

data class TranslationNodeMessage(
    val id: Int,
    val nodeId: Int,
    val locale: String,
    val translation: String
)