package com.yassine.movieguide.presentation.ui.adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yassine.movieguide.R
import com.yassine.movieguide.core.models.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoriesAdapter : RecyclerView.Adapter<CategoriesViewHolder>() {

    private var categories: ArrayList<Category> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categories.get(position))
    }

    fun setCategories(categories: ArrayList<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    fun updateCategory(category: Category) {
        categories.set(categories.indexOf(category), category)
        notifyItemChanged(categories.indexOf(category))
    }
}

class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val moviesAdapter : MoviesAdapter = MoviesAdapter()
    init {
        itemView.rvMovies.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        itemView.rvMovies.adapter = moviesAdapter
    }
    fun bind(category: Category) {
        itemView.categoryName.text = category.name

        if (category.movies != null){
            hideProgress()
            moviesAdapter.setMovies(category.movies!!)
        }
    }

    fun hideProgress(){
        itemView.progressBar.visibility = View.GONE
    }

}