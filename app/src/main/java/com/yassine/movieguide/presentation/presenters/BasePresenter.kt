package com.yassine.movieguide.presentation.presenters

interface BasePresenter {
    fun resume()
    fun pause()
    fun stop()
    fun destroy()
    fun isViewAttached(): Boolean
}