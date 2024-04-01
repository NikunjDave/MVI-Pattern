package com.sample.mvicardapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.mvicardapp.data.local.CardPrefs.PrefKeys.KEY_CARD_LIST
import com.sample.mvicardapp.domain.model.CardDetail
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
class CardPrefs @Inject constructor(context : Context) {

	private val Context.appPrefsDataStore by preferencesDataStore(name = "cards_prefs")

	private val prefs = context.appPrefsDataStore

	suspend fun saveCards(cardList : List<CardDetail>){
		val gson = Gson()
		val cardListJson = gson.toJson(cardList)
		prefs.edit { it[KEY_CARD_LIST] = cardListJson}
	}

	suspend fun fetchCards() : List<CardDetail>{
		val jsonString = prefs.data.first()[KEY_CARD_LIST] ?: return emptyList()
		val type = object : TypeToken<List<CardDetail>>() {}.type
		return Gson().fromJson(jsonString, type)
	}

	private object PrefKeys{
		val KEY_CARD_LIST = stringPreferencesKey("key_card_list")
	}


}