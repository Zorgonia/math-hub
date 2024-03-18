package com.kyang.mathhub.mathquestion.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun QuestionSettingCheckOption(
    @StringRes optionTitle: Int,
    checked: Boolean,
    onCheckChange: (Boolean) -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes inputTitle: Int,
    showOnEnabled: Boolean,
    modifier: Modifier = Modifier

) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(id = optionTitle),
        )
        Checkbox(checked = checked, onCheckedChange = onCheckChange)
    }

    if ((checked && showOnEnabled) || (!checked && !showOnEnabled)) {
        QuestionSettingNumberField(title = inputTitle, value = value, onValueChange = onValueChange)
    }
}