package id.nns.themoviedb.network

import id.nns.themoviedb.BuildConfig.API_KEY
import id.nns.themoviedb.BuildConfig.BASE_URL

object TMDBApi {
    fun getMovie(): String {
        return BASE_URL + API_KEY
    }
}