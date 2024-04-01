package com.sample.mvicardapp.domain.model

/***
 * Created by Nikunj Dave on 31/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
data class CardDetail(
	val id: Int,
	val name: String,
	val cardNumber: String,
	val expiryDate: String,
	val cvv: String
)