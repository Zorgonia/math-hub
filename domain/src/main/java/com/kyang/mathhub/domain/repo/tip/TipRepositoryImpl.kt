package com.kyang.mathhub.domain.repo.tip

import javax.inject.Inject


class TipRepositoryImpl @Inject constructor(): TipRepository {
    override fun getTip(price: Double, tipPercent: Double): Double {
        return price * (tipPercent / 100)
    }

    override fun getTaxedAmount(base: Double, taxPercent: Double): Double {
        return base * (1 + (taxPercent / 100))
    }

    override fun getUntaxedAmount(taxed: Double, taxPercent: Double): Double {
        return taxed / (1 + (taxPercent / 100))
    }

    override fun getFinalAmount(
        price: Double,
        tipPercent: Double,
        taxPercent: Double,
        isTaxed: Boolean
    ): Double {
        val subtotal = if (isTaxed) price else getTaxedAmount(price, taxPercent)
        return subtotal + getTip(price, tipPercent)
    }
}