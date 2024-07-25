package com.example.myapplication.data.repository

import com.example.myapplication.data.dao.GameDao
import com.example.myapplication.data.entity.NodeTranslationEntity
import com.example.myapplication.domain.repository.TranslationRepository
import javax.inject.Inject

class TranslationRepositoryImpl @Inject constructor(
    private val gameDao: GameDao
) : TranslationRepository {
    override fun getTranslationsForNode(nodeId: Int): List<NodeTranslationEntity> {
        return gameDao.getTranslationsForNode(nodeId)
    }

    override fun insertTranslation(translations: List<NodeTranslationEntity>) {
        gameDao.insertTranslations(translations)
    }
}