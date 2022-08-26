package com.example.test2.app.ui.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test2.app.domains.DomainConvertor
import com.example.test2.app.domains.HomeRecyclerItem
import com.example.test2.app.events.NavigateToDetailsEvent
import com.example.test2.app.events.NavigationEvent
import com.example.test2.app.ui.base.BaseViewModel
import com.example.test2.persistance.database.DatabaseManager
import com.example.test2.persistance.preferences.PreferencesManager
import com.example.test2.repository.ImagesRepository
import com.example.test2.util.SingleLiveEvent
import com.example.test2.util.ifNotEmpty
import kotlinx.coroutines.launch

class HomeVM(
    private val repository: ImagesRepository,
    private val databaseManager: DatabaseManager,
    private val preferencesManager: PreferencesManager,
    private val domainConvertor: DomainConvertor
) : BaseViewModel() {

    val event = SingleLiveEvent<NavigationEvent>()
    val isLoading = MutableLiveData<Boolean>()
    val items = MutableLiveData<List<HomeRecyclerItem>?>()
    val searchText = MutableLiveData<String?>()

    init {
        loadData()
    }

    fun onSearchButtonClick() {
        searchText.value?.ifNotEmpty {
            loadData(it)
            preferencesManager.storeLastSearch(it)
        }
    }

    fun onRecyclerItemClick(item: HomeRecyclerItem) {
        item.link?.ifNotEmpty {
            event.value = NavigateToDetailsEvent(it)
        }
    }

    fun getLastSavedSearch(): String? = preferencesManager.getLastSearch()

    /**
     * if [searchString] is null or empty, load the data from the database.
     * Otherwise use the [searchString] to make a request to the API repo.
     */
    private fun loadData(searchString: String? = null) {
        if (isLoading.value == true) {
            // Data is already being loaded, no need to proceed.
            return
        }

        isLoading.value = true

        viewModelScope.launch {
            if (searchString.isNullOrEmpty()) {
                loadDataFromDatabase()
            } else {
                loadDataFromApiRepo(searchString)
            }
            isLoading.postValue(false)
        }
    }

    private suspend fun loadDataFromDatabase() {
        databaseManager.getAllItems().ifNotEmpty { dbItems ->
            items.postValue(domainConvertor.makeRecyclerItemsFromDatabase(dbItems))
        }
    }

    private suspend fun loadDataFromApiRepo(searchString: String) {
        domainConvertor.makeRecyclerItemsFromResponse(repository.getApiResponse(searchString)).ifNotEmpty { uiItems ->
            items.postValue(uiItems)
            databaseManager.deleteItems()
            databaseManager.storeItems(*domainConvertor.makeDatabaseItemsFromRecycler(uiItems).toTypedArray())
        }
    }

}