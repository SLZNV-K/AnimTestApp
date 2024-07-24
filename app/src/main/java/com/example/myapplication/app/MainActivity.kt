package com.example.myapplication.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.myapplication.app.ui.MainUI
import com.example.myapplication.app.viewModel.GameViewModel
import com.example.myapplication.domain.dto.GameInitializer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GameInitializer(viewModel).initialize()
        setContent {
            MainUI(viewModel)
        }
    }
}
