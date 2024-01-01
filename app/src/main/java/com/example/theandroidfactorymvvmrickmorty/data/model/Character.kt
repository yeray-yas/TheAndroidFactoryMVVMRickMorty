package com.example.theandroidfactorymvvmrickmorty.data.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String, val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)