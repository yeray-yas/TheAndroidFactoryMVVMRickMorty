package com.example.theandroidfactorymvvmrickmorty.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theandroidfactorymvvmrickmorty.data.repository.SharedRepository
import com.example.theandroidfactorymvvmrickmorty.data.model.GetCharacterByIdResponse
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val repository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<GetCharacterByIdResponse?>()
    val characterByIdLiveData: LiveData<GetCharacterByIdResponse?> = _characterByIdLiveData

    init {
        refreshCharacter(54)
    }

    private fun refreshCharacter(characterId: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)

            _characterByIdLiveData.postValue(response)
        }
    }
}