package com.plcoding.cryptocurrencyappyt.presentation.coin_detail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.common.constants.PARAM_COIN_ID
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.usecases.get_coin.GetCoinUseCase
import com.plcoding.cryptocurrencyappyt.domain.usecases.get_coins.GetCoinsUseCase
import com.plcoding.cryptocurrencyappyt.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
    ) : ViewModel(){

    private val _state = mutableStateOf<UiState<CoinDetail>>(UiState())
    val state: State<UiState<CoinDetail>> = _state

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let{
            getCoinDetail(it)
        }
     }

    private fun getCoinDetail(coinId: String){
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UiState(data = result.data)
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