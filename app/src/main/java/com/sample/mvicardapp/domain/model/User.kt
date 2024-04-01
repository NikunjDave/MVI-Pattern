package com.sample.mvicardapp.domain.model

import com.sample.mvicardapp.data.dto.CardDto

/***
 * Created by Nikunj Dave on 31/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
data class User(
	val id: Int,
	val username: String,
	val email: String,
	val fullName: String,
	val avatarUrl: String,
	val cards: List<CardDetail>
)