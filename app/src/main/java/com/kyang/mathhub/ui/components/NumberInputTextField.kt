package com.kyang.mathhub.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.kyang.mathhub.helper.isInt

@Composable
fun NumberInputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: () -> Unit,
    label: @Composable() (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = {
            if (it.isInt()) {
                onValueChange(it)
            }
        },
        label = label,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go,
            keyboardType = KeyboardType.Decimal
        ),
        keyboardActions = KeyboardActions(onGo = {
            onSubmit()
        }),
        modifier = modifier
    )
}

@Composable
fun DoubleInputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: () -> Unit,
    label: @Composable() (() -> Unit)?,
    decimalPoints: Int,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = { str ->
            if (str.contains('.') && str.length - (str.indexOf('.')) > decimalPoints + 1 ) {
                return@TextField
            }
            if (str.isInt() || str.count{ char -> char == '.'} <= 1) {
                if (str.length > 1 && str[0] == '0' && str[1] != '.') {
                    onValueChange(str.substring(1))
                } else {
                    onValueChange(str)
                }
            }
        },
        label = label,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(onGo = {
            onSubmit()
        }),
        modifier = modifier
    )
}