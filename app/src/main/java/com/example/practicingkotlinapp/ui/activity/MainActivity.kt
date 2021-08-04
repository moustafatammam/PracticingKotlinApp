package com.example.practicingkotlinapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        updateMovies(viewModel.page)

    }

    fun initRecyclerView(binding: ActivityMainBinding){
        binding.recycler.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            addOnScrollListener(object : PaginationScrollListener(layoutManager as GridLayoutManager) {

                override fun isLoading(): Boolean {
                    return viewModel.isLoading
                }

                override fun loadMoreItems() {
                    viewModel.isLoading = true
                    viewModel.page = viewModel.page + 1
                    updateMovies(viewModel.page)
                    post(Runnable { adapter?.notifyDataSetChanged() })
                }
            })
            moviesAdapter = MoviesAdapter()
            adapter = moviesAdapter
        }
    }

    fun updateMovies(page: Int){
        viewModel.isLoading = true
        viewModel.getMovieList().observe(this, Observer<MovieResponse>{
            if (it != null){
                if (it.total >= it.page && it.page == page) {
                    if (page > 1) {
                        moviesAdapter.addData(it.movies)
                    } else {
                        moviesAdapter.updateData(it.movies)
                        moviesAdapter.notifyDataSetChanged()
                    }
                }
                viewModel.isLoading = false

            }
        })
        viewModel.callGetMovies(page);
    }

    abstract class PaginationScrollListener

        (var layoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {

        var visibleItemCount = 0;
        var totalItemCount = 0;
        var pastVisibleItems = 0;

        abstract fun isLoading(): Boolean

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            visibleItemCount = layoutManager.childCount
            totalItemCount = layoutManager.itemCount

            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            pastVisibleItems = firstVisibleItemPosition

            val visibleThreshold = 5

            if (!isLoading()) {
                if ((visibleItemCount + pastVisibleItems) >= totalItemCount - visibleThreshold) {
                    loadMoreItems()
                }
            }
        }
        abstract fun loadMoreItems()
    }
}