package com.sample.mvicardapp.ui.login

import com.sample.mvicardapp.domain.model.User

/***
 * Created by Nikunj Dave on 31/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */

sealed class LoginState {

	object Loading : LoginState()
	data class ResultUserDetail(val data : User) : LoginState()

	data class ResultError(val error : String) : LoginState()
}