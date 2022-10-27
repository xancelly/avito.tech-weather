package com.example.avitotechweather.presentation.fragments.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.avitotechweather.R
import com.example.avitotechweather.databinding.FragmentSearchBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.BoundingBox
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.search.*
import com.yandex.runtime.Error
import com.yandex.runtime.network.NetworkError
import com.yandex.runtime.network.RemoteError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), SuggestSession.SuggestListener {

    private var searchManager: SearchManager? = null
    private var suggestSession: SuggestSession? = null
    private var searchBinding: FragmentSearchBinding? = null
    private val binding get() = searchBinding!!
    private lateinit var searchEditText: EditText
    private lateinit var resultAdapter: SearchAdapter
    private var suggestResult: List<SuggestItem>? = null
    private val RESULT_NUMBER_LIMIT = 5
    private val SEARCH_OPTIONS = SuggestOptions().setSuggestTypes(SuggestType.GEO.value)

    private val CENTER = Point(55.75, 37.62)
    private val BOX_SIZE = 0.2
    private val BOUNDING_BOX = BoundingBox(
        Point(CENTER.latitude - BOX_SIZE, CENTER.longitude - BOX_SIZE),
        Point(CENTER.latitude + BOX_SIZE, CENTER.longitude + BOX_SIZE)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MapKitFactory.initialize(view.context)

        searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.ONLINE)
        suggestSession = searchManager!!.createSuggestSession()
        resultAdapter = SearchAdapter()
        searchEditText = view.findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                requestSuggest(s.toString())
            }

        })
    }

    private fun loadDataInRecyclerView() {

        binding.resultRecyclerView.apply {
            adapter = resultAdapter
        }

        resultAdapter.suggestList = suggestResult!!
    }

    private fun requestSuggest(query: String) {
        suggestSession!!.suggest(query, BOUNDING_BOX, SEARCH_OPTIONS, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        searchBinding = null
    }

    override fun onResponse(suggest: MutableList<SuggestItem>) {
        suggestResult = emptyList()
        suggestResult = suggest
        loadDataInRecyclerView()
    }

    override fun onError(error: Error) {
        var errorMessage = "Неизвестная ошибка"
        if (error is RemoteError) {
            errorMessage = "Ошибка при получении данных"
        } else if (error is NetworkError) {
            errorMessage = "Ошибка сети"
        }
        Log.e("SearchFragment", error.toString())
    }
}