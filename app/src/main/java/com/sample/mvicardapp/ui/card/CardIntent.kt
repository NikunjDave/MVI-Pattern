package com.sample.mvicardapp.ui.card

import com.sample.mvicardapp.domain.model.CardDetail

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
sealed class CardIntent {
	object FetchCard : CardIntent()
	data class SelectCard(val card : CardDetail) : CardIntent()
}