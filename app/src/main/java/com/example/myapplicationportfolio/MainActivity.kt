package com.example.myapplicationportfolio

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.myapplicationportfolio.ui.screen.ChartScreen
import com.example.myapplicationportfolio.ui.theme.PortfolioAppTheme
import com.example.myapplicationportfolio.ui.viewmodel.ViewChartModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioAppTheme {
                DonutChartContent()
            }
        }
    }
}

@Composable
fun DonutChartContent() {
    val chartViewModel = ViewChartModel()

    val chartColors = listOf(
        Color.parseColor("#9b5de5"),
        Color.parseColor("#f15bb5"),
        Color.parseColor("#00bbf9"),
        Color.parseColor("#ff7d00")
    )
    ChartScreen(chartViewModel, chartColors)
}