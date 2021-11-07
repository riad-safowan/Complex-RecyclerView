package com.riadsafowan.recyclerView.di

import android.content.Context
import androidx.room.Room
import com.riadsafowan.recyclerView.data.local.AppDatabase
import com.riadsafowan.recyclerView.data.local.PersonDao
import com.riadsafowan.recyclerView.data.remote.ApiClient
import com.riadsafowan.recyclerView.data.remote.RemoteDataSource
import com.riadsafowan.recyclerView.data.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "user")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCartDao(appDatabase: AppDatabase): PersonDao = appDatabase.personDao()

    @Provides
    @Singleton
    fun provideAppRepository(personDao: PersonDao): AppRepository = AppRepository(personDao)


}