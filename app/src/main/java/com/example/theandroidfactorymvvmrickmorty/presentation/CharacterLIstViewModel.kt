package com.example.theandroidfactorymvvmrickmorty.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theandroidfactorymvvmrickmorty.data.model.CharacterResponse
import com.example.theandroidfactorymvvmrickmorty.data.remote.ApiClient
import com.example.theandroidfactorymvvmrickmorty.data.repository.MainRepository
import com.example.theandroidfactorymvvmrickmorty.utils.ScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.theandroidfactorymvvmrickmorty.data.model.Character
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val repository: MainRepository = MainRepository(ApiClient.apiService)
) : ViewModel() {

    private var _charactersLiveData = MutableLiveData<ScreenState<List<Character>?>>()
    val charactersLiveData: LiveData<ScreenState<List<Character>?>>
        get() = _charactersLiveData

    init {
        fetchCharacter()
    }

    private fun fetchCharacter() {
        viewModelScope.launch {
            val client = repository.getCharacters("1")
            _charactersLiveData.postValue(ScreenState.Loading(null))
            client.enqueue(object : Callback<CharacterResponse> {
                override fun onResponse(
                    call: Call<CharacterResponse>,
                    response: Response<CharacterResponse>
                ) {
                    if (response.isSuccessful) {
                        _charactersLiveData.postValue(ScreenState.Success(response.body()?.result))
                    } else {
                        _charactersLiveData.postValue(
                            ScreenState.Error(
                                response.code().toString(),
                                null
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    // Log.d("ERRORO", t.message.toString())
                    _charactersLiveData.postValue(ScreenState.Error(t.message.toString(), null))
                }
            })
        }
    }
}