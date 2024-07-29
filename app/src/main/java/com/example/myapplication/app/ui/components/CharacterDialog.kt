package com.example.myapplication.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.dto.CharacterType
import com.example.myapplication.domain.dto.Edges
import com.example.myapplication.domain.dto.Node

@Composable
fun CharacterDialog(
    node: Node,
    modifier: Modifier
) {
    val isSpeakingLeft = when (node.characterType) {
        CharacterType.PLAYER -> true
        CharacterType.EMILY -> false
        CharacterType.VOICE_OVER -> true
    }
    val backgroundColor = Color.White
    val textColor = Color.Black
    val nameBackgroundColor = Color.Cyan

    if (node.characterType == CharacterType.VOICE_OVER) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    nameBackgroundColor,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .background(backgroundColor, shape = RoundedCornerShape(8.dp))
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = node.message,
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    } else {
        Column(
            modifier = modifier
                .padding(
                    start = if (isSpeakingLeft) 16.dp else 0.dp,
                    end = if (isSpeakingLeft) 0.dp else 16.dp
                )
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = if (node.characterType == CharacterType.PLAYER) Alignment.End else Alignment.Start)
                    .background(
                        nameBackgroundColor,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
            ) {
                Text(
                    text = node.characterType.toString(),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 4.dp),
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        nameBackgroundColor,
                        shape = if (node.characterType == CharacterType.PLAYER) RoundedCornerShape(
                            topStart = 8.dp,
                            bottomStart = 8.dp,
                            bottomEnd = 8.dp
                        ) else RoundedCornerShape(
                            topEnd = 8.dp,
                            bottomStart = 8.dp,
                            bottomEnd = 8.dp
                        )
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = node.message,
                        color = textColor,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
fun TriangleShape() {
    Canvas(modifier = Modifier.size(40.dp)) {
        val trianglePath = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, size.height)
            lineTo(size.width / 2, size.height)
            close()
        }
        drawPath(
            path = trianglePath,
            color = Color.White
        )
    }
}


@Preview
@Composable
fun CharacterDialogEmilyPreview() {
    CharacterDialog(Node(0, "Привет!", Edges(), characterType = CharacterType.EMILY), Modifier)
}

@Preview
@Composable
fun CharacterDialogPlayerPreview() {
    CharacterDialog(Node(0, "Привет!", Edges(), characterType = CharacterType.PLAYER), Modifier)
}

@Preview
@Composable
fun CharacterDialogVoicePreview() {
    CharacterDialog(Node(0, "Привет!", Edges(), characterType = CharacterType.VOICE_OVER), Modifier)
}

@Preview
@Composable
fun TrianglePreview() {
    TriangleShape()
}