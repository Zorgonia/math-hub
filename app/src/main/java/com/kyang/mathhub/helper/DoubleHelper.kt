package com.kyang.mathhub.helper

fun Double.toPriceString(): String {
    return "%.${2}f".format(this)
}