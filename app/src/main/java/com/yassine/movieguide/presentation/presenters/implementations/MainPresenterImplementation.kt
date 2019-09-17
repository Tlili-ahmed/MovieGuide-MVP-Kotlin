package com.yassine.movieguide.presentation.presenters.implementations

import android.util.Log
import com.yassine.movieguide.core.models.Category
import com.yassine.movieguide.presentation.models.MainModel
import com.yassine.movieguide.presentation.presenters.interfaces.MainPresenter

class MainPresenterImplementation(private val view: MainPresenter.View, private val mainModel: MainModel) : MainPresenter {

    private val TAG = "Main Presenter"
    private var loadedCategories: ArrayList<Category>? = null


    fun initialize(){
        mainModel.getAllMovies()
    }

    override fun onCategoriesFetchSuccess(categories: ArrayList<Category>) {
        loadedCategories = categories
        this.view.showCategories(loadedCategories!!)

    }

    override fun updateCategory(category: Category) {
        this.view.updateCategory(category)
    }

    override fun onMoviesFetchFailed(e: Throwable) {
        Log.e(TAG, e.toString())
        view.showError()
    }
}