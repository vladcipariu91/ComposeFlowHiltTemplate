package com.example.feature.template

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature.template.domain.Delivery
import java.text.SimpleDateFormat

@Composable
fun DeliveriesScreen(
    modifier: Modifier = Modifier,
    viewModel: DeliveriesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Success -> DeliveriesList(deliveries = (uiState as UiState.Success).data)
        }
    }
}

@Composable
fun DeliveriesList(
    modifier: Modifier = Modifier,
    deliveries: List<Delivery>
) {
    if (deliveries.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No content yet")
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(deliveries.size) { index ->
                DeliveryItem(deliveries[index])
            }
        }
    }
}

@Composable
fun DeliveryItem(delivery: Delivery) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Order delivered at: ${delivery.time}",)
            Text(text = "This was rated as: ${delivery.starts}")
        }
    }
}

