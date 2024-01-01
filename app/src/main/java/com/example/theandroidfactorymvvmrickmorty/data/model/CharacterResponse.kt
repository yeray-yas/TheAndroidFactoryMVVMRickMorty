package com.example.theandroidfactorymvvmrickmorty.data.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results")
    val result: List<Character>,
)