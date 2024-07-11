package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun MainUI() {
    var trigSuccess by remember { mutableStateOf(false) }
    var trigFail by remember { mutableStateOf(false) }
    var isChecking by remember { mutableStateOf(false) }
    var isHandsUp by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        PanoramaView(
            modifier = Modifier.fillMaxSize(),
            trigSuccess = trigSuccess,
            trigFail = trigFail,
            isChecking = isChecking,
            isHandsUp = isHandsUp
        )
        ButtonsBlock(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(12.dp),
            onOption1Click = {
                isChecking = true
            },
            onOption2Click = { isHandsUp = true },
            onOption3Click = {
                isHandsUp = false
                isChecking = false
            }
        )
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
    val imageParts = splitBitmap(bitmap, 4)


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

                    1 -> LottieAnimation(
                        modifier = Modifier
                            .width((imageParts[index].width / 3).dp)
                            .align(Alignment.Center)
                    )

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

@Composable
fun LottieAnimation(modifier: Modifier) {
    var isPlaying by remember {
        mutableStateOf(true)
    }

    var speed by remember {
        mutableStateOf(1f)
    }
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.anim_girl_lotti)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = false
    )
    com.airbnb.lottie.compose.LottieAnimation(
        composition,
        progress,
        modifier = modifier
    )
}

@Composable
fun ButtonsBlock(
    modifier: Modifier,
    onOption1Click: () -> Unit,
    onOption2Click: () -> Unit,
    onOption3Click: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(horizontal = 12.dp)) {
            Button(onClick = { onOption1Click() }) {
                Text(text = "Option 1")
            }
            Button(onClick = { onOption2Click() }) {
                Text(text = "Option 2")
            }
        }
        Button(
            onClick = { onOption3Click() },
        ) {
            Text(text = "Option 3")
        }
    }
}

fun splitBitmap(bitmap: Bitmap, chunkNumbers: Int): List<Bitmap> {
    val chunkWidth = bitmap.width / chunkNumbers
    val chunkHeight = bitmap.height
    val chunks = mutableListOf<Bitmap>()

    for (i in 0 until chunkNumbers) {
        val x = i * chunkWidth
        chunks.add(Bitmap.createBitmap(bitmap, x, 0, chunkWidth, chunkHeight))
    }
    return chunks
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainUI()
}

@Preview
@Composable
fun ButtonsBlockPreview() {
    ButtonsBlock(Modifier, {}, {}, {})
}