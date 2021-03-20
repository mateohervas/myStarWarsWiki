package com.shadows.mystarwarswiki.ui.viewmodels

import androidx.lifecycle.*
import com.shadows.core.models.local.Resource
import com.shadows.core.models.local.StarWarsCharacter
import com.shadows.core.utils.getPersonalizedMessage
import com.shadows.mystarwarswiki.domain.CharacterSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterSearchViewModel @Inject constructor(private val characterSearchRepository: CharacterSearchRepository): ViewModel() {

    private val _characters = MutableLiveData<Resource<List<StarWarsCharacter>>>()
    val characters get() = _characters

    fun getStarWarsCharacter(name:String){
        viewModelScope.launch {
            characterSearchRepository.getCharacterByName(name).onStart {
                _characters.value = Resource.Loading(true)
            }.catch { e ->
                _characters.value = Resource.Loading(false)
                characters.value = Resource.Error(e.getPersonalizedMessage())
            }.collect {
                _characters.value = Resource.Loading(false)
                _characters.value = Resource.Success(it)

            }
        }
    }




}