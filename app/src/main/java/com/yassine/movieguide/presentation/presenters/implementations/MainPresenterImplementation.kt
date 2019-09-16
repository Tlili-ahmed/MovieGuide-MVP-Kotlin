package com.yassine.movieguide.presentation.presenters.implementations

import android.util.Log
import com.yassine.movieguide.core.interactors.implementations.GetAllMoviesInteractorImplementation
import com.yassine.movieguide.core.interactors.interfaces.GetAllMoviesInteractor
import com.yassine.movieguide.core.models.Category
import com.yassine.movieguide.presentation.presenters.interfaces.MainPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenterImplementation : MainPresenter {

    private val TAG = "Main Presenter"

    private var getAllMoviesInteractor: GetAllMoviesInteractor
    private var view: MainPresenter.View? = null

    private var compositeDisposable: CompositeDisposable
    private var loadedCategories: ArrayList<Category>? = null


    init {
        getAllMoviesInteractor = GetAllMoviesInteractorImplementation()
        compositeDisposable = CompositeDisposable()
    }

    override fun setView(view: MainPresenter.View) {
        this.view = view
        getAllMovies()
    }

    private fun getAllMovies() {
        getAllCategories()
                .subscribeOn(Schedulers.io())
                .flatMap {
                    getMoviesObservable(category = it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            updateCategory(it)
                        },
                        {
                            onMoviesFetchFailed(it)
                        },
                        {
                        },
                        {
                            compositeDisposable.add(it)
                        }
                )
    }

    private fun getAllCategories(): Observable<Category> {
        return getAllMoviesInteractor.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    onCategoriesFetchSuccess(it)
                    return@flatMap Observable.fromIterable(it)
                            .subscribeOn(Schedulers.io())
                }
    }


    private fun getMoviesObservable(category: Category): Observable<Category> {
        return getAllMoviesInteractor.getMoviesByCategorie(category)
                .map {
                    category.movies = it
                    return@map category
                }.subscribeOn(Schedulers.io())
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
        compositeDisposable.clear()
    }

    private fun onCategoriesFetchSuccess(categories: ArrayList<Category>) {
        loadedCategories = categories
        if (isViewAttached()) {
            this.view?.showCategories(loadedCategories!!)
        }
    }

    private fun updateCategory(category: Category) {
        if (isViewAttached()) {
            this.view?.updateCategory(category)
        }
    }

    private fun onMoviesFetchFailed(e: Throwable) {
        Log.e(TAG, e.toString())
        if (isViewAttached()) {
            view?.showError()
        }
    }

    override fun isViewAttached(): Boolean {
        return this.view != null
    }
}