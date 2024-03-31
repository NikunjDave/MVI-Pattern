package com.sample.mvicardapp.data

import com.sample.mvicardapp.data.dto.LoginResponse
import com.sample.mvicardapp.domain.LoginRepository
import javax.inject.Inject

/***
 * Created by Nikunj Dave on 28/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
class LoginRepositoryImpl @Inject constructor(private val logInApi: LogInApi) : LoginRepository {
	override suspend fun login(userName: String, password: String): LoginResponse {
		return logInApi.login()
	}
}