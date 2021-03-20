package com.shadows.mystarwarswiki.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shadows.core.models.local.*
import com.shadows.core.utils.getPersonalizedMessage
import com.shadows.mystarwarswiki.domain.CharacterDemographicsRepository
import com.shadows.mystarwarswiki.domain.CharacterFilmsRepository
import com.shadows.mystarwarswiki.domain.FavoriteCharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersDetailViewModel @Inject constructor(
    private val demographicsRepository: CharacterDemographicsRepository,
    private val filmsRepository: CharacterFilmsRepository,
    private val favoriteCharactersRepository: FavoriteCharactersRepository
) : ViewModel() {

    private var demographicDetailsData = DemographicDetails(null, null)

    private val _demographicDetails = MutableLiveData<Resource<DemographicDetails>>()
    val demographicDetails get() = _demographicDetails

    private val _favoriteCharacter = MutableLiveData<Resource<Boolean>>()
    val favoriteCharacter get() = _favoriteCharacter

    fun getDemographicInformation(speciesUrl: String, planetUrl: String, filmsList: List<String>) =
        viewModelScope.launch {
            try {
                _demographicDetails.value = Resource.Loading(true)
                getSpeciesInformation(speciesUrl)
                getPlanetInformation(planetUrl)
                getFilmsInformation(filmsList)
            } catch (e: Throwable) {
                _demographicDetails.value = Resource.Error(e.getPersonalizedMessage())
            } finally {
                _demographicDetails.value = Resource.Loading(false)
            }

        }

    private suspend fun getSpeciesInformation(speciesUrl: String) =
        demographicsRepository.getSpeciesByUrl(speciesUrl).collect {
            demographicDetailsData.species = it
            _demographicDetails.value = Resource.Success(demographicDetailsData)
        }


    private suspend fun getPlanetInformation(planetUrl: String) =
        demographicsRepository.getPlanetByUrl(planetUrl).collect {
            demographicDetailsData.planet = it
            _demographicDetails.value = Resource.Success(demographicDetailsData)
        }


    private suspend fun getFilmsInformation(filmsList: List<String>) =
        filmsRepository.getFilmsByUrl(filmsList).collect {
            demographicDetailsData.films.addAll(it)
            _demographicDetails.value = Resource.Success(demographicDetailsData)
        }


    fun saveFavoriteCharacter(starWarsCharacter: StarWarsCharacter) = viewModelScope.launch {
        favoriteCharactersRepository.saveCharacter(starWarsCharacter).collect {
            _favoriteCharacter.value = Resource.Success(true)
        }
    }


    fun deleteFavoriteCharacter(starWarsCharacter: StarWarsCharacter) = viewModelScope.launch {
        favoriteCharactersRepository.deleteCharacter(starWarsCharacter).collect {
            _favoriteCharacter.value = Resource.Success(false)
        }
    }

    fun checkIfIsFavorite(characterUrl: String) = viewModelScope.launch {
        favoriteCharactersRepository.isCharacterFavorite(characterUrl).collect {
            _favoriteCharacter.value = Resource.Success(it)
        }
    }


}