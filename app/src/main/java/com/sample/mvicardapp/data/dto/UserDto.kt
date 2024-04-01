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
data class UserDto(
	val id: Int,
	val username: String,
	val email: String,
	@SerializedName("full_name")
	val fullName: String,
	@SerializedName("avatar_url")
	val avatarUrl: String,
	val cards: List<CardDto>
)