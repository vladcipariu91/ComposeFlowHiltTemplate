package com.example.feature.template.data

import kotlinx.serialization.Serializable

@Serializable
data class DeliveryDto(
    val id: String,
    val time: Long,
    val stars: Int,
)