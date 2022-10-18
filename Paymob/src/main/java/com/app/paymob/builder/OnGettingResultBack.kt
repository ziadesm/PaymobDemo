package com.app.paymob.builder
import android.content.Intent

interface OnGettingResultBack {
    fun onGettingResult(request: Int, data: Intent?)
}