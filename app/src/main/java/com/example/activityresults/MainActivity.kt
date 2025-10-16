package com.example.activityresults

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.activityresults.ui.theme.ActivityResultsTheme

class MainActivity : ComponentActivity() {


    companion object {
        const val RAINBOW_COLOR_NAME = "RAINBOW_COLOR_NAME"
        const val RAINBOW_COLOR = "RAINBOW_COLOR"
        const val TRANSPARENT = 0x00FFFFFF
    }

    private var rainbowColor by mutableStateOf(Color(TRANSPARENT))
    private var colorName by mutableStateOf("")
    private var colorMessage by mutableStateOf("")

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        val data = activityResult.data
        val colorValue = data?.getLongExtra(RAINBOW_COLOR, TRANSPARENT.toLong()) ?: TRANSPARENT.toLong()
        val nameValue = data?.getStringExtra(RAINBOW_COLOR_NAME) ?: ""

        rainbowColor = Color(colorValue)
        colorName = nameValue
        colorMessage = "$nameValue is your favorite color!"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivityResultsTheme {
                val context = LocalContext.current
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainScreen(
                        backgroundColor = rainbowColor,
                        colorMessage = colorMessage,
                        context = context,
                        startForResult = startForResult,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    backgroundColor: Color,
    colorMessage: String,
    context: android.content.Context,
    startForResult: androidx.activity.result.ActivityResultLauncher<Intent>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "¡Haga clic en el botón a continuación para elegir su color favorito del arcoíris!",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Button(
            onClick = {
                val intent = Intent(context, ColorPickerActivity::class.java)
                startForResult.launch(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "ELIJE UN COLOR",
                fontSize = 18.sp
            )
        }

        TextWithBackgroundColor(
            backgroundColor = backgroundColor,
            colorMessage = colorMessage,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}

@Composable
fun TextWithBackgroundColor(
    backgroundColor: Color,
    colorMessage: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 20.dp)
            .height(50.dp)
            .background(backgroundColor)
            .fillMaxWidth()
    ) {
        Text(
            text = colorMessage,
            fontSize = 22.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextWithBackgroundColorPreview() {
    ActivityResultsTheme {
        TextWithBackgroundColor(
            backgroundColor = Color(0xFFFFA500),
            colorMessage = "ORANGE is your favorite color!"
        )
    }
}