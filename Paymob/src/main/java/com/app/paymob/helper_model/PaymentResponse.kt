package com.app.paymob.helper_model

sealed class PaymentResponse {
    data class PaymentSuccess(val transaction: PaymentSuccessModel?, val threeDResource: String?): PaymentResponse()
    data class PaymentFailed(val paymentError: PaymentErrorModel?): PaymentResponse()
    object PaymentCanceled: PaymentResponse()
    data class PaymentUserCanceled(val cancel: String?): PaymentResponse()
}