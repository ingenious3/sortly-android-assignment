package com.example.sortly.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sortly.data.datasource.network.NetworkConstants.DEFAULT_ERROR
import com.example.sortly.data.datasource.network.NetworkConstants.INITIAL_PAGE
import com.example.sortly.data.datasource.network.ResultData
import com.example.sortly.data.model.Items
import com.example.sortly.domain.GetItemsUseCase
import com.example.sortly.ui.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(private val getItemsUseCase: GetItemsUseCase): ViewModel() {

    private val _items: MutableLiveData<UiStates> = MutableLiveData(UiStates.Loading(true))
    var items: LiveData<UiStates> = _items

    private val cachedList = mutableListOf<Items>()
    private val TYPE_FOLDER = "folder"
    private var currentPage = INITIAL_PAGE
    private var totalPages = INITIAL_PAGE

    fun fetchItems() {
        viewModelScope.launch {
            getItemsUseCase.invoke(currentPage).collect { apiResponse ->
                when (apiResponse) {
                    is ResultData.Success -> {
                        currentPage += 1
                        totalPages = apiResponse.data.meta?.totalPages ?: INITIAL_PAGE
                        val items = apiResponse.data.dataJson ?: emptyList()
                        if (items.isNullOrEmpty().not()) {
                            cachedList.addAll(items)
                            _items.value = UiStates.Success(cachedList.sortedWith(compareBy { if (it.type == TYPE_FOLDER) 0 else 1 }))
                            loadMoreData()
                        }
                    }
                    is ResultData.Failure -> {
                        UiStates.Error(apiResponse.msg)
                    }
                    is ResultData.Exception -> {
                        UiStates.Error(apiResponse.throwable?.message ?: DEFAULT_ERROR)
                    }
                    is ResultData.Loading -> {
                        UiStates.Loading(true)
                    }

                }
            }
        }
    }

    fun isDataCached(): Boolean = cachedList.isNotEmpty()

    private fun shouldLoadMore(): Boolean = currentPage <= totalPages

    private fun loadMoreData() {
        if (shouldLoadMore()) {
            fetchItems()
        }
    }

}