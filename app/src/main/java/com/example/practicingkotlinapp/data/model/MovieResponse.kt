package com.example.practicingkotlinapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("total_pages")
    val total: Int = 0,
    val page: Int = 0,
    @SerializedName("results")
    val movies: ArrayList<Movie>
    )

data class Movie(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val originalTitle: String
)
