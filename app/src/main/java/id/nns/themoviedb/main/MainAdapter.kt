package id.nns.themoviedb.main

import android.graphics.Color
import android.view.View
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout.GRAVITY_CENTER
import com.squareup.picasso.Picasso
import id.nns.themoviedb.BuildConfig.URL_POSTER
import id.nns.themoviedb.R
import id.nns.themoviedb.model.Movie
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainAdapter(private val result: List<Movie>, private val listener: (Movie) -> Unit)
    : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(result[position], listener)
    }

    override fun getItemCount(): Int = result.size
}

class MovieUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(5)
                orientation = LinearLayout.VERTICAL

                imageView {
                    id = R.id.movie_poster
                }.lparams {
                    width = dip(140)
                    height = dip(210)
                    margin = dip(16)
                    gravity = GRAVITY_CENTER
                }

                textView {
                    id = R.id.movie_title
                    textSize = 16f
                    textAlignment = TEXT_ALIGNMENT_CENTER
                    textColor = Color.WHITE
                }
            }
        }
    }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val moviePoster: ImageView = view.findViewById(R.id.movie_poster)
    private val movieTitle: TextView = view.findViewById(R.id.movie_title)

    fun bindItem(movies: Movie, listener: (Movie) -> Unit) {
        Picasso.get().load(URL_POSTER + movies.poster).into(moviePoster)
        movieTitle.text = movies.title

        moviePoster.onClick {
            listener(movies)
        }
    }
}