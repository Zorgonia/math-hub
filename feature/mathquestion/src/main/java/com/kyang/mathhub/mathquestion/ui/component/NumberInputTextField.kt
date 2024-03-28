package com.kyang.mathhub.mathquestion.ui.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.kyang.mathhub.helper.extensions.isInt

@Composable
fun NumberInputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: () -> Unit,
    label: @Composable() (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = {
            if (it.isEmpty() || it.isInt()) {
                onValueChange(it)
            }
        },
        label = label,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go,
            keyboardType = KeyboardType.Decimal,
        ),
        keyboardActions = KeyboardActions(
            onGo = {
                onSubmit()
            },
        ),
        modifier = modifier,
    )
}
