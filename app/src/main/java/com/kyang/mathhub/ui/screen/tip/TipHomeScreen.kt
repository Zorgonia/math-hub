package com.kyang.mathhub.ui.screen.tip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kyang.mathhub.R
import com.kyang.mathhub.data.TipPrice
import com.kyang.mathhub.data.TipUIState
import com.kyang.mathhub.ui.components.DoubleInputTextField
import com.kyang.mathhub.ui.components.TipListComponent
import com.kyang.mathhub.ui.theme.MathHubTheme
import com.kyang.mathhub.ui.viewmodel.TipViewModel

@Composable
fun TipHomePage(
    uiState: TipUIState,
    tipViewModel: TipViewModel,
    tipNavController: NavHostController
) {
    TipHomeScreen(
        basePrice = uiState.priceWithoutTax,
        onBasePriceChange = { tipViewModel.setPriceWithoutTax(it) },
        taxedPrice = uiState.priceWithTax,
        onTaxedPriceChange = { tipViewModel.setPriceWithTax(it) },
        taxPercent = uiState.taxPercent,
        onTaxPercentChange = { tipViewModel.setTaxPercentage(it) },
        tipCalculations = uiState.tipCalcs,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun TipHomeScreen(
    basePrice: String,
    onBasePriceChange: (String) -> Unit,
    taxedPrice: String,
    onTaxedPriceChange: (String) -> Unit,
    taxPercent: String,
    onTaxPercentChange: (String) -> Unit,
    tipCalculations: List<TipPrice>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        DoubleInputTextField(
            value = basePrice,
            onValueChange = onBasePriceChange,
            onSubmit = { },
            label = {
                Text(text = stringResource(id = R.string.base_amount))
            },
            decimalPoints = 2,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        DoubleInputTextField(
            value = taxedPrice,
            onValueChange = onTaxedPriceChange,
            onSubmit = { },
            label = {
                Text(text = stringResource(id = R.string.taxed_amount))
            },
            decimalPoints = 2,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        DoubleInputTextField(
            value = taxPercent,
            onValueChange = onTaxPercentChange,
            onSubmit = { },
            label = {
                Text(text = stringResource(id = R.string.tax_percent))
            },
            decimalPoints = 2,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(tipCalculations) { item ->
                TipListComponent(
                    tipPercent = item.tipPercent,
                    withTaxPrice = item.taxedCalc,
                    withoutTaxPrice = item.nonTaxedCalc,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TipHomeScreenPreview() {
    MathHubTheme {
        TipHomeScreen(
            basePrice = "1",
            taxPercent = "13.0",
            taxedPrice = "1.13",
            tipCalculations = listOf(TipPrice("10", "1.23", "1.24")),
            onTaxPercentChange = {},
            onTaxedPriceChange = {},
            onBasePriceChange = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}