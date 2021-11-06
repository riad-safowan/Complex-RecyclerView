package com.riadsafowan.recyclerView.data.repository

import com.riadsafowan.recyclerView.data.remote.ApiClient
import com.riadsafowan.recyclerView.data.remote.SafeApiCall
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiClient: ApiClient
):SafeApiCall{
    suspend fun getMovies() = safeApiCall { apiClient.getMovies() }
    suspend fun getDirectors() = safeApiCall { apiClient.getDirectors() }

}