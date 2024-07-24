package com.example.myapplication.app.ui

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.app.ui.util.splitBitmap
import com.example.myapplication.app.viewModel.GameViewModel
import com.example.myapplication.domain.dto.Node

@Composable
fun MainUI(viewModel: GameViewModel) {
    val nodes by viewModel.allNodes.observeAsState(emptyList())

    var currentNode by remember { mutableStateOf<Node?>(null) }

    LaunchedEffect(nodes) {
        if (currentNode == null && nodes.isNotEmpty()) {
            currentNode = nodes.firstOrNull { it.id == 1 }
            println("Current Node: $currentNode")
        }
    }

    val trigSuccess by remember { mutableStateOf(false) }
    val trigFail by remember { mutableStateOf(false) }
    val isChecking by remember { mutableStateOf(false) }
    var isHandsUp by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        PanoramaView(
            modifier = Modifier.fillMaxSize(),
            trigSuccess = trigSuccess,
            trigFail = trigFail,
            isChecking = isChecking,
            isHandsUp = isHandsUp
        )

        currentNode?.let { nodeWithEdges ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = nodeWithEdges.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )

                nodeWithEdges.edges.edges.forEach { edge ->
                    Button(
                        onClick = {
                            val nextNode =
                                nodes.firstOrNull { it.id == edge.nextNodeId }
                            if (nextNode != null) {
                                currentNode = nextNode
                                isHandsUp = !isHandsUp
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = edge.message,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PanoramaView(
    modifier: Modifier,
    trigSuccess: Boolean,
    trigFail: Boolean,
    isChecking: Boolean,
    isHandsUp: Boolean
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
                    contentDescription = "Часть панорамы",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.485F)
                        .align(Alignment.Center)
                )
                when (index) {

                    0 -> ComposableRiveAnimationView(
                        animation = R.raw.login,
                        modifier = Modifier
                            .width((imageParts[index].width / 3).dp)
                            .align(Alignment.CenterEnd)
                    ) { view ->
                        view.setBooleanState(
                            "Login Machine",
                            "isHandsUp",
                            isHandsUp
                        )
                        view.setBooleanState(
                            "Login Machine",
                            "isChecking",
                            isChecking
                        )
                        if (trigFail)
                            view.fireState("Login Machine", "trigFail")
                        if (trigSuccess)
                            view.fireState("Login Machine", "trigSuccess")
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MainUI()
//}