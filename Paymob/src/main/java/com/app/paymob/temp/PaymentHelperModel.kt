package com.app.paymob.temp
import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class PaymentHelperModel(
    var checkout_id: String? = null,
    var payment_type: HashSet<String> = hashSetOf(),
    var shopper_url: String? = null,
    var provider_mode: Boolean = true
): Serializable
