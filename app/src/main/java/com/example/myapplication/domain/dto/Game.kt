package com.example.myapplication.domain.dto

import com.example.myapplication.app.viewModel.GameViewModel
import com.example.myapplication.data.entity.NodeEntity.Companion.fromDto
import javax.inject.Inject

class GameInitializer @Inject constructor(
    private val viewModel: GameViewModel
) {
    private val nodes = mutableMapOf<String, Node>()

    fun initialize() {
        createNodes()
        saveToDatabase()
    }

    private fun createNodes() {
        val node1 = Node(1, "Добро пожаловать, хочешь помочь?", Edges())
        val node2 = Node(2, "Прекрасно", Edges())
        val node3 = Node(3, "Поможешь себе - изменишь весь мир!", Edges())
        val node4 = Node(4, "Точно! Кому-то помощь не нужна", Edges())
        val node5 = Node(5, "Ты обычный бариста. Как будешь помогать?", Edges())
        val node6 = Node(6, "Да это точно", Edges())
        val node7 = Node(7, "Да, конечно. Лишь бы голова к вечеру не гудела от историй", Edges())
        val node8 = Node(8, "Примеры из жизни? Это ценно", Edges())
        val node9 = Node(
            9,
            "О, а эту девочку ты знаешь. Эмили частый гость в твоем кафе. Но сегодня что-то не так, как обычно..",
            Edges()
        )

        nodes["node1"] = node1
        nodes["node2"] = node2
        nodes["node3"] = node3
        nodes["node4"] = node4
        nodes["node5"] = node5
        nodes["node6"] = node6
        nodes["node7"] = node7
        nodes["node8"] = node8
        nodes["node9"] = node9

        nodes["node3"]?.edges = Edges(listOf(Edge(1, "*без текста*", 5)))
        nodes["node4"]?.edges = Edges(listOf(Edge(1, "*без текста*", 5)))
        nodes["node1"]?.edges = Edges(
            listOf(
                Edge(1, "Конечно хочу!", 2),
                Edge(2, "И людям, и себе заодно!", 3),
                Edge(3, "Смотря кому..", 3)
            )
        )
        nodes["node2"]?.edges = Edges(listOf(Edge(1, "*без текста*", 5)))
        nodes["node5"]?.edges = Edges(
            listOf(
                Edge(1, "Хороший кофе уже помощь!", 6),
                Edge(2, "Бариста это не только про кофе, но еще и про поговорить!", 7),
                Edge(3, "Имею богатый жизненный опыт, буду им делиться", 8)
            )
        )
        nodes["node6"]?.edges = Edges(listOf(Edge(1, "*без текста*", 9)))
        nodes["node7"]?.edges = Edges(listOf(Edge(1, "*без текста*", 9)))
        nodes["node8"]?.edges = Edges(listOf(Edge(1, "*без текста*", 9)))
        nodes["node9"]?.edges = Edges(listOf(Edge(1, "Вернуться в начало", 1)))
    }

    private fun saveToDatabase() {
        val nodeEntities = nodes.values.map { node ->
            fromDto(node)
        }
        viewModel.insertNodes(nodeEntities)
    }
}