package com.yassine.movieguide.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.yassine.movieguide.R
import com.yassine.movieguide.core.models.Movie
import com.yassine.movieguide.presentation.presenters.implementations.DetailsPresenterImplementation
import com.yassine.movieguide.presentation.presenters.interfaces.DetailsPresenter
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : YouTubeBaseActivity(), DetailsPresenter.View, YouTubePlayer.OnInitializedListener {

    var movie: Movie? = null
    private val RECOVERY_REQUEST = 1
    private var detailsPresenter : DetailsPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        detailsPresenter = DetailsPresenterImplementation(this)
    }

    override fun showMovieDetails() {
        movie = intent.getParcelableExtra<Movie>("movie")
        trailerVideo.initialize(getString(R.string.YouTube_Api_Key), this)

        if (movie != null) {
            movieTitle.text = movie?.title
            movieYear.text = movie?.year
            movieDescription.apply {
                text = movie?.description
                movementMethod = ScrollingMovementMethod()
            }
        }
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        if (!p2 && p1 != null && movie != null) {
            p1.cueVideo(movie?.trailerUrl)
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        if (p1 != null && p1.isUserRecoverableError) {
            p1.getErrorDialog(this, RECOVERY_REQUEST).show()
        } else {
            Toast.makeText(this, p1.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            trailerVideo.initialize(getString(R.string.YouTube_Api_Key), this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        detailsPresenter?.destroy()
    }
}
