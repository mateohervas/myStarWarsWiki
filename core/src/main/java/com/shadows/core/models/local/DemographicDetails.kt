package com.shadows.core.models.local

data class DemographicDetails(var species: StarWarsSpecies?,
                              var planet: StarWarsPlanet?,
                              var films: ArrayList<StarWarsFilm> = ArrayList()
)
