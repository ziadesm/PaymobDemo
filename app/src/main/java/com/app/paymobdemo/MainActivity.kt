package com.app.paymobdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.app.paymob.builder.OnPaymentResponseCallback
import com.app.paymob.helper_model.PaymentResponse
import com.app.paymob.temp.PaymentHelper

class MainActivity : AppCompatActivity(), OnPaymentResponseCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PaymentHelper.Builder(this, this)
            .setPaymentKey("ZXlKaGJHY2lPaUpJVXpVeE1pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmxlSFJ5WVNJNmUzMHNJbVY0Y0NJNk1UWTJOakV3TmpNMk15d2liM0prWlhKZmFXUWlPamMxTURjM05UWTFMQ0pwYm5SbFozSmhkR2x2Ymw5cFpDSTZNamc0TVRnMk1Dd2lkWE5sY2w5cFpDSTZPRGs0TXpBd0xDSndiV3RmYVhBaU9pSTNPQzQwTmk0MU5pNHhOekFpTENKaGJXOTFiblJmWTJWdWRITWlPakV3TUN3aVkzVnljbVZ1WTNraU9pSkZSMUFpTENKaWFXeHNhVzVuWDJSaGRHRWlPbnNpWm1seWMzUmZibUZ0WlNJNkltMWhibk52ZFhKaElpd2liR0Z6ZEY5dVlXMWxJam9pYldGdFpHOTFhQ0lzSW5OMGNtVmxkQ0k2SW5OMExtMWhibk52ZFhKaElpd2lZblZwYkdScGJtY2lPaUl4TlRraUxDSm1iRzl2Y2lJNklqRXlNeUlzSW1Gd1lYSjBiV1Z1ZENJNklqZ3dNaUlzSW1OcGRIa2lPaUp0WVc1emIzVnlZU0lzSW5OMFlYUmxJam9pWkdGeGRXaHNlV0VpTENKamIzVnVkSEo1SWpvaVpXZDVjSFFpTENKbGJXRnBiQ0k2SW10aGNtbHRJaXdpY0dodmJtVmZiblZ0WW1WeUlqb2lNREV3TWpNME1UazVNRE1pTENKd2IzTjBZV3hmWTI5a1pTSTZJakUwTnpnNUlpd2laWGgwY21GZlpHVnpZM0pwY0hScGIyNGlPaUpPUVNKOUxDSnNiMk5yWDI5eVpHVnlYM2RvWlc1ZmNHRnBaQ0k2Wm1Gc2MyVjkuZEtDQkNJZ01vNkZPRXl3NHFscGtzaGw5eGFMcG9SdG5SV20tSHVUUG1JMk9hSlJBV0pHRjhtVXhRSDFEbzdJUDVITHF2eWd6Y3V2YXhiM09WR2thRFE=")
            .build()

    }

    override fun onPaymentResponseCallback(paymentResponse: PaymentResponse) {
        when(paymentResponse) {
            is PaymentResponse.PaymentSuccess -> {
                Log.e("PaymentResponse", "PaymentSuccess: >>> $paymentResponse")
            }
            is PaymentResponse.PaymentFailed -> {
                Log.e("PaymentResponse", "PaymentFailed: >>> $paymentResponse")
            }
            is PaymentResponse.PaymentCanceled -> {
                Log.e("PaymentResponse", "PaymentCanceled: >>> $paymentResponse")
            }
            is PaymentResponse.PaymentUserCanceled -> {
                Log.e("PaymentResponse", "PaymentUserCanceled: >>> $paymentResponse")
            }
        }
    }
}