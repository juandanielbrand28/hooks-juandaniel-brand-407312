package com.example.activityresults

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.activityresults.ui.theme.ActivityResultsTheme

class ColorPickerActivity : ComponentActivity() {

    // Constantes para los colores del arcoíris
    companion object {
        const val RED = 0xFFFF0000L
        const val ORANGE = 0xFFFFA500L
        const val YELLOW = 0xFFFFFF00L
        const val GREEN = 0xFF00FF00L
        const val BLUE = 0xFF0000FFL
        const val INDIGO = 0xFF4B0082L
        const val VIOLET = 0xFF8A2BE2L

        // Constantes para las keys (deben coincidir con MainActivity)
        const val RAINBOW_COLOR_NAME = "RAINBOW_COLOR_NAME"
        const val RAINBOW_COLOR = "RAINBOW_COLOR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivityResultsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ColorPickerScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun setRainbowColor(color: Long, colorName: String) {
        val resultIntent = Intent().apply {
            putExtra(RAINBOW_COLOR_NAME, colorName)
            putExtra(RAINBOW_COLOR, color)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    @Composable
    fun ColorPickerScreen(modifier: Modifier = Modifier) {
        val clickHandler = { color: Long, colorName: String ->
            setRainbowColor(color, colorName)
        }

        Column(
            verticalArrangement = Arrangement.Top,
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Rainbow Colors", // Texto directo en lugar de stringResource
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )

            // Botones de colores con texto directo
            RainbowColor(RED, "RED", clickHandler)
            RainbowColor(ORANGE, "ORANGE", clickHandler)
            RainbowColor(YELLOW, "YELLOW", clickHandler)
            RainbowColor(GREEN, "GREEN", clickHandler)
            RainbowColor(BLUE, "BLUE", clickHandler)
            RainbowColor(INDIGO, "INDIGO", clickHandler)
            RainbowColor(VIOLET, "VIOLET", clickHandler)

            Text(
                text = "Click the button above which is your favorite color of the rainbow.",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            )
        }
    }

    @Composable
    fun RainbowColor(
        color: Long,
        colorName: String,
        onButtonClick: (Long, String) -> Unit
    ) {
        Button(
            onClick = { onButtonClick(color, colorName) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(color)
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = colorName,
                color = Color.White,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ColorPickerScreenPreview() {
        ActivityResultsTheme {
            ColorPickerScreen()
        }
    }
}