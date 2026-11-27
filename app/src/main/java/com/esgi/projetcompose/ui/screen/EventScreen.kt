package com.esgi.projetcompose.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndSelectAll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.esgi.projetcompose.R
import com.esgi.projetcompose.data.Event

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(events: List<Event>) {
    val queryState = rememberTextFieldState()
    var selectedCategories by rememberSaveable { mutableStateOf(listOf<String>()) }
    var favoriteEventsId by rememberSaveable { mutableStateOf(listOf<Int>()) }
    val visibleEvents = events.filter {
        it.title.contains(queryState.text, ignoreCase = true)
                && (selectedCategories.isEmpty() || selectedCategories.contains(it.category))  }
    val favoriteEvents = visibleEvents.filter { favoriteEventsId.contains(it.id) }
    Scaffold(
        topBar = {
            TopAppBar(title= { Text("Campus")})
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            SearchField(queryState, Modifier)
            Text(
                text = pluralStringResource(
                    R.plurals.events_search_results,
                    visibleEvents.size,
                    visibleEvents.size
                ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            LazyColumn {
                item {
                    CategoryRow(
                        categories = events.map { it.category }.distinct(),
                        selectedCategories,
                        onCategorySelect = { selectedCategories = if (selectedCategories.contains(it)) selectedCategories - it else selectedCategories + it },
                        modifier = Modifier
                    )
                }
                if (!favoriteEvents.isEmpty()) {
                    item {
                        Text(
                            stringResource(R.string.favorites),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }

                    items(visibleEvents.filter { favoriteEventsId.contains(it.id) }) {
                        EventCard(
                            it,
                            favoriteEventsId.contains(it.id),
                            onClick = { Log.d("CAMPUS", "Click sur voir") },
                            onBook = { Log.d("CAMPUS", "Click sur réserver") },
                            onFavoriteClick = {
                                favoriteEventsId =
                                    if (favoriteEventsId.contains(it.id)) favoriteEventsId - it.id else favoriteEventsId + it.id
                            },
                            Modifier.padding(8.dp)
                        )
                    }
                    item {
                        Text(
                            stringResource(R.string.other_events),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
                items(visibleEvents.filter { !favoriteEventsId.contains(it.id) }) {
                    EventCard(
                        it,
                        favoriteEventsId.contains(it.id),
                        onClick = { Log.d("CAMPUS", "Click sur voir") },
                        onBook = { Log.d("CAMPUS", "Click sur réserver") },
                        onFavoriteClick = {
                            favoriteEventsId = if (favoriteEventsId.contains(it.id)) favoriteEventsId - it.id else favoriteEventsId + it.id
                        },
                        Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SearchField(state: TextFieldState, modifier: Modifier) {
    TextField(
        state,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = { Text(stringResource(R.string.search_event)) },
        trailingIcon = {
            TextButton(
                onClick = { state.setTextAndSelectAll("")  }
            ) {
                Text("X")
            }
        },
    )
}

@Composable
fun CategoryRow(categories: List<String>, selectedCategories: List<String>, onCategorySelect: (String) -> Unit, modifier: Modifier) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) {
            Button(
                onClick = { onCategorySelect(it) },
                colors = if (selectedCategories.contains(it) || selectedCategories.isEmpty()) ButtonDefaults.buttonColors() else ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(it)
            }
        }
    }
}

@Composable
fun EventCard(
    event: Event,
    isFavorite: Boolean = false,
    onClick : () -> Unit,
    onBook : () -> Unit,
    onFavoriteClick : () -> Unit,
    modifier: Modifier
) {
    Card(onClick = onClick, modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(event.title)
                    IconButton(
                        onClick = onFavoriteClick,
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp)
                    ) {
                        Text(if (isFavorite) "★" else "☆")
                    }
                }
                Text(text = event.category)
            }
            Text(event.location ?: stringResource(id = R.string.unconfirmed_place))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onBook,
                    enabled = event.capacity > 0
                ) {
                    Text(stringResource(R.string.book))
                }
                TextButton(onClick = onClick) {
                    Text(stringResource(R.string.see_details))
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

    EventCard(event = event, isFavorite = true, onClick = {  }, onBook = { }, onFavoriteClick = { }, modifier = Modifier)
}