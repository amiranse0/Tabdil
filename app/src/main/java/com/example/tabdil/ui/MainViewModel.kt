package com.example.tabdil.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabdil.data.Repository
import com.example.tabdil.data.model.local.LocalCurrency
import com.example.tabdil.data.model.remote.Currency
import com.example.tabdil.util.ResultOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val query: Map<String, String> = mapOf()

    private val _CurrencyStateFlow: MutableStateFlow<ResultOf<List<LocalCurrency>>> =
        MutableStateFlow<ResultOf<List<LocalCurrency>>>(ResultOf.LoadingEmptyLocal)
    val currencyStateFlow = _CurrencyStateFlow

    private var job: Job? = null
    fun getCurrencies() {
        job?.cancel()
        job = viewModelScope.launch {
            repository.getData(query).collect {
                _CurrencyStateFlow.emit(it)
            }
        }
    }
}