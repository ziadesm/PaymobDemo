package com.app.paymob.builder

import androidx.annotation.ColorRes

interface PaymentBuilder {
    /**
     * Is a set of strings as "VISA", "MASTER", "DIRECTDEBIT_SEPA", etc...
     * @params paymentType
     * */
    fun saveCardDefault(saveCard: Boolean): PaymentBuilder
    fun showSavedCards(showSaved: Boolean): PaymentBuilder
    fun showActionBar(show: Boolean): PaymentBuilder
    fun language(language: String): PaymentBuilder
    fun themeColor(@ColorRes color: Int): PaymentBuilder
    fun token(token: String): PaymentBuilder
    fun maskedPanNumber(mask: String): PaymentBuilder


    /**
     * Setting first name in Paymob database
     * @params setFirstName
     * */
    fun setFirstName(firstName: String): PaymentBuilder
    /**
     * Setting last name in Paymob database
     * @params setLastName
     * */
    fun setLastName(lastName: String): PaymentBuilder
    /**
     * Setting Building number in Paymob database
     * @params setBuildingNumber
     * */
    fun setBuildingNumber(buildingNumber: String): PaymentBuilder
    /**
     * Setting Floor number in Paymob database
     * @params setFloorNumber
     * */
    fun setFloorNumber(floorNumber: String): PaymentBuilder
    /**
     * Setting Apartment number in Paymob database
     * @params setApartmentNumber
     * */
    fun setApartmentNumber(floorNumber: String): PaymentBuilder
    /**
     * Setting city name in Paymob database
     * @params city_name
     * */
    fun setCityName(floorNumber: String): PaymentBuilder
    fun setStateName(stateNumber: String): PaymentBuilder
    fun setCountry(countryName: String): PaymentBuilder
    fun setEmail(email: String): PaymentBuilder
    fun setPhoneNumber(phoneNumber: String): PaymentBuilder
    fun setPostalNumber(postalNumber: String): PaymentBuilder

    /**
     * Is used to set APPLICATION_ID or BUNDLE_ID ONLY
     * @params shopperUrl
    * */
    fun testMode(testMode: Boolean): PaymentBuilder

    /**
     * Is the method used to run the SDK activity
     * view and dialog to choose and pay
     * @params shopperUrl
     * */
    fun build()
}