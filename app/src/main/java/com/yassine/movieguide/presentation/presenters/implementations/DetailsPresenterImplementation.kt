package com.yassine.movieguide.presentation.presenters.implementations

import com.yassine.movieguide.presentation.presenters.interfaces.DetailsPresenter

class DetailsPresenterImplementation (private var view: DetailsPresenter.View?) : DetailsPresenter {

    init {
        if (isViewAttached()){
            this.view?.showMovieDetails()
        }
    }

    override fun destroy() {
        view = null
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

}