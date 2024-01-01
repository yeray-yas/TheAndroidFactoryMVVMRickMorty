package com.example.theandroidfactorymvvmrickmorty.data.remote

import com.example.theandroidfactorymvvmrickmorty.data.model.GetCharacterByIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") characterId: Int): Response<GetCharacterByIdResponse>
}