package com.app.paymob.temp
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.app.paymob.builder.OnGettingResultBack
import com.app.paymob.builder.OnPaymentResponseCallback
import com.app.paymob.builder.PaymentBuilder
import com.app.paymob.helper_model.PaymentErrorModel
import com.app.paymob.helper_model.PaymentResponse
import com.app.paymob.helper_model.PaymentSuccessModel
import com.app.paymob.utils.StartingActivityResult
import com.paymob.acceptsdk.*
import java.io.IOException

class PaymentHelper: PaymentBuilder, OnGettingResultBack {
    private constructor(activity: AppCompatActivity, paymentCallback: OnPaymentResponseCallback) {
        mContext = activity
        mPaymentModel = PaymentHelperModel()
        mPaymentResponse = paymentCallback
        createStartingActivity(activity)
    }
    private constructor(fragment: Fragment, paymentCallback: OnPaymentResponseCallback) {
        mContext = fragment.requireContext()
        mPaymentModel = PaymentHelperModel()
        mPaymentModel?.isFragment = true
        mPaymentResponse = paymentCallback
        createStartingFragment(fragment)
    }

    private var mContext: Context? = null
    private var mPaymentModel: PaymentHelperModel? = null
    private var mStartPayment: StartingActivityResult? = null
    private var mPaymentResponse: OnPaymentResponseCallback? = null
    override fun saveCardDefault(saveCard: Boolean): PaymentBuilder {
        mPaymentModel?.saveCard = saveCard
        return this
    }

    override fun showSavedCards(showSaved: Boolean): PaymentBuilder {
        mPaymentModel?.showSave = showSaved
        return this
    }

    override fun showActionBar(show: Boolean): PaymentBuilder {
        mPaymentModel?.actionBar = show
        return this
    }

    override fun language(language: String): PaymentBuilder {
        mPaymentModel?.language = language
        return this
    }

    override fun themeColor(color: Int): PaymentBuilder {
        mPaymentModel?.color = color
        return this
    }

    override fun token(token: String): PaymentBuilder {
        mPaymentModel?.token = token
        return this
    }

    override fun maskedPanNumber(mask: String): PaymentBuilder {
        mPaymentModel?.maskedNumber = mask
        return this
    }

    override fun setFirstName(firstName: String): PaymentBuilder {
        mPaymentModel?.firstName = firstName
        return this
    }

    override fun setLastName(lastName: String): PaymentBuilder {
        mPaymentModel?.lastName = lastName
        return this
    }

    override fun setBuildingNumber(buildingNumber: String): PaymentBuilder {
        mPaymentModel?.buildingNumber = buildingNumber
        return this
    }

    override fun setFloorNumber(floorNumber: String): PaymentBuilder {
        mPaymentModel?.floorNumber = floorNumber
        return this
    }

    override fun setApartmentNumber(floorNumber: String): PaymentBuilder {
        mPaymentModel?.apartmentNumber = floorNumber
        return this
    }

    override fun setCityName(floorNumber: String): PaymentBuilder {
        mPaymentModel?.cityName = floorNumber
        return this
    }

    override fun setPaymentKey(paymentKey: String): PaymentBuilder {
        mPaymentModel?.paymentKey = paymentKey
        return this
    }

    override fun setStateName(stateNumber: String): PaymentBuilder {
        mPaymentModel?.stateNumber = stateNumber
        return this
    }

    override fun setCountry(countryName: String): PaymentBuilder {
        mPaymentModel?.countryName = countryName
        return this
    }

    override fun setEmail(email: String): PaymentBuilder {
        mPaymentModel?.email = email
        return this
    }

    override fun setPhoneNumber(phoneNumber: String): PaymentBuilder {
        mPaymentModel?.phoneNumber = phoneNumber
        return this
    }

    override fun setPostalNumber(postalNumber: String): PaymentBuilder {
        mPaymentModel?.postalNumber = postalNumber
        return this
    }

    override fun testMode(testMode: Boolean): PaymentBuilder {
        //mPaymentModel?.provider_mode = if (testMode) Connect.ProviderMode.TEST else Connect.ProviderMode.LIVE
        return this
    }

    override fun build() {
        val payIntent = Intent(mContext, PayActivity::class.java)
        mPaymentModel?.paymentKey?.let { payIntent.putExtra(PayActivityIntentKeys.PAYMENT_KEY, mPaymentModel?.paymentKey) } ?: throw IOException("Need Payment Key !!")
        payIntent.putExtra(PayActivityIntentKeys.THREE_D_SECURE_ACTIVITY_TITLE, "Verification")
        payIntent.putExtra(PayActivityIntentKeys.SAVE_CARD_DEFAULT, mPaymentModel?.saveCard ?: false)
        payIntent.putExtra(PayActivityIntentKeys.SHOW_SAVE_CARD, mPaymentModel?.showSave ?: false)
        payIntent.putExtra("ActionBar", mPaymentModel?.actionBar ?: false)
        payIntent.putExtra("language", mPaymentModel?.language ?: "ar")

        mPaymentModel?.token?.let { payIntent.putExtra(PayActivityIntentKeys.TOKEN, mPaymentModel?.token) }
        payIntent.putExtra(PayActivityIntentKeys.MASKED_PAN_NUMBER, mPaymentModel?.maskedNumber ?: "xxxx-xxxx-xxxx-1234")
        mPaymentModel?.color?.let {
            payIntent.putExtra(PayActivityIntentKeys.THEME_COLOR,
            mContext?.let { it1 -> ContextCompat.getColor(it1, it) })
        }

        mPaymentModel?.firstName?.let { payIntent.putExtra(PayActivityIntentKeys.FIRST_NAME, it) }
        mPaymentModel?.lastName?.let { payIntent.putExtra(PayActivityIntentKeys.LAST_NAME, it) }
        mPaymentModel?.buildingNumber?.let { payIntent.putExtra(PayActivityIntentKeys.BUILDING, it) }
        mPaymentModel?.floorNumber?.let { payIntent.putExtra(PayActivityIntentKeys.FLOOR, it) }
        mPaymentModel?.apartmentNumber?.let { payIntent.putExtra(PayActivityIntentKeys.APARTMENT, it) }
        mPaymentModel?.cityName?.let { payIntent.putExtra(PayActivityIntentKeys.CITY, it) }
        mPaymentModel?.stateNumber?.let { payIntent.putExtra(PayActivityIntentKeys.STATE, it) }
        mPaymentModel?.countryName?.let { payIntent.putExtra(PayActivityIntentKeys.COUNTRY, it) }
        mPaymentModel?.email?.let { payIntent.putExtra(PayActivityIntentKeys.EMAIL, it) }
        mPaymentModel?.phoneNumber?.let { payIntent.putExtra(PayActivityIntentKeys.PHONE_NUMBER, it) }
        mPaymentModel?.postalNumber?.let { payIntent.putExtra(PayActivityIntentKeys.POSTAL_CODE, it) }

        if (mPaymentModel?.isFragment == true) mStartPayment?.launchResultFragment(payIntent)
        else mStartPayment?.launchResultActivity(payIntent)
        val secureIntent = Intent(mContext, ThreeDSecureWebViewActivty::class.java)
        secureIntent.putExtra("ActionBar", false)
    }

    private fun createStartingActivity(activity: AppCompatActivity) {
        mStartPayment = StartingActivityResult(
            this@PaymentHelper
        )
        mStartPayment?.initActivityResult(activity)
    }
    private fun createStartingFragment(fragment: Fragment) {
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
        fun Builder(fragment: Fragment, paymentCallback: OnPaymentResponseCallback): PaymentHelper {
            return PaymentHelper(fragment, paymentCallback)
        }
    }

    override fun onGettingResult(resultCode: Int, data: Intent?) {
        val extras = data?.extras

        when (resultCode) {
            IntentConstants.USER_CANCELED -> {
                // User canceled and did no payment request was fired
                mPaymentResponse?.onPaymentResponseCallback(PaymentResponse.PaymentCanceled)
                Log.e("PaymentResponseInside", "USER_CANCELED: >>>>> ")
            }
            IntentConstants.MISSING_ARGUMENT -> {
                // You forgot to pass an important key-value pair in the intent's extras
                mPaymentResponse?.onPaymentResponseCallback(
                    PaymentResponse.PaymentFailed(
                        PaymentErrorModel(
                            extras?.getString(IntentConstants.MISSING_ARGUMENT_VALUE),
                            extras?.getString(PayResponseKeys.DATA_MESSAGE),
                            extras?.getString(PayResponseKeys.ORDER),
                        )
                    ))
                Log.e("PaymentResponseInside", "MISSING_ARGUMENT: >>>>> ${extras?.getString(IntentConstants.MISSING_ARGUMENT_VALUE)}")
                Log.e("PaymentResponseInside", "MISSING_ARGUMENT: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "MISSING_ARGUMENT: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "MISSING_ARGUMENT: >>>>> ${extras?.getString(PayResponseKeys.DATA_MESSAGE)}")
                Log.e("PaymentResponseInside", "MISSING_ARGUMENT: >>>>> ${extras?.getString(PayResponseKeys.ORDER)}")
                Log.e("PaymentResponseInside", "MISSING_ARGUMENT: >>>>> ${extras?.getString(PayResponseKeys.SUCCESS)}")
            }
            IntentConstants.TRANSACTION_ERROR -> {
                // An error occurred while handling an API's response
                mPaymentResponse?.onPaymentResponseCallback(
                    PaymentResponse.PaymentFailed(
                        PaymentErrorModel(
                            extras?.getString(IntentConstants.TRANSACTION_ERROR_REASON),
                            extras?.getString(PayResponseKeys.DATA_MESSAGE),
                            extras?.getString(PayResponseKeys.ORDER),
                        )
                    ))
                Log.e("PaymentResponseInside", "TRANSACTION_ERROR: >>>>> ${extras?.getString(IntentConstants.MISSING_ARGUMENT_VALUE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_ERROR: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_ERROR: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_ERROR: >>>>> ${extras?.getString(PayResponseKeys.DATA_MESSAGE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_ERROR: >>>>> ${extras?.getString(PayResponseKeys.ORDER)}")
                Log.e("PaymentResponseInside", "TRANSACTION_ERROR: >>>>> ${extras?.getString(PayResponseKeys.SUCCESS)}")
            }
            IntentConstants.TRANSACTION_REJECTED -> {
                // User attempted to pay but their transaction was rejected
                // Use the static keys declared in PayResponseKeys to extract the fields you want
                mPaymentResponse?.onPaymentResponseCallback(
                    PaymentResponse.PaymentFailed(
                        PaymentErrorModel(
                            extras?.getString(PayResponseKeys.DATA_MESSAGE),
                            extras?.getString(PayResponseKeys.CAPTURED_AMOUNT),
                            extras?.getString(PayResponseKeys.ORDER),
                        )
                    ))
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED: >>>>> ${extras?.getString(IntentConstants.MISSING_ARGUMENT_VALUE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED: >>>>> ${extras?.getString(PayResponseKeys.DATA_MESSAGE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED: >>>>> ${extras?.getString(PayResponseKeys.ORDER)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED: >>>>> ${extras?.getString(PayResponseKeys.SUCCESS)}")
            }
            IntentConstants.TRANSACTION_REJECTED_PARSING_ISSUE -> {
                // User attempted to pay but their transaction was rejected. An error occured while reading the returned JSON
                mPaymentResponse?.onPaymentResponseCallback(
                    PaymentResponse.PaymentFailed(
                        PaymentErrorModel(
                            extras?.getString(IntentConstants.RAW_PAY_RESPONSE),
                            extras?.getString(PayResponseKeys.DATA_MESSAGE),
                            extras?.getString(PayResponseKeys.ORDER),
                        )
                    ))
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED_PARSING_ISSUE: >>>>> ${extras?.getString(IntentConstants.MISSING_ARGUMENT_VALUE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED_PARSING_ISSUE: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED_PARSING_ISSUE: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.DATA_MESSAGE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.ORDER)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.SUCCESS)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.CAPTURED_AMOUNT)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.IS_CAPTURE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.SOURCE_DATA_TYPE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.SOURCE_DATA_SUB_TYPE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_REJECTED_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.SOURCE_DATA_PAN)}")
            }
            IntentConstants.TRANSACTION_SUCCESSFUL -> {
                // User finished their payment successfully
                mPaymentResponse?.onPaymentResponseCallback(PaymentResponse.PaymentSuccess(
                    transaction = PaymentSuccessModel(
                        extras?.getString(IntentConstants.RAW_PAY_RESPONSE),
                        extras?.getString(PayResponseKeys.DATA_MESSAGE),
                        extras?.getString(PayResponseKeys.ORDER),
                    ),
                    null
                ))
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL: >>>>> ${extras?.getString(IntentConstants.MISSING_ARGUMENT_VALUE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL: >>>>> ${extras?.getString(PayResponseKeys.DATA_MESSAGE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL: >>>>> ${extras?.getString(PayResponseKeys.ORDER)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL: >>>>> ${extras?.getString(PayResponseKeys.SUCCESS)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL: >>>>> ${extras?.getString(PayResponseKeys.CAPTURED_AMOUNT)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL: >>>>> ${extras?.getString(PayResponseKeys.IS_CAPTURE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL: >>>>> ${extras?.getString(PayResponseKeys.SOURCE_DATA_TYPE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL: >>>>> ${extras?.getString(PayResponseKeys.SOURCE_DATA_SUB_TYPE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL: >>>>> ${extras?.getString(PayResponseKeys.SOURCE_DATA_PAN)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL: >>>>> ${extras?.getString(PayResponseKeys.ORDER)}")
            }
            IntentConstants.TRANSACTION_SUCCESSFUL_PARSING_ISSUE -> {
                mPaymentResponse?.onPaymentResponseCallback(PaymentResponse.PaymentSuccess(
                    transaction = PaymentSuccessModel(
                        extras?.getString(IntentConstants.RAW_PAY_RESPONSE),
                        extras?.getString(PayResponseKeys.DATA_MESSAGE),
                        extras?.getString(PayResponseKeys.ORDER),
                    ),
                    extras?.getString(SaveCardResponseKeys.TOKEN)
                ))
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL_PARSING_ISSUE: >>>>> ${extras?.getString(IntentConstants.MISSING_ARGUMENT_VALUE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL_PARSING_ISSUE: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL_PARSING_ISSUE: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.DATA_MESSAGE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.ORDER)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.SUCCESS)}")

            }
            IntentConstants.TRANSACTION_SUCCESSFUL_CARD_SAVED -> {
                // User finished their payment successfully and card was saved.
                mPaymentResponse?.onPaymentResponseCallback(PaymentResponse.PaymentSuccess(
                    transaction = PaymentSuccessModel(
                        extras?.getString(IntentConstants.RAW_PAY_RESPONSE),
                        extras?.getString(PayResponseKeys.DATA_MESSAGE),
                        extras?.getString(PayResponseKeys.ORDER),
                    ),
                    extras?.getString(SaveCardResponseKeys.TOKEN)
                ))
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL_CARD_SAVED: >>>>> ${extras?.getString(IntentConstants.MISSING_ARGUMENT_VALUE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL_CARD_SAVED: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL_CARD_SAVED: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL_CARD_SAVED: >>>>> ${extras?.getString(PayResponseKeys.DATA_MESSAGE)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL_CARD_SAVED: >>>>> ${extras?.getString(PayResponseKeys.ORDER)}")
                Log.e("PaymentResponseInside", "TRANSACTION_SUCCESSFUL_CARD_SAVED: >>>>> ${extras?.getString(PayResponseKeys.SUCCESS)}")
                // Use the static keys declared in PayResponseKeys to extract the fields you want
                // Use the static keys declared in SaveCardResponseKeys to extract the fields you want
            }
            IntentConstants.USER_CANCELED_3D_SECURE_VERIFICATION -> {
                // Note that a payment process was attempted. You can extract the original returned values
                // Use the static keys declared in PayResponseKeys to extract the fields you want
                mPaymentResponse?.onPaymentResponseCallback(PaymentResponse.PaymentSuccess(
                    transaction = PaymentSuccessModel(
                        extras?.getString(IntentConstants.RAW_PAY_RESPONSE),
                        extras?.getString(PayResponseKeys.DATA_MESSAGE),
                        extras?.getString(PayResponseKeys.ORDER),
                    ),
                    extras?.getString(PayResponseKeys.PENDING)
                ))
                Log.e("PaymentResponseInside", "USER_CANCELED_3D_SECURE_VERIFICATION: >>>>> ${extras?.getString(IntentConstants.MISSING_ARGUMENT_VALUE)}")
                Log.e("PaymentResponseInside", "USER_CANCELED_3D_SECURE_VERIFICATION: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "USER_CANCELED_3D_SECURE_VERIFICATION: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "USER_CANCELED_3D_SECURE_VERIFICATION: >>>>> ${extras?.getString(PayResponseKeys.DATA_MESSAGE)}")
                Log.e("PaymentResponseInside", "USER_CANCELED_3D_SECURE_VERIFICATION: >>>>> ${extras?.getString(PayResponseKeys.ORDER)}")
                Log.e("PaymentResponseInside", "USER_CANCELED_3D_SECURE_VERIFICATION: >>>>> ${extras?.getString(PayResponseKeys.SUCCESS)}")
            }
            IntentConstants.USER_CANCELED_3D_SECURE_VERIFICATION_PARSING_ISSUE -> {
                // Note that a payment process was attempted.
                // User finished their payment successfully. An error occured while reading the returned JSON.
                mPaymentResponse?.onPaymentResponseCallback(
                    PaymentResponse.PaymentUserCanceled(
                        extras?.getString(IntentConstants.RAW_PAY_RESPONSE)
                ))
                Log.e("PaymentResponseInside", "USER_CANCELED_3D_SECURE_VERIFICATION_PARSING_ISSUE: >>>>> ${extras?.getString(IntentConstants.MISSING_ARGUMENT_VALUE)}")
                Log.e("PaymentResponseInside", "USER_CANCELED_3D_SECURE_VERIFICATION_PARSING_ISSUE: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "USER_CANCELED_3D_SECURE_VERIFICATION_PARSING_ISSUE: >>>>> ${extras?.getString(IntentConstants.RAW_PAY_RESPONSE)}")
                Log.e("PaymentResponseInside", "USER_CANCELED_3D_SECURE_VERIFICATION_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.DATA_MESSAGE)}")
                Log.e("PaymentResponseInside", "USER_CANCELED_3D_SECURE_VERIFICATION_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.ORDER)}")
                Log.e("PaymentResponseInside", "USER_CANCELED_3D_SECURE_VERIFICATION_PARSING_ISSUE: >>>>> ${extras?.getString(PayResponseKeys.SUCCESS)}")
            }
        }
    }
}