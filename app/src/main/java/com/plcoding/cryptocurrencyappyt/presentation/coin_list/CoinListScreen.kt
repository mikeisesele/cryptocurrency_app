package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.presentation.Screen
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.components.CoinListItem

@Composable
fun CoinListScreen(
    navController: NavController,
    coinListViewModel: CoinListViewModel = hiltViewModel()
){
    val state = coinListViewModel.state.value


    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(modifier = Modifier.fillMaxSize()){
            if (state.data is List<Coin>){
                items(state.data){ coin ->
                    CoinListItem(coin = coin, onItemClick = {
                        navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                    })
                }
            }
        }
        if(state.error.isNotBlank()){
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
         if (state.isLoading){
             CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
         }
    }
}