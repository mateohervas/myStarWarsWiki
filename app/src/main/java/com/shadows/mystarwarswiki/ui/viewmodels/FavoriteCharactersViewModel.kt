package com.shadows.mystarwarswiki.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shadows.core.models.local.Resource
import com.shadows.core.models.local.StarWarsCharacter
import com.shadows.core.utils.getPersonalizedMessage
import com.shadows.mystarwarswiki.domain.FavoriteCharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

//this view model is in charge of consulting and emitting favorite characters
@HiltViewModel
class FavoriteCharactersViewModel @Inject constructor(private val repository: FavoriteCharactersRepository): ViewModel()  {

    private val _favoriteCharacters = MutableLiveData<Resource<List<StarWarsCharacter>>>()
    val favoriteCharacters get() = _favoriteCharacters

    fun getFavoriteCharacters(){
        viewModelScope.launch {
            repository.getFavoriteCharacters().onStart {
                _favoriteCharacters.value = Resource.Loading(true)
            }.catch {
                _favoriteCharacters.value = Resource.Error(it.getPersonalizedMessage())
            }.collect {
                _favoriteCharacters.value = Resource.Loading(false)
                _favoriteCharacters.value = Resource.Success(it)
            }
        }
    }
}