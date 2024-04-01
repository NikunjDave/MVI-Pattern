package com.sample.mvicardapp.ui.card

import com.airbnb.mvrx.MavericksState
import com.sample.mvicardapp.domain.model.CardDetail

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
data class CardState(
	val isLoading : Boolean = false,
	val cards : List<CardDetail> = emptyList(),
	val error : String = ""
) : MavericksState