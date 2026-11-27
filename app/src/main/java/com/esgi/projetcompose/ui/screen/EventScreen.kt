package com.esgi.projetcompose.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.esgi.projetcompose.data.Event

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(events: List<Event>) {
    Scaffold(
        topBar = {
            TopAppBar(title= { Text("Campus")})
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(events)  {
                EventCard(
                    it,
                    onClick = { Log.d("CAMPUS", "Click sur voir") },
                    onBook = { Log.d("CAMPUS", "Click sur réserver") },
                    Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun EventCard(
    event: Event,
    onClick : () -> Unit,
    onBook : () -> Unit,
    modifier: Modifier
) {
    Card(onClick = onClick, modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(event.title)
                Text(text = event.category)
            }
            Text(event.location ?: "Lieu à confirmer")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onBook,
                    enabled = event.capacity > 0
                ) {
                    Text("Réserver")
                }
                TextButton(onClick = onClick) {
                    Text("Voir")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@PreviewFontScale
@PreviewScreenSizes
@Composable
private fun EventCardPreview(){
    val event: Event = Event(1, "Conférence Kotlin", "", "TECH", "Salle 204", 10)

    EventCard(event = event, onClick = {  }, onBook = { }, modifier = Modifier)
}