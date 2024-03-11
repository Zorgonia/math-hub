package com.kyang.mathhub.helper

fun String.isInt(): Boolean {
    val regex = "-?[0-9]+".toRegex()
    return this.matches(regex)
}