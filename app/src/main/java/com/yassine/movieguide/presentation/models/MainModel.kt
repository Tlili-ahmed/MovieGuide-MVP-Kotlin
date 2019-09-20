package com.yassine.movieguide.presentation.models

import android.arch.lifecycle.ViewModel
import com.yassine.movieguide.core.interactors.implementations.GetAllMoviesInteractorImplementation
import com.yassine.movieguide.core.interactors.interfaces.GetAllMoviesInteractor
import com.yassine.movieguide.core.models.Category
import com.yassine.movieguide.presentation.presenters.interfaces.MainPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainModel : ViewModel() {
    private var getAllMoviesInteractor: GetAllMoviesInteractor
    private var compositeDisposable: CompositeDisposable
    private var mainPresenter: MainPresenter? = null
    private var categories = ArrayList<Category>()
    private var throwable: Throwable? = null
    private var firstCall = true

    init {
        getAllMoviesInteractor = GetAllMoviesInteractorImplementation()
        compositeDisposable = CompositeDisposable()
    }

    fun initialize(mainPresenter: MainPresenter) {
        this.mainPresenter = mainPresenter
    }

    fun getAllMovies() {
        if (firstCall) {
            getMovies()
                    .subscribe(
                            {
                                mainPresenter?.updateCategory(it)
                                categories.add(it)
                            },
                            {
                                throwable = it
                                mainPresenter?.onMoviesFetchFailed(it)
                            },
                            {
                                firstCall = false
                            },
                            {
                                compositeDisposable.add(it)
                            }
                    )

        } else {
            if (throwable == null) {
                mainPresenter?.onCategoriesFetchSuccess(categories)
                Observable.fromIterable(categories).subscribe { mainPresenter?.updateCategory(it) }
            } else {
                mainPresenter?.onMoviesFetchFailed(throwable!!)
            }

        }

    }

    private fun getMovies(): Observable<Category> {
        return getAllCategories()
                .subscribeOn(Schedulers.io())
                .flatMap {
                    getMoviesObservable(category = it)
                }
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getAllCategories(): Observable<Category> {
        return getAllMoviesInteractor.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    mainPresenter?.onCategoriesFetchSuccess(it)
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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}