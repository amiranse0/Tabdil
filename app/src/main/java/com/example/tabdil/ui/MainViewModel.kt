package com.example.tabdil.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabdil.data.Repository
import com.example.tabdil.data.model.local.LocalCurrency
import com.example.tabdil.util.ResultOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val query: Map<String, String> = mapOf()

    private val _CurrencyStateFlow: MutableStateFlow<ResultOf<List<LocalCurrency>>> =
        MutableStateFlow<ResultOf<List<LocalCurrency>>>(ResultOf.Loading)
    val currencyStateFlow = _CurrencyStateFlow

    private val _FavoritesNameLiveData: MutableLiveData<List<String>> = MutableLiveData()
    val favoriteNamesLiveData = _FavoritesNameLiveData

    private val _offlineLiveData: MutableLiveData<List<LocalCurrency>> = MutableLiveData()
    val offlineLiveData = _offlineLiveData

    private var job: Job? = null
    fun saveDataOnLocal() {
        job?.cancel()
        job = viewModelScope.launch {
            repository.getData(query).collect {
                _CurrencyStateFlow.emit(it)
            }
        }
    }

    fun getOfflineData(){
        viewModelScope.launch {
            _offlineLiveData.postValue(repository.getDataFromLocal().first())
        }
    }

    fun updateFavoriteOrUnfavorite(currency: LocalCurrency){
        viewModelScope.launch {
            repository.favoriteUnfavoriteCurrency(currency)
        }
    }

    fun updatePinOrUnpin(currency: LocalCurrency){
        viewModelScope.launch {
            repository.pinUnpinCurrency(currency)
        }
    }

    fun getFavoritesName(){
        viewModelScope.launch {
            _FavoritesNameLiveData.postValue(repository.getFavoritesName())
        }
    }

}