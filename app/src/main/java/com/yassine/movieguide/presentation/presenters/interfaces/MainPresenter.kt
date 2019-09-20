package com.yassine.movieguide.presentation.presenters.interfaces

import com.yassine.movieguide.core.models.Category
import com.yassine.movieguide.presentation.presenters.BasePresenter

interface MainPresenter : BasePresenter {

    interface View {
        fun showCategories(categories: ArrayList<Category>)
        fun updateCategory(categorie: Category)
        fun hideProgress()
        fun showError()
    }

    fun onCategoriesFetchSuccess(categories: ArrayList<Category>)
    fun updateCategory(category: Category)
    fun onMoviesFetchFailed(e: Throwable)
    fun initialize()
}