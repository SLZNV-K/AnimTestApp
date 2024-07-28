package com.example.myapplication.domain.dto

data class Node(
    val id: Int,
    val message: String,
    var edges: Edges = Edges(),
    val foreground: Int = 0,
    val anim: Int = 0,
    val characterType: CharacterType = CharacterType.VOICE_OVER
)

enum class CharacterType(private val displayName: String) {
    PLAYER("Player"),
    EMILY("Emily"),
    VOICE_OVER("Voice over");

    override fun toString(): String {
        return displayName
    }
}