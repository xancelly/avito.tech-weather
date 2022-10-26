package com.example.avitotechweather.presentation.fragments.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotechweather.data.repository.KladrRepositoryImpl
import com.example.avitotechweather.domain.models.SearchResult
import com.example.avitotechweather.domain.repository.KladrRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject
constructor(private val kladrRepository: KladrRepository): ViewModel() {

    private val response = MutableLiveData<SearchResult>()
    private var searchField: String = ""

    val searchResponse: LiveData<SearchResult>
        get() = response

    init {
        if (searchField.isNotEmpty())
            getAddress(searchField)
    }

    fun setSearchField(search: String) {
        searchField = search
    }

    private fun getAddress(query: String) = viewModelScope.launch {
        kladrRepository.getAddress(query = query).let { resp ->
            if (resp.isSuccessful) {
                response.postValue(resp.body())
                Log.i("getAddress", resp.message())
            } else {
                Log.e("getAddress", resp.message())
            }
        }
    }
}