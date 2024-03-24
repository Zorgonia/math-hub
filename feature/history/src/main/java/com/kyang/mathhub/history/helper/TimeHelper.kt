package com.kyang.mathhub.history.helper

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun convertToReadable(isoDateTime: String): String {
    val parsed = LocalDateTime.parse(isoDateTime)
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
    return formatter.format(parsed)
}

