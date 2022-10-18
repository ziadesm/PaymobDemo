package com.app.paymob.helper_model

import java.io.Serializable

data class PaymentErrorModel(
    val error_code: String,
    val error_info: String?,
    val error_message: String?,
): Serializable
