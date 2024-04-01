package com.sample.mvicardapp.data

import com.sample.mvicardapp.data.local.CardPrefs
import com.sample.mvicardapp.domain.CardRepository
import com.sample.mvicardapp.domain.model.CardDetail
import javax.inject.Inject

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
class CardRepositoryImpl @Inject constructor(private val cardPrefs: CardPrefs) : CardRepository {
	override suspend fun fetchCards(): List<CardDetail> {
		return cardPrefs.fetchCards()
	}
}