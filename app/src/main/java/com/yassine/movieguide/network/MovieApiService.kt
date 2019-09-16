package com.yassine.movieguide.network

import com.yassine.movieguide.core.models.Category
import com.yassine.movieguide.core.models.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("getCategories.php")
    fun getAllCategories(): Observable<ArrayList<Category>>

    @GET("get{categoryName}Films.php")
    fun getMoviesByCategorie(@Path("categoryName") categoryName: String): Observable<ArrayList<Movie>>

}