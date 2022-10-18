package com.app.paymob.helper_model

import java.io.Serializable

data class PaymentSuccessModel(
    val resourcePath: String?,
    val payResponse: String? = null,
    val orderId: String? = null,
): Serializable
