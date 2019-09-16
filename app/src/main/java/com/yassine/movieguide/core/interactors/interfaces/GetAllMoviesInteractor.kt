package com.yassine.movieguide.core.interactors.interfaces

import com.yassine.movieguide.core.models.Category
import com.yassine.movieguide.core.models.Movie
import io.reactivex.Observable

interface GetAllMoviesInteractor {
    fun getAllCategories(): Observable<ArrayList<Category>>
    fun getMoviesByCategorie(category: Category): Observable<ArrayList<Movie>>
}