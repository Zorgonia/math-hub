package com.kyang.mathhub.history.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.feature.history.R
import com.kyang.mathhub.helper.extensions.convertToReadable
import com.kyang.mathhub.theme.LightGreen
import com.kyang.mathhub.theme.LightRed


@Composable
fun HistoryItemRowComponent(
    onClick: (LocalMathHistoryItem) -> Unit,
    data: LocalMathHistoryItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick(data) },
    ) {
        Text(
            text = com.kyang.mathhub.helper.extensions.convertToReadable(data.timestamp),
            modifier = Modifier
                .weight(0.5f),
        )

        Text(text = data.question, modifier = Modifier.weight(0.75f), textAlign = TextAlign.Center)


        Text(
            text = data.userAnswer.ifEmpty { stringResource(id = R.string.no_answer) },
            modifier = Modifier
                .background(if (data.correct) LightGreen else LightRed)
                .weight(0.65f),
            textAlign = TextAlign.Center,
        )

        Text(
            text = data.timeUsed,
            modifier = Modifier.weight(0.25f),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun HistoryHomeHeader(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = stringResource(id = R.string.history_date_header),
            modifier = Modifier.weight(0.5f),
            textAlign = TextAlign.Center,
        )

        Text(
            text = stringResource(id = R.string.history_question_header),
            modifier = Modifier.weight(0.75f),
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(id = R.string.history_your_answer_header),
            modifier = Modifier.weight(0.65f),
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(id = R.string.history_time_used_header),
            modifier = Modifier.weight(0.25f),
            textAlign = TextAlign.Center,
        )

    }
}
