package com.yassine.movieguide.presentation.ui.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.yassine.movieguide.R
import com.yassine.movieguide.core.models.Movie
import com.yassine.movieguide.presentation.ui.activities.DetailsActivity
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies: ArrayList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies.get(position))
        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, DetailsActivity::class.java)
            intent.putExtra("movie",movies.get(position))
            it.context.startActivity(intent)
        }
    }

    fun setMovies(movies: ArrayList<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie) {
        Picasso.get().load(movie.image).into(itemView.imgMovie)
    }

}