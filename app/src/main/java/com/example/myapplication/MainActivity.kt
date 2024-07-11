package com.example.myapplication

//import coil.ImageLoader
//import coil.compose.rememberAsyncImagePainter
//import coil.decode.GifDecoder
//import coil.decode.ImageDecoderDecoder
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting() {
    Box(modifier = Modifier.fillMaxSize()) {
        PanoramaView(modifier = Modifier.fillMaxSize())
        ButtonsBlock(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(12.dp)
        )
    }
}

@Composable
fun Gif(modifier: Modifier, id: Int) {
//    val imageLoader = ImageLoader.Builder(LocalContext.current)
//        .components {
//            if (SDK_INT >= 28) {
//                add(ImageDecoderDecoder.Factory())
//            } else {
//                add(GifDecoder.Factory())
//            }
//        }
//        .build()
//    Image(
//        painter = rememberAsyncImagePainter(id, imageLoader),
//        contentDescription = null,
//        modifier = modifier
//    )
}

@Composable
fun PanoramaView(modifier: Modifier) {
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
//                    1 -> {
//                        Gif(
//                            modifier = Modifier
//                                .align(Alignment.Center)
//                                .size(400.dp),
//                            id = R.drawable.emily_no_background
//                        )
//                    }

                    1 -> LottieAnimation(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )

                    0 -> ComposableRiveAnimationView(
                        animation = R.raw.login,
                        modifier = Modifier
                            .size(400.dp)
                    ) { view ->
                        view.setBooleanState(
                            "Login Machine",
                            "isHandsUp",
                            true
                        )
                        view.setBooleanState(
                            "Login Machine",
                            "isChecking",
                            false
                        )
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
    LottieAnimation(
        composition,
        progress,
        modifier = modifier.size(400.dp)
    )
}

@Composable
fun ButtonsBlock(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(horizontal = 12.dp)) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Option 1")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Option 2")
            }
        }
        Button(
            onClick = { /*TODO*/ },
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
    Greeting()
}

@Preview
@Composable
fun ButtonsBlockPreview() {
    ButtonsBlock(Modifier)
}