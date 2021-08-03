package com.example.practicingkotlinapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicingkotlinapp.R
import com.example.practicingkotlinapp.data.model.MovieResponse
import com.example.practicingkotlinapp.databinding.ActivityMainBinding
import com.example.practicingkotlinapp.ui.adapter.MoviesAdapter
import com.example.practicingkotlinapp.viewmodel.MoviesViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:  MoviesViewModel
    lateinit var moviesAdapter: MoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        binding.viewmodel = viewModel
        binding.executePendingBindings()

        initRecyclerView(binding)
        updateMovies()

    }

    fun initRecyclerView(binding: ActivityMainBinding){
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            moviesAdapter = MoviesAdapter()
            adapter = moviesAdapter
        }
    }

    fun updateMovies(){
        viewModel.getMovieList().observe(this, Observer<MovieResponse>{
            if (it.movies != null){
                moviesAdapter.movieList = it.movies
                moviesAdapter.notifyDataSetChanged()
            }
        })
        viewModel.callGetMovies()
    }
}