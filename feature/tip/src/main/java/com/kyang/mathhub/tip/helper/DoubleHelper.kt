package com.kyang.mathhub.tip.helper

fun Double.toPriceString(): String {
    return "%.${2}f".format(this)
}