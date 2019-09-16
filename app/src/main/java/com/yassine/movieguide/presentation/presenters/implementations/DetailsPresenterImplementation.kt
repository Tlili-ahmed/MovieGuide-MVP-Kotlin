package com.yassine.movieguide.presentation.presenters.implementations

import com.yassine.movieguide.presentation.presenters.interfaces.DetailsPresenter

class DetailsPresenterImplementation : DetailsPresenter {

    private var view: DetailsPresenter.View? = null

    override fun setView(view: DetailsPresenter.View) {
        this.view = view
        if (isViewAttached()) {
            this.view?.showMovieDetails()
        }
    }

    override fun resume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        this.view = null
    }

    override fun isViewAttached(): Boolean {
        return this.view != null
    }
}