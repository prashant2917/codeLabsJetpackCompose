package com.pocket.codelabsjetpackcompose

import AppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pocket.codelabsjetpackcompose.cupcakeapp.presentation.CupcakeApp
import com.pocket.codelabsjetpackcompose.marsphoto.presentation.MarsPhotosApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                  MarsPhotosApp()
                }
            }
        }
    }
}

