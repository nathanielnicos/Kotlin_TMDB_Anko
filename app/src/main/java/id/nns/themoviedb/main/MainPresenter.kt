package id.nns.themoviedb.main

import com.google.gson.Gson
import id.nns.themoviedb.model.MovieResponse
import id.nns.themoviedb.network.ApiRepository
import id.nns.themoviedb.network.TMDBApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView, private val apiRepository: ApiRepository, private val gson: Gson) {
    fun getMovieList() {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TMDBApi.getMovie()), MovieResponse::class.java)
            uiThread {
                view.showMovieList(data.results)
            }
        }
    }
}