package com.sample.mvicardapp.data.dto

/***
 * Created by Nikunj Dave on 28/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
data class LoginResponse(
	val success: Boolean,
	val message: String,
	val user: User
)

data class User(
	val id: Int,
	val username: String,
	val email: String,
	val full_name: String,
	val avatar_url: String,
	val cards: List<Card>
)

data class Card(
	val id: Int,
	val name: String,
	val card_number: String,
	val expiry_date: String,
	val cvv: String
)