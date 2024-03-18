package com.kyang.mathhub.tip.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kyang.mathhub.feature.tip.R
import com.kyang.mathhub.theme.Black
import com.kyang.mathhub.theme.Grey
import com.kyang.mathhub.theme.Red
import com.kyang.mathhub.theme.White
import com.kyang.mathhub.tip.model.TipPrice
import com.kyang.mathhub.tip.model.TipUIState
import com.kyang.mathhub.tip.ui.component.DoubleInputTextField
import com.kyang.mathhub.tip.viewmodel.TipViewModel
import java.util.UUID

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
        modifier = Modifier.fillMaxSize(),
        onAddNewTip = { tipViewModel.setAddingTip(it) },
        isAddingNewTip = uiState.addingTip,
        newTip = uiState.newTip,
        onSetNewTip = { tipViewModel.setNewTip(it) },
        finishNewTip = { tipViewModel.finishAddingNewTip() },
        onConfirmEdits = { tipViewModel.deleteItems() },
        onCancelEdits = { tipViewModel.cancelDelete() },
        editing = uiState.editing,
        onEditGesture = { tipViewModel.startEditing(it) },
        onCheckChange = { tipViewModel.updateSelected(it) }
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
    onAddNewTip: (Boolean) -> Unit,
    isAddingNewTip: Boolean,
    newTip: String,
    onSetNewTip: (String) -> Unit,
    finishNewTip: () -> Unit,
    editing: Boolean,
    onEditGesture: (UUID) -> Unit,
    onCheckChange: (UUID) -> Unit,
    onCancelEdits: () -> Unit,
    onConfirmEdits: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            DoubleInputTextField(
                value = basePrice,
                onValueChange = onBasePriceChange,
                onSubmit = { },
                label = {
                    Text(text = stringResource(id = R.string.base_amount))
                },
                decimalPoints = 2,
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .weight(1f)
            )

            DoubleInputTextField(
                value = taxedPrice,
                onValueChange = onTaxedPriceChange,
                onSubmit = { },
                label = {
                    Text(text = stringResource(id = R.string.taxed_amount))
                },
                decimalPoints = 2,
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .weight(1f)
            )
        }


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

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.85f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(tipCalculations, key = { it.id }) { item ->
                com.kyang.mathhub.tip.ui.component.TipListComponent(
                    tipPercent = item.tipPercent,
                    withTaxPrice = item.taxedCalc,
                    withoutTaxPrice = item.nonTaxedCalc,
                    modifier = Modifier.padding(bottom = 16.dp),
                    id = item.id,
                    onEditGesture = onEditGesture,
                    editing = editing,
                    selected = item.selected,
                    onCheckChange = onCheckChange,
                )
            }
        }

        if (isAddingNewTip) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                DoubleInputTextField(
                    value = newTip,
                    onValueChange = onSetNewTip,
                    onSubmit = finishNewTip,
                    label = {
                        Text(text = stringResource(id = R.string.tip_percent_header))
                    },
                    decimalPoints = 2,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                IconButton(onClick = finishNewTip) {
                    Icon(Icons.Filled.Check, contentDescription = "check")
                }
            }
        }
        if (!isAddingNewTip && !editing) {
            Button(
                onClick = { onAddNewTip(true) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = stringResource(id = R.string.add_new_tip))
            }
        }
        if (editing) {
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Button(
                    onClick = onCancelEdits,
                    colors = ButtonColors(
                        containerColor = Grey,
                        contentColor = White,
                        disabledContainerColor = Grey,
                        disabledContentColor = Black
                    )
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }
                Spacer(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp))
                Button(
                    onClick = onConfirmEdits,
                    colors = ButtonColors(
                        containerColor = Red,
                        contentColor = White,
                        disabledContainerColor = Grey,
                        disabledContentColor = Black
                    )
                ) {
                    Text(text = stringResource(id = R.string.delete))
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun TipHomeScreenPreview() {
    com.kyang.mathhub.theme.MathHubTheme {
        TipHomeScreen(
            basePrice = "1",
            taxPercent = "13.0",
            taxedPrice = "1.13",
            tipCalculations = listOf(TipPrice("10", "1.23", "1.24")),
            onTaxPercentChange = {},
            onTaxedPriceChange = {},
            onBasePriceChange = {},
            isAddingNewTip = true,
            newTip = "12",
            onSetNewTip = {},
            onAddNewTip = {},
            finishNewTip = {},
            onCheckChange = {},
            onEditGesture = {},
            onCancelEdits = {},
            onConfirmEdits = {},
            editing = true,
            modifier = Modifier.fillMaxSize()
        )
    }
}