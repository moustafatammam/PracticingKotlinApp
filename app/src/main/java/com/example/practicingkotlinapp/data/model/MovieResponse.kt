package com.example.practicingkotlinapp.data.model

data class MovieResponse (val movies: ArrayList<Movie>)

data class Movie(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val originalTitle: String
)
