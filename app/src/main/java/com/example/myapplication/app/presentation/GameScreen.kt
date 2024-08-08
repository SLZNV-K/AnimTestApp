package com.example.myapplication.app.presentation

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplication.R
import com.example.myapplication.app.presentation.components.CharacterDialog
import com.example.myapplication.app.presentation.theme.Pink40
import com.example.myapplication.app.presentation.theme.Purple40
import com.example.myapplication.app.presentation.util.splitBitmap
import com.example.myapplication.domain.dto.Node
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import kotlinx.coroutines.delay

@Composable
fun MainUiComposable(viewModel: GameViewModel) {
    val nodes by viewModel.allNodes.collectAsState(emptyList())
    val currentNode = viewModel.currentNode
    MainUiComposable(nodes = nodes, currentNode) { viewModel.updateCurrentNode(it) }
}

@Composable
private fun MainUiComposable(
    nodes: List<Node>,
    currentNode: Node?,
    updateCurrentNode: (Node) -> Unit
) {
    val backgroundColor = Purple40
    var currentNodeValue by remember { mutableStateOf(currentNode) }
    var visible by remember { mutableStateOf(true) }

    val brush = Brush.verticalGradient(
        colors = listOf(
            Pink40,
            Color.White,
        )
    )

    LaunchedEffect(nodes) {
        if (currentNode == null && nodes.isNotEmpty()) {
            currentNodeValue = nodes.firstOrNull { it.id == 1 }
            println("Current Node: $currentNodeValue")
        } else currentNodeValue = currentNode

        // Запуск анимации появления при изменении узла
        visible = false
        delay(1000)
        visible = true
    }

    Box(modifier = Modifier.fillMaxSize()) {
        PanoramaView(
            modifier = Modifier.fillMaxSize(),
        )
        Crossfade(
            targetState = currentNodeValue,
            label = "",
            animationSpec = tween(durationMillis = 2000)
        ) { nodeWithEdges ->
            if (nodeWithEdges != null) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(450.dp))
                    CharacterDialog(
                        node = nodeWithEdges,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .background(
                                backgroundColor,
                                shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                            )
//                                .animateContentSize() // Это анимирует изменения размеров блока кнопок
                    ) {
                        nodeWithEdges.edges.edges.forEach { edge ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                    .background(brush = brush, shape = RoundedCornerShape(8.dp))
                                    .clickable {
                                        val nextNode =
                                            nodes.firstOrNull { it.id == edge.nextNodeId }
                                        if (nextNode != null) {
                                            currentNodeValue = nextNode
                                            updateCurrentNode(nextNode)
                                        }
                                    }
                            ) {
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = edge.message,
                                    fontSize = 16.sp,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PanoramaView(
    modifier: Modifier,
) {
    val context = LocalContext.current
    val resources = context.resources
    val bitmap = BitmapFactory.decodeResource(resources, R.drawable.panorama)
    val imageParts = bitmap.splitBitmap(4)


    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(imageParts.size) { index ->
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    bitmap = imageParts[index].asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.485F)
                        .align(Alignment.Center)
                )
                when (index) {
                    0 -> VideoPlayer(
                        LocalContext.current, Modifier
                            .width((imageParts[index].width / 2.2).dp)
                            .align(Alignment.CenterEnd)
                    )
                }
            }
        }
    }
}

@Composable
private fun VideoPlayer(context: Context, modifier: Modifier) {
    val uri = "android.resource://${context.packageName}/${R.raw.anim}"

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = Player.REPEAT_MODE_ONE // Зацикливание видео
            playWhenReady = true // Автоматическое воспроизведение

            val mediaItem = MediaItem.fromUri(Uri.parse(uri))
            setMediaItem(mediaItem)
            prepare()
        }
    }

    AndroidView(
        factory = {
            PlayerView(it).apply {
                player = exoPlayer
                setBackgroundColor(Color.Transparent.toArgb())
                hideController()
                controllerAutoShow = false
                setControllerHideOnTouch(true)
                setControllerVisibilityListener { visibility ->
                    if (visibility == View.VISIBLE) {
                        hideController()
                    }
                }
            }
        },
        modifier = modifier
    )
}