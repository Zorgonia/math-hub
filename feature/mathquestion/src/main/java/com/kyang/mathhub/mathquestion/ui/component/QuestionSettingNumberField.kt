package com.kyang.mathhub.mathquestion.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun QuestionSettingNumberField(
    @StringRes title: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NumberInputTextField(
        value = value, onValueChange = onValueChange,
        label = {
            Text(stringResource(id = title))
        },
        onSubmit = { },
        modifier = modifier
            .width(100.dp)
            .wrapContentHeight(),
    )
}
