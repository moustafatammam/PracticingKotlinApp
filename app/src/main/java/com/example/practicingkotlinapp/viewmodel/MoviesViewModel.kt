package com.example.practicingkotlinapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicingkotlinapp.data.model.MovieResponse
import com.example.practicingkotlinapp.data.remote.ApiClient
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel: ViewModel() {

    var moviesLiveData: MutableLiveData<MovieResponse> = MutableLiveData()

    fun getMovieList(): MutableLiveData<MovieResponse>{
        return moviesLiveData
    }

    fun callGetMovies(){
        val apiClient = ApiClient.buildMoviesService()
        apiClient.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getMovieListObserver())
    }

    private fun getMovieListObserver(): Observer<MovieResponse> {
        return object : Observer<MovieResponse>{
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: MovieResponse) {
                moviesLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                moviesLiveData.postValue(null)
            }

            override fun onComplete() {
            }

        }
    }

}