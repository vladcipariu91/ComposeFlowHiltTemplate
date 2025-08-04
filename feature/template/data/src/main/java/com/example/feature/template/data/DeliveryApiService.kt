package com.example.feature.template.data

import retrofit2.Response
import retrofit2.http.GET

interface DeliveryApiService {

    @GET("interviews/time-list")
    suspend fun getDeliveries(): Response<List<DeliveryDto>>
}