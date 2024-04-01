package com.sample.mvicardapp.data

import com.sample.mvicardapp.data.dto.LoginResponse
import com.sample.mvicardapp.data.dto.toCardDetail
import com.sample.mvicardapp.data.local.CardPrefs
import com.sample.mvicardapp.data.remote.LogInApi
import com.sample.mvicardapp.domain.LoginRepository
import com.sample.mvicardapp.utils.Result
import com.sample.mvicardapp.utils.Success
import javax.inject.Inject

/***
 * Created by Nikunj Dave on 28/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
class LoginRepositoryImpl @Inject constructor(private val logInApi: LogInApi, private val prefs : CardPrefs) : LoginRepository {
	override suspend fun login(userName: String, password: String): Result<LoginResponse> {
		// we are sending mock response here, hence bypassing the username/password
		return logInApi.login().also { result ->
			(result as? Success)?.let { loginResponse ->
				prefs.saveCards(loginResponse.value.user.cards.map { it.toCardDetail() })
			}
		}
	}
}