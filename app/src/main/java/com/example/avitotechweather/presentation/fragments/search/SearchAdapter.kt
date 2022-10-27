package com.example.avitotechweather.presentation.fragments.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.avitotechweather.databinding.SearchResultLayoutBinding
import com.example.avitotechweather.domain.models.kladr.Result

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(val binding: SearchResultLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.guid == newItem.guid
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var resultList: List<Result>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            SearchResultLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @Suppress("SENSELESS_COMPARISON")
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentSearch = resultList[position]
        holder.binding.apply {
            try {
                fullNameCity.text =
                    if (currentSearch.parents != null)
                        if (currentSearch.parents[1] != null)
                            "${currentSearch.parents[0].name} ${currentSearch.parents[0].typeShort}, ${currentSearch.parents[1].name} ${currentSearch.parents[1].typeShort}, ${currentSearch.typeShort}. ${currentSearch.name}"
                        else
                            "${currentSearch.parents[0].name} ${currentSearch.parents[0].typeShort}, ${currentSearch.typeShort}. ${currentSearch.name}"
                    else
                        "${currentSearch.typeShort}. ${currentSearch.name}"
            } catch (e: Exception) {
                Log.e("SearchAdapter", "onBindViewHolder error: ${e.message.toString()}")
            }
        }
    }

    override fun getItemCount(): Int = resultList.size

}