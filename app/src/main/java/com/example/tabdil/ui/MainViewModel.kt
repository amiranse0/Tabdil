package com.example.tabdil.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabdil.data.Repository
import com.example.tabdil.data.model.Currency
import com.example.tabdil.util.ResultOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val query: Map<String, String> = mapOf()

    private val _CurrencyStateFlow: MutableStateFlow<ResultOf<List<Currency>>> =
        MutableStateFlow<ResultOf<List<Currency>>>(ResultOf.Loading)
    val currencyStateFlow = _CurrencyStateFlow

    fun getCurrencies(){
        viewModelScope.launch{
            repository.getData(query).collect{
                _CurrencyStateFlow.emit(it)
            }
        }
    }
}