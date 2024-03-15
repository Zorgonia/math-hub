package com.kyang.mathhub.data

data class TipUIState(
    val priceWithTax: String = "",
    val tipCalcs: List<TipPrice> = listOf(
        TipPrice("10", "", ""),
        TipPrice("15", "", ""),
        TipPrice("18", "", ""),
        TipPrice("20", "", "")
    ),
    val priceWithoutTax: String = "",
    val taxPercent: String = "13.0",
    val addingTip: Boolean = false,
    val newTip: String = ""
)

data class TipPrice(
    val tipPercent: String = "0",
    val nonTaxedCalc: String = "0",
    val taxedCalc: String = "0",
    val editing: Boolean = false
)