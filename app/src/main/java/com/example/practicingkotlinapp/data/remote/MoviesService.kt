package com.example.practicingkotlinapp.data.remote

import com.example.practicingkotlinapp.data.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MoviesService {

    @GET("movie/popular")
    fun getMovies(): Observable<MovieResponse>
}