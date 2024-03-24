package com.kyang.mathhub.history.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.feature.history.R
import com.kyang.mathhub.history.helper.convertToReadable
import com.kyang.mathhub.theme.LightGreen
import com.kyang.mathhub.theme.LightRed


@Composable
fun HistoryItemRowComponent(
    data: LocalMathHistoryItem,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = convertToReadable(data.timestamp))

        Text(text = data.question)


        Text(text = data.userAnswer.ifEmpty { stringResource(id = R.string.no_answer) }, modifier = Modifier
            .background(if (data.correct) LightGreen else LightRed))

//        Text(text = data.correctAnswer)

        Text(text = data.timeUsed)
    }
}

@Composable
fun HistoryHomeHeader(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = stringResource(id = R.string.history_date_header))

        Text(text = stringResource(id = R.string.history_question_header))
        Text(text = stringResource(id = R.string.history_your_answer_header))
//        Text(text = stringResource(id = R.string.history_correct_answer_header))
        Text(text = stringResource(id = R.string.history_time_used_header))

    }
}
