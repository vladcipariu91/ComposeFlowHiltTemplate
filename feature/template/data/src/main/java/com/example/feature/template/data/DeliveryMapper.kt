package com.example.feature.template.data

import com.example.feature.template.domain.Delivery
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun DeliveryDto.map(): Delivery =
    Delivery(
        id = id,
        time = DATE_FORMAT.format(Date(time * 1000)),
        starts = stars
    )

private val DATE_FORMAT = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())