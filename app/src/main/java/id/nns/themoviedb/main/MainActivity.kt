package id.nns.themoviedb.main

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import id.nns.themoviedb.detail.DetailActivity
import id.nns.themoviedb.model.Movie
import id.nns.themoviedb.network.ApiRepository
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var listMovie: RecyclerView
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private var movies: MutableList<Movie> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = 0xFF1F1F1F.toInt()

            listMovie = recyclerView {
                lparams(width = matchParent, height = wrapContent)
                layoutManager = GridLayoutManager(this@MainActivity, 2)
            }
        }

        adapter = MainAdapter(movies) {
            startActivity<DetailActivity>(
                    "TITLE" to it.title,
                    "POSTER" to it.poster,
                    "BACKDROP" to it.backdrop,
                    "OVERVIEW" to it.overview
            )
        }

        listMovie.adapter = adapter
        presenter = MainPresenter(this, ApiRepository(), Gson())
        presenter.getMovieList()
    }

    override fun showMovieList(data: List<Movie>) {
        movies.clear()
        movies.addAll(data)
        adapter.notifyDataSetChanged()
    }
}