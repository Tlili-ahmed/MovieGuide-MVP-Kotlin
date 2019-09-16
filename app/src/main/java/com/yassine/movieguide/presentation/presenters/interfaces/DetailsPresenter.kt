package com.yassine.movieguide.presentation.presenters.interfaces

import com.yassine.movieguide.presentation.presenters.BasePresenter

interface DetailsPresenter : BasePresenter {

    interface View {
        fun showMovieDetails()
    }

    fun setView(view: DetailsPresenter.View)
}