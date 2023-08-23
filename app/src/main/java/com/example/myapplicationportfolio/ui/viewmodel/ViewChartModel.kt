package com.example.myapplicationportfolio.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationportfolio.data.model.DataChart
import com.example.myapplicationportfolio.data.model.Transaction

class ViewChartModel : ViewModel() {

    private val rawChartData: List<DataChart> = listOf(
        DataChart(
            label = "Tarik Tunai",
            percentage = 55f,
            data = listOf(
                Transaction("21/01/2023", 1000000),
                Transaction("20/01/2023", 500000),
                Transaction("19/01/2023", 1000000)
            )
        ),
        DataChart(
            label = "QRIS Payment",
            percentage = 31f,
            data = listOf(
                Transaction("21/01/2023", 159000),
                Transaction("20/01/2023", 35000),
                Transaction("19/01/2023", 1500)
            )
        ),
        DataChart(
            label = "Topup Gopay",
            percentage = 7.7f,
            data = listOf(
                Transaction("21/01/2023", 200000),
                Transaction("20/01/2023", 195000),
                Transaction("19/01/2023", 5000000)
            )
        ),
        DataChart(
            label = "Lainnya",
            percentage = 6.3f,
            data = listOf(
                Transaction("21/01/2023", 1000000),
                Transaction("20/01/2023", 500000),
                Transaction("19/01/2023", 1000000)
            )
        )
    )

    val chartDataList: List<DataChart> = rawChartData

    private val _selectedChartData = MutableLiveData<DataChart?>()
    val selectedChartData: LiveData<DataChart?> = _selectedChartData

    fun onChartDataClicked(chartData: DataChart) {
        _selectedChartData.value = chartData
    }

    fun clearSelectedChartData() {
        _selectedChartData.value = null
    }

}