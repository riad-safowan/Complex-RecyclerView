package com.riadsafowan.recyclerView.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.riadsafowan.recyclerView.R
import com.riadsafowan.recyclerView.databinding.ItemDirectorBinding
import com.riadsafowan.recyclerView.databinding.ItemMovieBinding
import com.riadsafowan.recyclerView.databinding.ItemTitleBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var items = listOf<RecyclerViewItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: ((view: View, item: RecyclerViewItem, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            R.layout.item_title ->
                ViewHolder.TitleViewHolder(
                    ItemTitleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            R.layout.item_movie ->
                ViewHolder.MovieViewHolder(
                    ItemMovieBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            R.layout.item_director ->
                ViewHolder.DirectorViewHolder(
                    ItemDirectorBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemClickListener = itemClickListener
        when (holder) {
            is ViewHolder.DirectorViewHolder -> holder.bind(items[position] as RecyclerViewItem.Director)
            is ViewHolder.MovieViewHolder -> holder.bind(items[position] as RecyclerViewItem.Movie)
            is ViewHolder.TitleViewHolder -> holder.bind(items[position] as RecyclerViewItem.Title)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is RecyclerViewItem.Director -> R.layout.item_director
            is RecyclerViewItem.Movie -> R.layout.item_movie
            is RecyclerViewItem.Title -> R.layout.item_title
        }
    }

    override fun getItemCount() = items.size

    sealed class ViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        var itemClickListener: ((view: View, item: RecyclerViewItem, position: Int) -> Unit)? = null

        class TitleViewHolder(private val binding: ItemTitleBinding) : ViewHolder(binding) {
            fun bind(title: RecyclerViewItem.Title) {
                binding.textViewAll.setOnClickListener {
                    itemClickListener?.invoke(it, title, adapterPosition)
                }
                binding.textViewTitle.text = title.title
            }
        }

        class MovieViewHolder(private val binding: ItemMovieBinding) : ViewHolder(binding) {
            fun bind(movie: RecyclerViewItem.Movie) {
                //  binding.imageViewMovie.setImageURI(Uri.parse(movie.thumbnail))
                binding.root.setOnClickListener {
                    itemClickListener?.invoke(it, movie, adapterPosition)
                }
            }
        }

        class DirectorViewHolder(private val binding: ItemDirectorBinding) : ViewHolder(binding) {
            fun bind(director: RecyclerViewItem.Director) {
                // binding.imageViewDirector.setImageURI(Uri.parse(director.avatar))
                binding.root.setOnClickListener {
                    itemClickListener?.invoke(it, director, adapterPosition)
                }
            }
        }
    }


}
