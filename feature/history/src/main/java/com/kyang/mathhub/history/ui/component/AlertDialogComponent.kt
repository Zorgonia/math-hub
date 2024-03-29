package com.kyang.mathhub.history.ui.component

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.kyang.mathhub.feature.history.R
import com.kyang.mathhub.theme.Red

@Composable
fun AlertDialogComponent(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    @StringRes title: Int,
    @StringRes text: Int,
    @StringRes confirm: Int,
    modifier: Modifier = Modifier,
    confirmButtonColor: Color = Color.Unspecified
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(stringResource(id = confirm), color = Red)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.alert_cancel))
            }
        },
        title = {
            Text(text = stringResource(id = title))
        },
        text = {
            Text(text = stringResource(id = text))
        },
    )
}
