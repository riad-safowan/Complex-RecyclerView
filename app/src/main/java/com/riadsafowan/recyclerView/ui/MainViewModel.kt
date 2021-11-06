package com.riadsafowan.recyclerView.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riadsafowan.recyclerView.data.remote.Resource
import com.riadsafowan.recyclerView.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    private val _homeListItemsLiveData = MutableLiveData<Resource<List<RecyclerViewItem>>>()
    val listItemsLiveData: LiveData<Resource<List<RecyclerViewItem>>>
        get() = _homeListItemsLiveData

    init {
        getHomeListItems()
    }

    private fun getHomeListItems() = viewModelScope.launch {
        _homeListItemsLiveData.postValue(Resource.Loading)
        val moviesDeferred = async { apiRepository.getMovies() }
        val directorsDeferred = async { apiRepository.getDirectors() }

        val movies = moviesDeferred.await()
        val directors = directorsDeferred.await()

        val homeItemsList = mutableListOf<RecyclerViewItem>()
        if(movies is Resource.Success && directors is Resource.Success){
            homeItemsList.add(RecyclerViewItem.Title(1, "Recommended Movies"))
            homeItemsList.addAll(movies.value)
            homeItemsList.add(RecyclerViewItem.Title(2, "Top Directors"))
            homeItemsList.addAll(directors.value)
            _homeListItemsLiveData.postValue(Resource.Success(homeItemsList))
        }else{
            Resource.Failure(false, null, null)
        }
    }
}