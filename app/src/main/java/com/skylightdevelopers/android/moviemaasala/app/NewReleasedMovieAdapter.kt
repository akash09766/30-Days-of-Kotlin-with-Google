package com.skylightdevelopers.android.moviemaasala.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.skylightdevelopers.android.moviemaasala.R
import com.skylightdevelopers.android.moviemaasala.app.model.ResultsItem
import com.skylightdevelopers.android.moviemaasala.databinding.NewReleasedMovieItemBinding

class NewReleasedMovieAdapter :
    RecyclerView.Adapter<NewReleasedMovieAdapter.NewReleasedMovieViewHolder>() {
    private  val movieList =  ArrayList<ResultsItem>()

    fun setData(  movieList: List<ResultsItem>?){
        this.movieList.clear()
        movieList?.let { this.movieList.addAll(it) }
        notifyDataSetChanged()
    }

    private val TAG = "NewReleasedMovieAdapter"

    class NewReleasedMovieViewHolder(var view: NewReleasedMovieItemBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewReleasedMovieViewHolder {
        return NewReleasedMovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.new_released_movie_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: NewReleasedMovieViewHolder, position: Int) {

        holder.view.movieItem = movieList[position]
    }
}