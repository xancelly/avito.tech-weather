package com.example.avitotechweather.presentation.fragments.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.avitotechweather.databinding.SearchResultLayoutBinding
import com.example.avitotechweather.domain.repository.GeocoderRepository
import com.yandex.mapkit.search.SuggestItem

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(val binding: SearchResultLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<SuggestItem>() {
        override fun areItemsTheSame(oldItem: SuggestItem, newItem: SuggestItem): Boolean {
            return oldItem.displayText == newItem.displayText
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: SuggestItem, newItem: SuggestItem): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var suggestList: List<SuggestItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            SearchResultLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentSuggestItem = suggestList[position]
        holder.binding.apply {
            fullNameCity.text = currentSuggestItem.displayText
        }

        holder.itemView.setOnClickListener { view ->
            val direction = SearchFragmentDirections.actionSearchFragmentToWeatherFragment(
                latitude = 1f,
                longitude = 1f
            )
            view.findNavController().navigate(direction)


        }
    }

    override fun getItemCount(): Int = suggestList.size
}