package com.shadows.mystarwarswiki.ui.adapters.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shadows.core.models.local.StarWarsCharacter
import com.shadows.mystarwarswiki.R
import com.shadows.mystarwarswiki.databinding.ItemCharacterBinding

class SearchResultsAdapter(private val characterClickListener: CharacterClickListener): ListAdapter<StarWarsCharacter, SearchResultsAdapter.ViewHolder>(
    CharactersDiffer
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character,parent,false)
        val binding = ItemCharacterBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root){

        fun bindView(starWarsCharacter: StarWarsCharacter){
            binding.apply {
                tvCharacterName.text = starWarsCharacter.name
                val numberOfFilms = starWarsCharacter.films.size.toString()
                val s = if(starWarsCharacter.films.size==1) "" else "s"
                tvNumberOfMovies.text = root.context.getString(R.string.number_of_films,numberOfFilms,s)
            }
            binding.root.setOnClickListener {
                characterClickListener.goToCharacterDetailActivity(starWarsCharacter)
            }
        }
    }

    companion object CharactersDiffer : DiffUtil.ItemCallback<StarWarsCharacter>() {
        override fun areItemsTheSame(
            oldItem: StarWarsCharacter,
            newItem: StarWarsCharacter
        ): Boolean = oldItem.url == newItem.url

        override fun areContentsTheSame(
            oldItem: StarWarsCharacter,
            newItem: StarWarsCharacter
        ): Boolean = oldItem == newItem
    }


}