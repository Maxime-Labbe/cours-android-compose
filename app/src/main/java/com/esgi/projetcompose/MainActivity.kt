package com.esgi.projetcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.esgi.projetcompose.ui.theme.Projet_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val TAG : String = "CAMPUS"
        // val capacity: Int = "12" -> Initializer type mismatch: expected 'Int', actual 'String'.
        // val capacity: Int = "douze".toInt() -> java.lang.NumberFormatException: For input string: "douze"
        val capacity: Int = "douze".toIntOrNull()?: 0

        Log.d(TAG, "Places : $capacity")
        setContent {
            Projet_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Projet_composeTheme {
        Greeting("Android")
    }
}