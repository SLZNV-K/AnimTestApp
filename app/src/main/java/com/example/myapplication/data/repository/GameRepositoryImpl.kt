package com.example.myapplication.data.repository

import com.example.myapplication.data.dao.NodeDao
import com.example.myapplication.data.dao.TranslationsEdgeMessageDao
import com.example.myapplication.data.dao.TranslationsNodeMessageDao
import com.example.myapplication.data.entity.toDto
import com.example.myapplication.domain.dto.Edges
import com.example.myapplication.domain.dto.Node
import com.example.myapplication.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val nodeDao: NodeDao,
    translationsNodeMessageDao: TranslationsNodeMessageDao,
    translationsEdgeMessageDao: TranslationsEdgeMessageDao,
) : GameRepository {
    private val translationHelper =
        TranslationHelper(nodeDao, translationsNodeMessageDao, translationsEdgeMessageDao)

    override suspend fun getNodes(): Flow<List<Node>> =
        nodeDao.getAllNodes()
            .map { nodeList ->
                nodeList.toDto().map { node ->
                    val localeNodeMessage = translationHelper.getTranslatedNodeMessage(node.id)
                    val translatedEdgeList = node.edges.edges.map { edge ->
                        edge.copy(
                            message = translationHelper.getTranslatedEdgeMessage(
                                node.id,
                                edge.id
                            )
                        )
                    }
                    node.copy(message = localeNodeMessage, edges = Edges(translatedEdgeList))
                }
            }.flowOn(Dispatchers.IO)
}