package com.sample.mvicardapp.data.dto

import com.sample.mvicardapp.domain.model.CardDetail
import com.sample.mvicardapp.domain.model.User

/***
 * Created by Nikunj Dave on 31/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */

fun CardDto.toCardDetail() : CardDetail =
	CardDetail(
		id = id,
		name = name,
		cardNumber = cardNumber,
		expiryDate = expiryDate,
		cvv = cvv
	)


fun UserDto.toUser(): User =
	User(
		id = id,
		username = username,
		email = email,
		fullName = fullName,
		avatarUrl = avatarUrl,
		cards = cards.map { it.toCardDetail() }
	)