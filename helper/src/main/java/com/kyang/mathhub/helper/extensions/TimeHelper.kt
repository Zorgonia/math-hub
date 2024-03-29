package com.kyang.mathhub.helper.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun convertToReadable(isoDateTime: String): String {
    val parsed = LocalDateTime.parse(isoDateTime)
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
    return formatter.format(parsed)
}

