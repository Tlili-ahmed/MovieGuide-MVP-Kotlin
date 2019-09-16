package com.yassine.movieguide.utils

import com.yassine.movieguide.network.RetrofitInit

val MoviesApiServe by lazy {
    RetrofitInit.create()
}