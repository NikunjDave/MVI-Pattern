package com.sample.mvicardapp.domain

import com.sample.mvicardapp.domain.model.CardDetail

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
interface CardRepository {
	suspend fun fetchCards(): List<CardDetail>
}