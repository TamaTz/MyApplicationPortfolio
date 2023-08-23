package com.example.myapplicationportfolio.data.model

data class DataChart(
    val label: String,
    val percentage: Float,
    val data: List<Transaction>
)