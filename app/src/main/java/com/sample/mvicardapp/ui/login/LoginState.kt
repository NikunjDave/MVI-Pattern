package com.sample.mvicardapp.ui.login

import com.airbnb.mvrx.MavericksState
import com.sample.mvicardapp.domain.model.User

/***
 * Created by Nikunj Dave on 31/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */

data class LoginState(
	val isLoading : Boolean = false,
	val userDetail : User? = null,
	val error : String = ""
) : MavericksState
/*
{

	object Loading : LoginState()
	data class ResultUserDetail(val data : User) : LoginState()

	data class ResultError(val error : String) : LoginState()
}*/
