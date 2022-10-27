package com.example.avitotechweather.presentation.fragments.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotechweather.domain.models.kladr.SearchResult
import com.example.avitotechweather.domain.repository.KladrRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject
constructor(private val kladrRepository: KladrRepository): ViewModel() {

    private val response = MutableLiveData<SearchResult>()
    private var searchField: String = "мануш"

    val searchResponse: LiveData<SearchResult>
        get() = response

    init {
        getAddress(searchField)
    }

    fun setSearchField(search: String) {
        searchField = search
        getAddress(search)
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