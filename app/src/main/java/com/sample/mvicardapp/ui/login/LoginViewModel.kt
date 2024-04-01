package com.sample.mvicardapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.mvicardapp.data.dto.toUser
import com.sample.mvicardapp.domain.LoginRepository
import com.sample.mvicardapp.utils.Error
import com.sample.mvicardapp.utils.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/***
 * Created by Nikunj Dave on 28/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val repository: LoginRepository
) : ViewModel() {
	val loginIntent = Channel<LoginIntent>(Channel.UNLIMITED)

	init {
		handleIntent()
	}

	private fun handleIntent() {
		viewModelScope.launch {
			loginIntent.consumeAsFlow().collect {
				when (it) {
					is LoginIntent.Login -> login(it.userName, it.password)
				}
			}
		}
	}

	private val _loginStateFlow = MutableStateFlow<LoginState?>(null)
	val loginStateFlow = _loginStateFlow.asStateFlow()
	private fun login(userName: String, password: String) {
		println("login called")
		viewModelScope.launch {
			if (userName.isEmpty() || password.isEmpty()) {
				_loginStateFlow.emit(LoginState.ResultError("Please enter valid credential"))
			} else {
				_loginStateFlow.emit(LoginState.Loading)
				withContext(Dispatchers.IO) {
					repository.login(userName, password).also { response ->
						when (response) {
							is Success -> _loginStateFlow.emit(LoginState.ResultUserDetail(response.value.user.toUser()))
							is Error -> _loginStateFlow.emit(LoginState.ResultError(response.message.orEmpty()))
						}
					}
				}
			}
		}
	}

	fun clearLoginState() {
		viewModelScope.launch {
			_loginStateFlow.emit(null)
		}
	}
}