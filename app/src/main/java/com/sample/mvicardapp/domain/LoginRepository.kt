package com.sample.mvicardapp.domain

import com.sample.mvicardapp.data.dto.LoginResponse

/***
 * Created by Nikunj Dave on 28/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
interface LoginRepository {
	suspend fun login(userName: String, password: String) : LoginResponse
}