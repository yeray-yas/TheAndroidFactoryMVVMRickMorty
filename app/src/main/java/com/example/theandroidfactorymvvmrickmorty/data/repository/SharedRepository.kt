package com.example.theandroidfactorymvvmrickmorty.data.repository

import com.example.theandroidfactorymvvmrickmorty.data.model.GetCharacterByIdResponse
import com.example.theandroidfactorymvvmrickmorty.data.remote.NetworkLayer
import com.example.theandroidfactorymvvmrickmorty.data.remote.NetworkLayer.apiClient

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): GetCharacterByIdResponse? {
        val request = apiClient.getCharacterById(characterId)

        if (request.failed){
            return null
        }

        if (!request.isSuccessful){
            return null
        }

        return request.body
    }
}