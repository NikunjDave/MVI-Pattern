package com.sample.mvicardapp.ui.card

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
}