package com.app.paymobdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.paymob.builder.OnPaymentResponseCallback
import com.app.paymob.helper_model.PaymentResponse
import com.app.paymob.temp.PaymentHelper

class MainActivity : AppCompatActivity(), OnPaymentResponseCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PaymentHelper.Builder(this, this)
            .build()

    }

    override fun onPaymentResponseCallback(paymentResponse: PaymentResponse) {

    }
}