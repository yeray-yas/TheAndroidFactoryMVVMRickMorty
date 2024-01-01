package com.example.theandroidfactorymvvmrickmorty.data.repository

import com.example.theandroidfactorymvvmrickmorty.data.remote.ApiService

class MainRepository(private val apiService: ApiService) {
    // Single source of truth

    // load data from API
     fun  getCharacters(page:String) = apiService.fetchCharacters(page)
}