package com.shadows.mystarwarswiki.ui.adapters.films

import com.shadows.core.models.local.StarWarsFilm

interface FilmClickLister {

    fun onFilmClickListener(starWarsFilm: StarWarsFilm)
}