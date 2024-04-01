package com.sample.mvicardapp.data.dto

import com.google.gson.annotations.SerializedName

/***
 * Created by Nikunj Dave on 31/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
data class CardDto(
	val id: Int,
	val name: String,
	@SerializedName("card_number")
	val cardNumber: String,
	@SerializedName("expiry_date")
	val expiryDate: String,
	val cvv: String
)