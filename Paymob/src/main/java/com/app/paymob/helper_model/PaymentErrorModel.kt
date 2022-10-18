package com.app.paymob.helper_model

import java.io.Serializable

data class PaymentErrorModel(
    val error: String? = null,
    val notSuccess: String? = null,
    val orderId: String? = null
): Serializable
