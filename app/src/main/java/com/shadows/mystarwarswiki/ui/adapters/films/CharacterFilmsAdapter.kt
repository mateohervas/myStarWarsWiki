package com.shadows.mystarwarswiki.ui.adapters.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shadows.core.models.local.StarWarsFilm
import com.shadows.mystarwarswiki.R
import com.shadows.mystarwarswiki.databinding.ItemFilmBinding

class CharacterFilmsAdapter(private val filmClickLister: FilmClickLister):  ListAdapter<StarWarsFilm, CharacterFilmsAdapter.ViewHolder>(
    FilmsDiffer
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film,parent,false)
        val binding = ItemFilmBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class ViewHolder(private val binding:ItemFilmBinding): RecyclerView.ViewHolder(binding.root){
        fun bindView(starWarsFilm: StarWarsFilm){
            binding.apply {
                tvFilmName.text = starWarsFilm.title
                tvReleasedDate.text = starWarsFilm.releasedDate
                root.setOnClickListener{
                    filmClickLister.onFilmClickListener(starWarsFilm)
                }
            }

        }

    }
    companion object FilmsDiffer : DiffUtil.ItemCallback<StarWarsFilm>() {
        override fun areItemsTheSame(
            oldItem: StarWarsFilm,
            newItem: StarWarsFilm
        ): Boolean = oldItem.title == newItem.title

        override fun areContentsTheSame(
            oldItem: StarWarsFilm,
            newItem: StarWarsFilm
        ): Boolean = oldItem == newItem
    }


}