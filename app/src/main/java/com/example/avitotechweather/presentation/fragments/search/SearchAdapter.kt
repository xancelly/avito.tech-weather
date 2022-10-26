package com.example.avitotechweather.presentation.fragments.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.avitotechweather.databinding.SearchResultLayoutBinding
import com.example.avitotechweather.domain.models.SearchResult

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(val binding: SearchResultLayoutBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<com.example.avitotechweather.domain.models.Result>() {
        override fun areItemsTheSame(oldItem: com.example.avitotechweather.domain.models.Result, newItem: com.example.avitotechweather.domain.models.Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: com.example.avitotechweather.domain.models.Result, newItem: com.example.avitotechweather.domain.models.Result): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var resultList: List<com.example.avitotechweather.domain.models.Result>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(SearchResultLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentSearch = resultList[position]
        holder.binding.apply {
            fullNameCity.text = currentSearch.fullName
        }
    }

    override fun getItemCount(): Int = resultList.size

}