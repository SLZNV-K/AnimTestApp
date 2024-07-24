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
        val node1 = Node(1, "Добро пожаловать, хочешь помочь?")
        val node2 = Node(2, "Прекрасно")
        val node3 = Node(3, "Поможешь себе - изменишь весь мир!")
        val node4 = Node(4, "Точно! Кому-то помощь не нужна")
        val node5 = Node(5, "Ты обычный бариста. Как будешь помогать?")
        val node6 = Node(6, "Да это точно")
        val node7 = Node(7, "Да, конечно. Лишь бы голова к вечеру не гудела от историй")
        val node8 = Node(8, "Примеры из жизни? Это ценно")
        val node9 = Node(
            9,
            "О, а эту девочку ты знаешь. Эмили частый гость в твоем кафе. Но сегодня что-то не так, как обычно.."
        )

        val node10 = Node(10, "О, Player1, доброе утро!", characterType = CharacterType.EMILY)
        val node11 = Node(11, "Что-то ты сегодня рано, Эмили", characterType = CharacterType.PLAYER)
        val node12 = Node(
            12,
            "Сегодня у меня собеседование. Хочу на работу устроиться.",
            characterType = CharacterType.EMILY
        )
        val node13 = Node(
            13,
            "Три месяца искала! Резюме, пробные задачи, все такое. Волнуюсь.",
            characterType = CharacterType.EMILY
        )
        val node14 = Node(14, "О! Дело серьезное", characterType = CharacterType.PLAYER)
        // остановилась тут
        val node15 = Node(15, "Да это точно")
        val node16 = Node(16, "Да, конечно. Лишь бы голова к вечеру не гудела от историй")
        val node17 = Node(17, "Примеры из жизни? Это ценно")
        val node18 = Node(
            18,
            "О, а эту девочку ты знаешь. Эмили частый гость в твоем кафе. Но сегодня что-то не так, как обычно.."
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

        nodes["node10"] = node10
        nodes["node11"] = node11
        nodes["node12"] = node12
        nodes["node13"] = node13
        nodes["node14"] = node14
        nodes["node15"] = node15
        nodes["node16"] = node16
        nodes["node17"] = node17
        nodes["node18"] = node18

        addEdgesToNode()
    }

    private fun addEdgesToNode() {
        nodes["node3"]?.edges = Edges(listOf(Edge(1, "Дальше", 5)))
        nodes["node4"]?.edges = Edges(listOf(Edge(1, "Дальше", 5)))
        nodes["node1"]?.edges = Edges(
            listOf(
                Edge(1, "Конечно хочу!", 2),
                Edge(2, "И людям, и себе заодно!", 3),
                Edge(3, "Смотря кому..", 3)
            )
        )
        nodes["node2"]?.edges = Edges(listOf(Edge(1, "Дальше", 5)))
        nodes["node5"]?.edges = Edges(
            listOf(
                Edge(1, "Хороший кофе уже помощь!", 6),
                Edge(2, "Бариста это не только про кофе, но еще и про поговорить!", 7),
                Edge(3, "Имею богатый жизненный опыт, буду им делиться", 8)
            )
        )
        nodes["node6"]?.edges = Edges(listOf(Edge(1, "Дальше", 9)))
        nodes["node7"]?.edges = Edges(listOf(Edge(1, "Дальше", 9)))
        nodes["node8"]?.edges = Edges(listOf(Edge(1, "Дальше", 9)))
        nodes["node9"]?.edges = Edges(listOf(Edge(1, "*появление Эмили*", 10)))


        nodes["node10"]?.edges = Edges(listOf(Edge(1, "Дальше", 11)))
        nodes["node11"]?.edges = Edges(listOf(Edge(1, "Дальше", 12)))
        nodes["node12"]?.edges = Edges(listOf(Edge(1, "Дальше", 13)))
        nodes["node13"]?.edges = Edges(listOf(Edge(1, "Дальше", 14)))
    }

    private fun saveToDatabase() {
        val nodeEntities = nodes.values.map { node ->
            fromDto(node)
        }
        viewModel.insertNodes(nodeEntities)
    }
}