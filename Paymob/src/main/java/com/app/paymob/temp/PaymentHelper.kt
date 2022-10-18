package com.app.paymob.temp
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.app.paymob.builder.OnGettingResultBack
import com.app.paymob.builder.OnPaymentResponseCallback
import com.app.paymob.builder.PaymentBuilder
import com.app.paymob.utils.StartingActivityResult
import com.paymob.acceptsdk.PayActivity
import com.paymob.acceptsdk.PayActivityIntentKeys

class PaymentHelper: PaymentBuilder, OnGettingResultBack {
    private constructor(activity: AppCompatActivity, paymentCallback: OnPaymentResponseCallback) {
        mContext = activity
        mPaymentModel = PaymentHelperModel()
        mPaymentResponse = paymentCallback
        createStartingActivity(activity)
    }
    private constructor(fragment: FragmentActivity, paymentCallback: OnPaymentResponseCallback) {
        mContext = fragment
        mPaymentModel = PaymentHelperModel()
        mPaymentResponse = paymentCallback
        createStartingFragment(fragment)
    }

    private var mContext: Context? = null
    private var mPaymentModel: PaymentHelperModel? = null
    private var mStartPayment: StartingActivityResult? = null
    private var mPaymentResponse: OnPaymentResponseCallback? = null
    override fun saveCardDefault(saveCard: Boolean): PaymentBuilder {
        return this
    }

    override fun showSavedCards(showSaved: Boolean): PaymentBuilder {
        return this
    }

    override fun showActionBar(show: Boolean): PaymentBuilder {
        return this
    }

    override fun language(language: String): PaymentBuilder {
        return this
    }

    override fun themeColor(color: Int): PaymentBuilder {
        return this
    }

    override fun token(token: String): PaymentBuilder {
        return this
    }

    override fun maskedPanNumber(mask: String): PaymentBuilder {
        return this
    }

    override fun setFirstName(firstName: String): PaymentBuilder {
        return this
    }

    override fun setLastName(lastName: String): PaymentBuilder {
        return this
    }

    override fun setBuildingNumber(buildingNumber: String): PaymentBuilder {
        return this
    }

    override fun setFloorNumber(floorNumber: String): PaymentBuilder {
        return this
    }

    override fun setApartmentNumber(floorNumber: String): PaymentBuilder {
        return this
    }

    override fun setCityName(floorNumber: String): PaymentBuilder {
        return this
    }

    override fun setStateName(stateNumber: String): PaymentBuilder {
        return this
    }

    override fun setCountry(countryName: String): PaymentBuilder {
        return this
    }

    override fun setEmail(email: String): PaymentBuilder {
        return this
    }

    override fun setPhoneNumber(phoneNumber: String): PaymentBuilder {
        return this
    }

    override fun setPostalNumber(postalNumber: String): PaymentBuilder {
        return this
    }

    override fun testMode(testMode: Boolean): PaymentBuilder {
        //mPaymentModel?.provider_mode = if (testMode) Connect.ProviderMode.TEST else Connect.ProviderMode.LIVE
        return this
    }

    val paymentKey =
        ""
    override fun build() {
        val pay_intent = Intent(mContext, PayActivity::class.java)
        pay_intent.putExtra(PayActivityIntentKeys.PAYMENT_KEY, paymentKey)
        pay_intent.putExtra(PayActivityIntentKeys.THREE_D_SECURE_ACTIVITY_TITLE, "Verification")
        pay_intent.putExtra(PayActivityIntentKeys.SAVE_CARD_DEFAULT, false)
        pay_intent.putExtra(PayActivityIntentKeys.SHOW_SAVE_CARD, false)
        pay_intent.putExtra("ActionBar", false)
        pay_intent.putExtra("language", "ar")

        mStartPayment?.launchResultActivity(pay_intent)
    }

    private fun createStartingActivity(activity: AppCompatActivity) {
        mStartPayment = StartingActivityResult(
            this@PaymentHelper
        )
        mStartPayment?.initActivityResult(activity)
    }
    private fun createStartingFragment(fragment: FragmentActivity) {
        mStartPayment = StartingActivityResult(
            this@PaymentHelper
        )
        mStartPayment?.initFragmentResult(fragment)
    }

    companion object {
        const val ACCEPT_PAYMENT_REQUEST = 1555
        fun Builder(activity: AppCompatActivity, paymentCallback: OnPaymentResponseCallback): PaymentHelper {
            return PaymentHelper(activity, paymentCallback)
        }
        fun Builder(fragment: FragmentActivity, paymentCallback: OnPaymentResponseCallback): PaymentHelper {
            return PaymentHelper(fragment, paymentCallback)
        }
    }

    override fun onGettingResult(request: Int, data: Intent?) {
        /*when(request) {

        }*/
    }
}