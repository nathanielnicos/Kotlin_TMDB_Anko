package id.nns.themoviedb.main

import id.nns.themoviedb.model.Movie

interface MainView {
    fun showMovieList(data: List<Movie>)
}