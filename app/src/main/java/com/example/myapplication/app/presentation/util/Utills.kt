package com.example.myapplication.app.presentation.util

import android.graphics.Bitmap

fun Bitmap.splitBitmap(chunkNumbers: Int): List<Bitmap> {
    val chunkWidth = this.width / chunkNumbers
    val chunkHeight = this.height
    val chunks = mutableListOf<Bitmap>()

    for (i in 0 until chunkNumbers) {
        val x = i * chunkWidth
        chunks.add(Bitmap.createBitmap(this, x, 0, chunkWidth, chunkHeight))
    }
    return chunks
}