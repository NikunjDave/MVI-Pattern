package com.sample.mvicardapp.ui.card

import com.sample.mvicardapp.domain.model.CardDetail
import com.sample.mvicardapp.domain.model.User
import com.sample.mvicardapp.ui.login.LoginState

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
sealed class CardState {
	object Loading : CardState()

	data class ResultCardList(val cards : List<CardDetail>) : CardState()

	data class ResultError(val error : String) : CardState()
}