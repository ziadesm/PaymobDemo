package com.app.paymob.temp
import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class PaymentHelperModel(
    var isFragment: Boolean = false,
    var checkout_id: String? = null,
    var payment_type: HashSet<String> = hashSetOf(),
    var shopper_url: String? = null,
    var provider_mode: Boolean = true,
    var color: Int? = null,
    var postalNumber: String? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var countryName: String? = null,
    var stateNumber: String? = null,
    var cityName: String? = null,
    var apartmentNumber: String? = null,
    var floorNumber: String? = null,
    var buildingNumber: String? = null,
    var lastName: String? = null,
    var maskedNumber: String? = null,
    var firstName: String? = null,
    var token: String? = null,
    var language: String? = null,
    var actionBar: Boolean? = null,
    var showSave: Boolean? = null,
    var saveCard: Boolean? = null,
    var paymentKey: String? = null,
): Serializable {
}
