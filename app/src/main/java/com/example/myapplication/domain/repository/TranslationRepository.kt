package com.example.myapplication.domain.repository

import com.example.myapplication.data.entity.NodeTranslationEntity

interface TranslationRepository {

    fun getTranslationsForNode(nodeId: Int): List<NodeTranslationEntity>
    fun insertTranslation(translations: List<NodeTranslationEntity>)
}