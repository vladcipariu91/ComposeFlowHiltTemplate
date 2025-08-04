package com.example.feature.template.data

import com.example.feature.template.domain.Delivery
import com.example.feature.template.domain.DeliveryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeliveryRepositoryImpl @Inject constructor(private val deliveryApiService: DeliveryApiService) :
    DeliveryRepository {
    override fun getDeliveries(): Flow<List<Delivery>> =
        flow {
            val body = deliveryApiService.getDeliveries().body()
            if (body != null) {
                emit(
                    body.map { it.map() }
                )
            } else {
                emit(
                    emptyList()
                )
            }
        }.flowOn(Dispatchers.IO)
}