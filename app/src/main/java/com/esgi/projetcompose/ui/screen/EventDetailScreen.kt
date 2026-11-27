package com.esgi.projetcompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.esgi.projetcompose.R
import com.esgi.projetcompose.data.Event

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(
    event: Event,
    onBook: () -> Unit,
    modifier: Modifier
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(event.title)
        })
    }) { innerPadding ->
        EventDetail(
            event = event,
            onBook = onBook,
            modifier = modifier.padding(innerPadding)
        )
    }

}

@Composable
fun EventDetail(
    event: Event,
    onBook: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.event_banner),
            contentDescription = stringResource(R.string.event_image_details),
            modifier = Modifier.fillMaxWidth()
        )
        Text(event.title)
        Text(event.location ?: stringResource(R.string.unconfirmed_place))
        if (event.description != "") {
            Text(event.description)
        }
        Text(pluralStringResource(
            R.plurals.number_of_places,
            event.capacity,
            event.capacity
        ))
        Button(
            onClick = onBook,
            enabled = event.capacity > 0
        ) {
            Text(stringResource(R.string.book))
        }
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@PreviewFontScale
@PreviewScreenSizes
@Composable
fun EventDetailPreview() {
    EventDetail(
        event = Event(
            id = 1,
            title = "Titre de l'événement",
            description = "Description de l'événement",
            category = "Catégorie",
            location = null,
            capacity = 5
        ),
        onBook = {},
        Modifier
    )
}