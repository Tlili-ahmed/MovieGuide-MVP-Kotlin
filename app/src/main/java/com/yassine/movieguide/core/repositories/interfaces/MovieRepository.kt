package com.yassine.movieguide.core.repositories.interfaces

import com.yassine.movieguide.core.models.Category
import com.yassine.movieguide.core.models.Movie
import io.reactivex.Observable

interface MovieRepository {
    fun getAllCategories() : Observable<ArrayList<Category>>
    fun getMoviesByCategorie(category: Category): Observable<ArrayList<Movie>>
}