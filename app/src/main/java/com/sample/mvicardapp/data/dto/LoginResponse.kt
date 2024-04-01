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
	val user: UserDto
)
