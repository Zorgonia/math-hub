package com.kyang.mathhub.helper.extensions

fun Double.toPriceString(): String {
    return "%.${2}f".format(this)
}
