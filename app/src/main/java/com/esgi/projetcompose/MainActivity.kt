package com.esgi.projetcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
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
        setContent {
            EventHeader("Cours","Ecole",null)
        }
    }
}

@Composable
fun EventHeader(title: String, category: String, location: String?) {
    Column {
        Text(title)
        Text(category)
        Text(location ?: "Lieu à confirmer")
    }
}

@Preview(showBackground = true)
@Composable
fun EventHeaderPreview() {
    EventHeader("test","test", "null")
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