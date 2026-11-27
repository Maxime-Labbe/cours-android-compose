package com.esgi.projetcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.esgi.projetcompose.data.Event
import com.esgi.projetcompose.ui.screen.EventDetailScreen
import com.esgi.projetcompose.ui.screen.EventListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val event1: Event = Event(1, "titre", "", "catégorie", null, 10)
        val event2: Event = Event(2, "titre", "","catégorie", "lieu", 10)
        val event3: Event = Event(3, "titre", "","catégorie", "lieu", 0)
        val event4: Event = Event(4, "titre", "","catégorie", "lieu", 10)
        val event5: Event = Event(5, "titre", "","catégorie", "lieu", 10)
        val events: List<Event> = listOf(event1, event2, event3, event4, event5);
        setContent {
            EventListScreen(events)
        // EventDetailScreen(event1, { }, Modifier)
        }
    }
}



