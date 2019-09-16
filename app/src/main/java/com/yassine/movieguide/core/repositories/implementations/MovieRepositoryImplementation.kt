package com.yassine.movieguide.core.repositories.implementations

import com.yassine.movieguide.core.models.Category
import com.yassine.movieguide.core.models.Movie
import com.yassine.movieguide.core.repositories.interfaces.MovieRepository
import com.yassine.movieguide.utils.MoviesApiServe
import io.reactivex.Observable

class MovieRepositoryImplementation : MovieRepository {
    override fun getAllCategories(): Observable<ArrayList<Category>> {
        return MoviesApiServe.getAllCategories()
    }

    override fun getMoviesByCategorie(category: Category): Observable<ArrayList<Movie>> {
        return MoviesApiServe.getMoviesByCategorie(category.name)
    }
}