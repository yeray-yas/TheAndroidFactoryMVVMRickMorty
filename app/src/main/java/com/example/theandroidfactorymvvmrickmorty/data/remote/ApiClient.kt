package com.example.theandroidfactorymvvmrickmorty.data.remote

import com.example.theandroidfactorymvvmrickmorty.data.model.GetCharacterByIdResponse
import com.example.theandroidfactorymvvmrickmorty.utils.SimpleResponse
import retrofit2.Response

class ApiClient(
    private val apiService: ApiService
) {
    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { apiService.getCharacterById(characterId) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}