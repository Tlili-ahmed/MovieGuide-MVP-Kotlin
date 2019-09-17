package com.yassine.movieguide.presentation.presenters.interfaces

import com.yassine.movieguide.core.models.Category

interface MainPresenter {

    interface View {
        fun showCategories(categories: ArrayList<Category>)
        fun updateCategory(categorie: Category)
        fun hideProgress()
        fun showError()
    }

    fun onCategoriesFetchSuccess(categories: ArrayList<Category>)
    fun updateCategory(category: Category)
    fun onMoviesFetchFailed(e: Throwable)
}