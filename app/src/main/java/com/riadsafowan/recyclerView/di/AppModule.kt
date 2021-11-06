package com.riadsafowan.recyclerView.di

import com.riadsafowan.recyclerView.data.remote.ApiClient
import com.riadsafowan.recyclerView.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTargetApi(
        remoteDataSource: RemoteDataSource
    ): ApiClient {
        return remoteDataSource.buildApi(ApiClient::class.java)
    }
}