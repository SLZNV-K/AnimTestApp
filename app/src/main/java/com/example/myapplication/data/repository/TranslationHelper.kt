package com.example.myapplication.data.repository

import com.example.myapplication.data.dao.NodeDao
import com.example.myapplication.data.dao.TranslationsEdgeMessageDao
import com.example.myapplication.data.dao.TranslationsNodeMessageDao
import java.util.Locale

class TranslationHelper(
    private val nodeDao: NodeDao,
    private val translationsNodeMessageDao: TranslationsNodeMessageDao,
    private val translationsEdgeMessageDao: TranslationsEdgeMessageDao,
) {

    suspend fun getTranslatedNodeMessage(nodeId: Int): String {
        val currentLocale = Locale.getDefault()
        val currentLanguage = currentLocale.language

        val translation = translationsNodeMessageDao.getTranslation(nodeId, currentLanguage)
        return translation
            ?: nodeDao.getMessage(nodeId)
    }

    suspend fun getTranslatedEdgeMessage(nodeId: Int, id: Int): String {
        val currentLocale = Locale.getDefault()
        val currentLanguage = currentLocale.language

        val translation = translationsEdgeMessageDao.getTranslation(id, nodeId, currentLanguage)
        return translation
            ?: nodeDao.getEdges(nodeId).edges.find { it.id == id }?.message ?: ""
    }
}