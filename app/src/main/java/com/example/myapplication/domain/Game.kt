package com.example.myapplication.domain


class Game {
    private lateinit var graph: Map<String, Node>

    private val nodes = mutableMapOf<String, Node>()

    init {
        createNodes()
        initializeGraph()
    }

    private fun createNodes() {
        // Сначала создаем узлы без инициализации edges
        val node1 = Node(1, "Добро пожаловать, хочешь помочь?", emptyList())
        val node2 = Node(2, "Прекрасно", emptyList())
        val node3 = Node(3, "Поможешь себе - изменишь весь мир!", emptyList())
        val node4 = Node(4, "Точно! Кому-то помощь не нужна", emptyList())
        val node5 = Node(5, "Ты обычный бариста. Как будешь помогать?", emptyList())
        val node6 = Node(6, "Да это точно", emptyList())
        val node7 =
            Node(7, "Да, конечно. Лишь бы голова к вечеру не гудела от историй", emptyList())
        val node8 = Node(8, "Примеры из жизни? Это ценно", emptyList())
        val node9 = Node(
            9,
            "О, а эту девочку ты знаешь. Эмили частый гость в твоем кафе. Но сегодня что-то не так, как обычно..",
            emptyList()
        )

        // Теперь добавляем узлы в временный список
        nodes["node1"] = node1
        nodes["node2"] = node2
        nodes["node3"] = node3
        nodes["node4"] = node4
        nodes["node5"] = node5
        nodes["node6"] = node6
        nodes["node7"] = node7
        nodes["node8"] = node8
        nodes["node9"] = node9

        // Теперь мы можем инициализировать edges, так как все узлы созданы
        nodes["node3"]?.edges = listOf(
            Edge(1, "*без текста*", node5)
        )

        nodes["node4"]?.edges = listOf(
            Edge(1, "*без текста*", node5)
        )

        nodes["node1"]?.edges = listOf(
            Edge(1, "Конечно хочу!", node2),
            Edge(2, "И людям, и себе заодно!", node3),
            Edge(3, "Смотря кому..", node3)
        )

        nodes["node2"]?.edges = listOf(
            Edge(1, "*без текста*", node5)
        )

        nodes["node5"]?.edges = listOf(
            Edge(1, "Хороший кофе уже помощь!", node6),
            Edge(2, "Бариста это не только про кофе, но еще и про поговорить!", node7),
            Edge(3, "Имею богатый жизненный опыт, буду им делиться", node8)
        )

        nodes["node6"]?.edges = listOf(
            Edge(1, "*без текста*", node9)
        )

        nodes["node7"]?.edges = listOf(
            Edge(1, "*без текста*", node9)
        )

        nodes["node8"]?.edges = listOf(
            Edge(1, "*без текста*", node9)
        )

        nodes["node9"]?.edges = listOf(
            Edge(1, "Вернуться в начало", node1)
        )
    }

    private fun initializeGraph() {
        graph = nodes.toMap() // Преобразуем MutableMap в Immutable Map
    }

    fun getCurrentNode(): Node {
        return graph["node1"]!! // Возвращаем начальный узел
    }

    fun makeChoice(choiceIndex: Int, currentNode: Node): Node {
        return if (choiceIndex in currentNode.edges.indices) {
            currentNode.edges[choiceIndex].nextNode
        } else {
            currentNode // Возвращаем текущий узел, если выбор не валидный
        }
    }
}