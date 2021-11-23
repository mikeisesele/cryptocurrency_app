package com.plcoding.cryptocurrencyappyt.domain.usecases.get_coins

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.model.toCoin
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {
     operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
         try {
             emit(Resource.Loading())
             val coins = repository.getCoins().map { it.toCoin() }
             emit(Resource.Success(coins))
         } catch (e: HttpException){ // http error
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occurred"))
         } catch (e: IOException){ // bad internet
            emit(Resource.Error<List<Coin>>("Couldn't reach server, check internet connection"))
         }
     }
}