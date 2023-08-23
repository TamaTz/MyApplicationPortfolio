package com.example.myapplicationportfolio.ui.screen

import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplicationportfolio.data.model.DataChart
import com.example.myapplicationportfolio.ui.viewmodel.ViewChartModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

@Composable
fun ChartView(
    chartDataList: List<DataChart>,
    chartColors: List<Int>,
    viewModel: ViewChartModel
){
    val entries = chartDataList.mapIndexed{index, dataChart ->
        PieEntry(dataChart.percentage, dataChart.label)
    }

    val pieDataSet = PieDataSet(entries, null)
    pieDataSet.setDrawValues(true)
    pieDataSet.colors = chartColors

    val pieData = PieData(pieDataSet)
    pieData.setValueFormatter(PercentFormatter())

    pieDataSet.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            return "${value.toInt()}%"
        }
    }

    pieDataSet.valueTextSize = 16f

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "My Portfolio",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.TopCenter).padding(50.dp)
        )
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            PieChart(context).apply {
                description.isEnabled = false
                data = pieData
                holeRadius = 50f
                setHoleColor(Color.TRANSPARENT)
                legend.isEnabled = true
                animateY(1400)

                legend.apply {
                    isEnabled = true
                    verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                    horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                    setDrawInside(false)
                    yOffset = 80f
                }

                setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                    override fun onValueSelected(e: Entry?, h: Highlight?) {
                        if (e != null && e is PieEntry) {
                            val selectedChartData = chartDataList.find { it.label == e.label }
                            selectedChartData?.let {
                                viewModel.onChartDataClicked(selectedChartData)
                            }
                        }
                    }

                    override fun onNothingSelected() {

                    }

                })
            }

        }
    )
}

@Composable
fun ChartScreen(viewModel: ViewChartModel, chartColors: List<Int>) {
    val chartDataList = viewModel.chartDataList
    val selectedChartData = viewModel.selectedChartData.observeAsState()

    if (selectedChartData.value != null) {
        TransactionsScreen(
            chartData = selectedChartData.value!!,
            onBackClicked = { viewModel.clearSelectedChartData() }
        )
    } else {
        ChartView(chartDataList, chartColors, viewModel)
    }
}

@Preview
@Composable
fun PreviewChartScreen() {
    val viewModel = ViewChartModel()
    val chartColors = listOf(
        Color.parseColor("#9b5de5"),
        Color.parseColor("#f15bb5"),
        Color.parseColor("#00bbf9"),
        Color.parseColor("#ff7d00")
    )

    ChartScreen(viewModel, chartColors)
}
