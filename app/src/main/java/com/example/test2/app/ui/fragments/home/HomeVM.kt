package com.example.test2.app.ui.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test2.app.domains.DomainConvertor
import com.example.test2.app.domains.HomeRecyclerItem
import com.example.test2.app.events.NavigateToDetailsEvent
import com.example.test2.app.events.NavigationEvent
import com.example.test2.app.ui.base.BaseViewModel
import com.example.test2.repository.ImagesRepository
import com.example.test2.util.SingleLiveEvent
import kotlinx.coroutines.launch

class HomeVM(
    private val repository: ImagesRepository,
    private val domainConvertor: DomainConvertor
) : BaseViewModel() {

    val event = SingleLiveEvent<NavigationEvent>()
    val searchText = MutableLiveData<String?>()
    val items = MutableLiveData<List<HomeRecyclerItem>?>(null)
    val isLoading = MutableLiveData(false)

    init {
        // TODO: Restore persisted items
    }

    fun onSearchButtonClick() {
        searchText.value?.ifEmpty { null }?.let { keyword ->
            isLoading.value = true

            viewModelScope.launch {
                items.postValue(domainConvertor.getRecyclerItemsFromResponse(repository.getApiResponse(keyword)))
                isLoading.postValue(false)

                // TODO: Persist items
            }
        }
    }

    fun onItemClick(item: HomeRecyclerItem) {
        item.link?.let {
            event.value = NavigateToDetailsEvent(it)
        }
    }

}