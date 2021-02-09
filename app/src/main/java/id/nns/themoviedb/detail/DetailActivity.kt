package id.nns.themoviedb.detail

import android.graphics.Color
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.nns.themoviedb.BuildConfig.URL_POSTER
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    private var posterMovie: String? = ""
    private var titleMovie: String? = ""
    private var overviewMovie: String? = ""
    private var backdropMovie: String? = ""

    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var overview: TextView
    private lateinit var backdrop: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            lparams(width = matchParent, height = matchParent)
            backgroundColor = 0xFF1F1F1F.toInt()

            scrollView {
                lparams(width = matchParent, height = wrapContent)

                linearLayout {
                    lparams(width = matchParent, height = matchParent)
                    orientation = LinearLayout.VERTICAL

                    poster = imageView {
                    }.lparams {
                        width = dip(320)
                        gravity = Gravity.CENTER
                        margin = dip(16)
                    }

                    title = textView {
                        textSize = 16f
                        textColor = Color.CYAN
                        textAlignment = TEXT_ALIGNMENT_CENTER
                    }

                    overview = textView {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                                justificationMode = JUSTIFICATION_MODE_INTER_WORD
                            }
                        }
                        textColor = Color.WHITE
                    }.lparams {
                        margin = dip(16)
                    }

                    backdrop = imageView {
                    }.lparams {
                        gravity = Gravity.CENTER
                        margin = dip(16)
                    }
                }
            }
        }

        val i = intent
        posterMovie = i.getStringExtra("POSTER")
        titleMovie = i.getStringExtra("TITLE")
        overviewMovie = i.getStringExtra("OVERVIEW")
        backdropMovie = i.getStringExtra("BACKDROP")

        Picasso.get().load(URL_POSTER + posterMovie).into(poster)
        title.text = titleMovie
        overview.text = overviewMovie
        Picasso.get().load(URL_POSTER + backdropMovie).into(backdrop)
    }
}