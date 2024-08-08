package com.example.myapplication.app.presentation.components

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.app.presentation.theme.Pink80
import com.example.myapplication.app.presentation.theme.Purple40
import com.example.myapplication.domain.dto.CharacterType
import com.example.myapplication.domain.dto.Edges
import com.example.myapplication.domain.dto.Node

@Composable
fun CharacterDialog(
    node: Node,
    modifier: Modifier,
) {
    val backgroundColor = Color.White
    val textColor = Color.Black
    val nameBackgroundColor = Purple40

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
                    .padding(top = 4.dp)
                    .padding(horizontal = 4.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Pink80,
                                backgroundColor,
                            )
                        ), shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = node.message,
                    fontSize = 20.sp,
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    } else {
        Box {
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = if (node.characterType == CharacterType.PLAYER) Alignment.End else Alignment.Start)
                        .background(
                            nameBackgroundColor,
                            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                        )
                        .padding(8.dp)
                ) {
                    Text(
                        text = node.characterType.toString(),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(top = 4.dp),
                        fontSize = 16.sp,
                        color = Color.White,
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
                            .padding(top = 4.dp)
                            .padding(horizontal = 4.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        backgroundColor,
                                        Pink80
                                    )
                                ), shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = node.message,
                            fontSize = 20.sp,
                            color = textColor,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            val mirrorHorizontal = node.characterType != CharacterType.EMILY
            TriangleShapeWithBorder(
                modifier = Modifier.align(Alignment.TopEnd),
                mirrorHorizontal = mirrorHorizontal
            )
        }
    }
}

@Composable
fun TriangleShapeWithBorder(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    colorBorder: Color = Purple40,
    triangleSize: Dp = 48.9.dp,
    mirrorHorizontal: Boolean = false,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (!mirrorHorizontal) {
                    Modifier.padding(end = triangleSize)
                } else {
                    Modifier.padding(start = triangleSize)
                }
            )
    ) {
        Canvas(
            modifier = Modifier
                .size(triangleSize)
                .then(
                    if (!mirrorHorizontal) {
                        Modifier.align(Alignment.BottomEnd)
                    } else {
                        Modifier.align(Alignment.BottomStart)
                    }
                )
        ) {
            val trianglePath = Path().apply {
                if (!mirrorHorizontal) {
                    moveTo(0f, 0f)
                    lineTo(size.width, size.height)
                    lineTo(size.width / 2, size.height)
                } else {
                    moveTo(size.width, 0f)
                    lineTo(0f, size.height)
                    lineTo(size.width / 2, size.height)
                }
                close()
            }

            drawPath(
                path = trianglePath,
                color = backgroundColor
            )

            if (!mirrorHorizontal) {
                drawLine(
                    start = Offset(0f, 0f),
                    end = Offset(size.width, size.height),
                    color = colorBorder,
                    strokeWidth = 4.dp.toPx(),
                )
                drawLine(
                    start = Offset(0f, 0f),
                    end = Offset(size.width / 2, size.height),
                    color = colorBorder,
                    strokeWidth = 4.dp.toPx(),
                )
            } else {
                drawLine(
                    start = Offset(size.width, 0f),
                    end = Offset(0f, size.height),
                    color = colorBorder,
                    strokeWidth = 4.dp.toPx(),
                )
                drawLine(
                    start = Offset(size.width, 0f),
                    end = Offset(size.width / 2, size.height),
                    color = colorBorder,
                    strokeWidth = 4.dp.toPx(),
                )
            }
        }
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
fun TriangleShapeWithBorderPreview() {
    TriangleShapeWithBorder()
}
