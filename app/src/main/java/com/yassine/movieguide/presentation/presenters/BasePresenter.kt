package com.yassine.movieguide.presentation.presenters

interface BasePresenter {
    fun destroy()
    fun isViewAttached() : Boolean
}