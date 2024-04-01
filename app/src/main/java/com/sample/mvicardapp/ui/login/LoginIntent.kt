package com.sample.mvicardapp.ui.login

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
sealed class LoginIntent {
	data class Login(val userName: String, val password : String) : LoginIntent()
}