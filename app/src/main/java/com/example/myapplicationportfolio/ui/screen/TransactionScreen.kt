package com.example.myapplicationportfolio.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationportfolio.data.model.DataChart
import com.example.myapplicationportfolio.data.model.Transaction

@Composable
fun TransactionsScreen(chartData: DataChart, onBackClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onBackClicked
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(48.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = chartData.label,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Total"
        )

        val totalAmount = chartData.data.sumBy { it.nominal }
        Text(
            text = "-Rp$totalAmount",
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            itemsIndexed(chartData.data) { index, transaction ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "${transaction.trxDate}")
                    Text(
                        text = "-Rp${transaction.nominal}",
                        textAlign = TextAlign.End, // Right justify
                        color = Color.Red,
                        modifier = Modifier.weight(1f) // Expands to fill available space
                    )
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }

    }
}

@Composable
@Preview
fun TransactionsScreenPreview() {
    val sampleChartData = DataChart(
        label = "Contoh Label",
        percentage = 50f,
        data = listOf(
            Transaction("21/08/2023", 100000),
            Transaction("20/08/2023", 50000)
        )
    )

    TransactionsScreen(sampleChartData, onBackClicked = {})
}
