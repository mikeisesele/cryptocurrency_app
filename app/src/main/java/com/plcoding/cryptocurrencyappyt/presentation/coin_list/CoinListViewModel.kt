package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.usecases.get_coins.GetCoinsUseCase
import com.plcoding.cryptocurrencyappyt.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
    ) : ViewModel(){

    private val _state = mutableStateOf<UiState<List<Coin>>>(UiState())
    val state: State<UiState<List<Coin>>> = _state

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UiState(data = result.data?: emptyList(),)
                }
                is Resource.Loading -> {
                    _state.value = UiState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = UiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}