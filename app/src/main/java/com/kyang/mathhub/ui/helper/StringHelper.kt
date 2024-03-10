package com.kyang.mathhub.ui.helper

fun String.isInt(): Boolean {
    val regex = "-?[0-9]+".toRegex()
    return this.matches(regex)
}