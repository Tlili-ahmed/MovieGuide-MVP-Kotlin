package com.yassine.movieguide.core.interactors.implementations

import com.yassine.movieguide.core.interactors.interfaces.GetAllMoviesInteractor
import com.yassine.movieguide.core.models.Category
import com.yassine.movieguide.core.models.Movie
import com.yassine.movieguide.core.repositories.implementations.MovieRepositoryImplementation
import com.yassine.movieguide.core.repositories.interfaces.MovieRepository
import io.reactivex.Observable

class GetAllMoviesInteractorImplementation() : GetAllMoviesInteractor {

    private var movieRepository : MovieRepository

    init {
        movieRepository = MovieRepositoryImplementation()
    }

    override fun getAllCategories(): Observable<ArrayList<Category>> {
        return movieRepository.getAllCategories()
    }

    override fun getMoviesByCategorie(category: Category): Observable<ArrayList<Movie>> {
        return movieRepository.getMoviesByCategorie(category)
    }

}