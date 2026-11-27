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
        val event1 = Event(1, "Concert de jazz", "Un concert dans une ambiance chaleureuse.", "Musique", null, 10)
        val event2 = Event(2, "Match de foot", "Description disponible.", "Sport", "Stade municipal", 5)
        val event3 = Event(3, "Atelier cuisine", "", "Culture", "Salle polyvalente", 0)
        val event4 = Event(4, "Exposition photo", "Photographies contemporaines.", "Culture", null, 20)
        val event5 = Event(5, "Randonnée", "Une sortie en pleine nature.", "Sport", "", 1)
        val event6 = Event(6, "Conférence IA", "Conférence suivie d’un débat.", "Éducation", "Auditorium", 100)
        val event7 = Event(7, "Marathon", "Course ouverte à tous.", "Sport", "Centre-ville", -1)
        val event8 = Event(8, "Événement familial", "Activités pour petits et grands.", "Famille", null, 0)
        val event9 = Event(9, "Titre très long permettant de tester l’affichage sur plusieurs lignes dans l’interface", "Description également très longue pour vérifier les retours à la ligne.", "Cinéma", "Complexe culturel", 50)
        val event10 = Event(10, "🎵 Festival musical 🎵", "Événement avec emojis.", "Musique", "Parc des Expositions", 2)

        val events: List<Event> = listOf(event1, event2, event3, event4, event5, event6, event7, event8, event9, event10)
        setContent {
            EventListScreen(events)
        // EventDetailScreen(event1, { }, Modifier)
        }
    }
}



