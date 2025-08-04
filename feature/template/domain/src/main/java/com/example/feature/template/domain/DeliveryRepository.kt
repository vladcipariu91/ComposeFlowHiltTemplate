package com.example.feature.template.domain

import kotlinx.coroutines.flow.Flow

interface DeliveryRepository {

    fun getDeliveries(): Flow<List<Delivery>>
}