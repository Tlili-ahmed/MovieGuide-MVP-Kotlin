package com.yassine.movieguide.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInit {
    fun create(): MovieApiService {

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create())
                .baseUrl("https://yassinemoslah.com/api/")
                .build()

        return retrofit.create(MovieApiService::class.java)
    }
}