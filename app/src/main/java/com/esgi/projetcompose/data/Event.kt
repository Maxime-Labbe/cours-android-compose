package com.esgi.projetcompose.data

data class Event(
    val id: Int,
    val title: String,
    val description: String = "",
    val category: String,
    val location: String?,
    val capacity: Int
) {
}