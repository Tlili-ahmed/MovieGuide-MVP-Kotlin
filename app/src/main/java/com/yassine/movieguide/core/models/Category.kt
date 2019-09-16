package com.yassine.movieguide.core.models

import com.google.gson.annotations.SerializedName

data class Category(@SerializedName("categoryName") val name : String, var movies: ArrayList<Movie>?)