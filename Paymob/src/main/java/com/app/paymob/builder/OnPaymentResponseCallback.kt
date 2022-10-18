package com.app.paymob.builder

import com.app.paymob.helper_model.PaymentResponse

interface OnPaymentResponseCallback {
    fun onPaymentResponseCallback(paymentResponse: PaymentResponse)
}