package com.riadsafowan.recyclerView.data.remote

import com.riadsafowan.recyclerView.ui.RecyclerViewItem
import retrofit2.http.GET

interface ApiClient {

    @GET("movies")
    suspend fun getMovies(): List<RecyclerViewItem.Movie>

    @GET("directors")
    suspend fun getDirectors(): List<RecyclerViewItem.Director>
}