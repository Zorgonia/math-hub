package com.kyang.mathhub.domain.repo.tip

interface TipRepository {

    fun getTip(price: Double, tipPercent: Double): Double

    fun getTaxedAmount(base: Double, taxPercent: Double): Double

    fun getUntaxedAmount(taxed: Double, taxPercent: Double): Double

    fun getFinalAmount(
        price: Double,
        tipPercent: Double,
        taxPercent: Double,
        isTaxed: Boolean,
    ): Double
}
