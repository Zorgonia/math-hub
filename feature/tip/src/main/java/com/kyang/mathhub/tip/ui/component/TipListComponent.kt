package com.kyang.mathhub.tip.ui.component

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kyang.mathhub.feature.tip.R
import java.util.UUID

@Composable
fun TipListComponent(
    id: UUID,
    tipPercent: String,
    withTaxPrice: String,
    withoutTaxPrice: String,
    onEditGesture: (UUID) -> Unit,
    editing: Boolean,
    selected: Boolean,
    onCheckChange: (UUID) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    onEditGesture(id)
                })
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (editing) {
            Checkbox(checked = selected, onCheckedChange = {onCheckChange(id)},
                modifier = Modifier.padding(0.dp))
        }
        Text(
            text = stringResource(id = R.string.tip_percent, tipPercent),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp),
            modifier = Modifier.padding(vertical = 12.dp)
        )

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = stringResource(id = R.string.tip_price_base, withoutTaxPrice),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(id = R.string.tip_price_taxed, withTaxPrice),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun TipListComponentPreview() {
    com.kyang.mathhub.theme.MathHubTheme {
        TipListComponent(
            tipPercent = "15",
            withoutTaxPrice = "123.24",
            withTaxPrice = "136.24",
            editing = true,
            onCheckChange = {},
            onEditGesture = {},
            id = UUID.randomUUID(),
            selected = true
        )
    }
}