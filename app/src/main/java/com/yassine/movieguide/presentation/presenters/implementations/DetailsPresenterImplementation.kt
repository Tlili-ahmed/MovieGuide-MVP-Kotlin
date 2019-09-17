package com.yassine.movieguide.presentation.presenters.implementations

import com.yassine.movieguide.presentation.presenters.interfaces.DetailsPresenter

class DetailsPresenterImplementation (private val view: DetailsPresenter.View) : DetailsPresenter {

    init {
        this.view.showMovieDetails()
    }

}