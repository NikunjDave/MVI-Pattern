package com.sample.mvicardapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.mvicardapp.data.local.CardPrefs.PrefKeys.KEY_CARD_LIST
import com.sample.mvicardapp.data.local.CardPrefs.PrefKeys.KEY_USER_LOGGED_IN
import com.sample.mvicardapp.domain.model.CardDetail
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
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
	suspend fun setUserLoggedIn(isLoggedIn: Boolean){
		prefs.edit { it[KEY_USER_LOGGED_IN] = isLoggedIn }
	}

	val userLoginFlow = prefs.data.map { it[KEY_USER_LOGGED_IN]  }


	private object PrefKeys{
		val KEY_CARD_LIST = stringPreferencesKey("key_card_list")
		val KEY_USER_LOGGED_IN = booleanPreferencesKey("key_login")
	}
}