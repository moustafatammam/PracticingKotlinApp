package com.example.practicingkotlinapp.data.remote

import com.example.practicingkotlinapp.data.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/popular")
    fun getMovies(@Query("page") page: Int): Observable<MovieResponse>
}