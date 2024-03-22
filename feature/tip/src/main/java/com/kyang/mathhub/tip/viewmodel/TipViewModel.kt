package com.kyang.mathhub.tip.viewmodel

import androidx.lifecycle.ViewModel
import com.kyang.mathhub.domain.repo.tip.TipRepository
import com.kyang.mathhub.tip.helper.isDouble
import com.kyang.mathhub.tip.helper.toPriceString
import com.kyang.mathhub.tip.model.TipPrice
import com.kyang.mathhub.tip.model.TipUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TipViewModel @Inject constructor(
    private val tipRepository: TipRepository,
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
                    taxPercent = curr.taxPercent.ifEmpty { "0" },
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
                    taxPercent = curr.taxPercent.ifEmpty { "0" },
                )
            }
        }
    }

    fun setTaxPercentage(percent: String) {
        if (percent.isEmpty()) {
            _uiState.update { curr ->
                curr.copy(
                    taxPercent = percent,
                )
            }
        } else if (percent.isDouble()) {
            calculateNewPrices(uiState.value.priceWithoutTax, false, percent)
        } else if (percent.last() == '.') {
            _uiState.update { curr ->
                curr.copy(
                    taxPercent = percent,
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

    fun setAddingTip(adding: Boolean) {
        _uiState.update { curr ->
            curr.copy(
                addingTip = adding,
            )
        }
    }

    fun setNewTip(tip: String) {
        if (tip.isDouble()) {
            _uiState.update { curr ->
                curr.copy(
                    newTip = tip,
                )
            }
        }
    }

    fun startEditing(id: UUID) {
        _uiState.update { curr ->
            curr.copy(
                editing = true,
                addingTip = false,
                tipCalcs = curr.tipCalcs.map { tipPrice ->
                    if (tipPrice.id == id) {
                        tipPrice.copy(selected = true)
                    } else {
                        tipPrice
                    }
                },
            )
        }
    }

    fun updateSelected(id: UUID) {
        _uiState.update { curr ->
            curr.copy(
                tipCalcs = curr.tipCalcs.map { tipPrice ->
                    if (tipPrice.id == id) {
                        tipPrice.copy(selected = !tipPrice.selected)
                    } else {
                        tipPrice
                    }
                },
            )
        }
    }

    fun deleteItems() {
        _uiState.update { curr ->
            val updated = curr.tipCalcs.toMutableList()
            updated.removeIf { it.selected }
            curr.copy(
                editing = false,
                tipCalcs = updated,
            )
        }
    }

    fun cancelDelete() {
        _uiState.update { curr ->
            curr.copy(
                editing = false,
                tipCalcs = curr.tipCalcs.map { tipPrice ->
                    if (tipPrice.selected) {
                        tipPrice.copy(selected = false)
                    } else {
                        tipPrice
                    }
                },
            )
        }
    }

    fun finishAddingNewTip() {
        if (!uiState.value.newTip.isDouble()) return
        _uiState.update { curr ->
            val temp = if (curr.tipCalcs.map { it.tipPercent }.contains(curr.newTip)) {
                listOf()
            } else if (uiState.value.priceWithoutTax.isEmpty()) {
                listOf(TipPrice(tipPercent = curr.newTip, nonTaxedCalc = "", taxedCalc = ""))
            } else {
                listOf(
                    TipPrice(
                        tipPercent = curr.newTip,
                        nonTaxedCalc = tipRepository.getFinalAmount(
                            price = curr.priceWithoutTax.toDouble(),
                            tipPercent = curr.newTip.toDouble(),
                            taxPercent = curr.taxPercent.toDouble(),
                            isTaxed = false,
                        ).toPriceString(),
                        taxedCalc = tipRepository.getFinalAmount(
                            price = curr.priceWithTax.toDouble(),
                            tipPercent = curr.newTip.toDouble(),
                            taxPercent = curr.taxPercent.toDouble(),
                            isTaxed = true,
                        ).toPriceString(),
                    ),
                )
            }

            curr.copy(
                tipCalcs = (curr.tipCalcs + temp).sortedBy { it.tipPercent.toDouble() },
                newTip = "",
                addingTip = false,
            )
        }
    }

    private fun calculateNewPrices(
        price: String,
        isTaxed: Boolean,
        updatedTax: String? = null,
    ) {
        _uiState.update { curr ->
            val taxPercent = updatedTax?.toDouble()
                ?: if (curr.taxPercent.isEmpty()) 0.0 else curr.taxPercent.toDouble()
            val nonTaxed = if (isTaxed) tipRepository.getUntaxedAmount(
                price.toDouble(),
                taxPercent,
            ).toPriceString() else price
            val taxed = if (isTaxed) price else tipRepository.getTaxedAmount(
                price.toDouble(),
                taxPercent,
            ).toPriceString()
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
                            isTaxed = false,
                        ).toPriceString(),
                        taxedCalc = tipRepository.getFinalAmount(
                            price = taxed.toDouble(),
                            tipPercent = it.tipPercent.toDouble(),
                            taxPercent = taxPercent,
                            isTaxed = true,
                        ).toPriceString(),
                        id = it.id,
                        selected = it.selected,
                    )
                },
                taxPercent = updatedTax ?: curr.taxPercent.ifEmpty { "0" },
            )
        }
    }
}
