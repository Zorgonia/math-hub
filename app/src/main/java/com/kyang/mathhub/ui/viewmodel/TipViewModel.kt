package com.kyang.mathhub.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kyang.mathhub.data.TipPrice
import com.kyang.mathhub.data.TipUIState
import com.kyang.mathhub.helper.isDouble
import com.kyang.mathhub.helper.toPriceString
import com.kyang.mathhub.repo.tip.TipRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TipViewModel @Inject constructor(
    private val tipRepository: TipRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TipUIState())
    val uiState: StateFlow<TipUIState> = _uiState.asStateFlow()

    fun setPriceWithTax(price: String) {
        if (price.isEmpty()) {
            setPricesEmpty()
        } else if (price.isDouble()) {
            calculateNewPrices(price, true)
        } else if (price.last() == '.') {
            _uiState.update { curr ->
                curr.copy(
                    priceWithTax = price,
                    taxPercent = curr.taxPercent.ifEmpty { "0" }
                )
            }
        }
    }

    fun setPriceWithoutTax(price: String) {
        if (price.isEmpty()) {
            setPricesEmpty()
        } else if (price.isDouble()) {
            calculateNewPrices(price, false)
        } else if (price.last() == '.') {
            _uiState.update { curr ->
                curr.copy(
                    priceWithoutTax = price,
                    taxPercent = curr.taxPercent.ifEmpty { "0" }
                )
            }
        }
    }

    fun setTaxPercentage(percent: String) {
        if (percent.isEmpty()) {
            _uiState.update { curr ->
                curr.copy(
                    taxPercent = percent
                )
            }
        } else if (percent.isDouble()) {
            calculateNewPrices(uiState.value.priceWithoutTax, false, percent)
        } else if (percent.last() == '.') {
            _uiState.update { curr ->
                curr.copy(
                    taxPercent = percent
                )
            }
        }
    }

    private fun setPricesEmpty() {
        _uiState.update { curr ->
            curr.copy(
                priceWithTax = "",
                priceWithoutTax = "",
                tipCalcs = curr.tipCalcs.map { TipPrice(it.tipPercent, "", "") },
            )
        }
    }

    private fun calculateNewPrices(
        price: String,
        isTaxed: Boolean,
        updatedTax: String? = null
    ) {
        _uiState.update { curr ->
            val taxPercent = updatedTax?.toDouble() ?: if (curr.taxPercent.isEmpty()) 0.0 else curr.taxPercent.toDouble()
            val nonTaxed = if (isTaxed) tipRepository.getUntaxedAmount(
                price.toDouble(),
                taxPercent
            ).toPriceString() else price
            val taxed = if (isTaxed) price else tipRepository.getTaxedAmount(
                price.toDouble(),
                taxPercent
            ).toPriceString()
            Log.d("test", "$nonTaxed $taxed")
            curr.copy(
                priceWithTax = taxed,
                priceWithoutTax = nonTaxed,
                tipCalcs = curr.tipCalcs.map {
                    TipPrice(
                        tipPercent = it.tipPercent,
                        nonTaxedCalc = tipRepository.getFinalAmount(
                            price = nonTaxed.toDouble(),
                            tipPercent = it.tipPercent.toDouble(),
                            taxPercent = taxPercent,
                            isTaxed = false
                        ).toPriceString(),
                        taxedCalc = tipRepository.getFinalAmount(
                            price = taxed.toDouble(),
                            tipPercent = it.tipPercent.toDouble(),
                            taxPercent = taxPercent,
                            isTaxed = true
                        ).toPriceString()
                    )
                },
                taxPercent = updatedTax ?: curr.taxPercent.ifEmpty { "0" }
            )
        }
    }
}