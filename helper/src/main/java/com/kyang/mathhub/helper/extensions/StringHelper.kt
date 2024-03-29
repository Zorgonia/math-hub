package com.kyang.mathhub.helper.extensions

fun String.isInt(): Boolean {
    val regex = "(-?)(0|([1-9][0-9]*))".toRegex()
    return this.matches(regex)
}

fun String.isDouble(): Boolean {
    val regex = "(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?".toRegex()
    return this.matches(regex)
}
