package com.sample.mvicardapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.sample.mvicardapp.data.dto.toUser
import com.sample.mvicardapp.domain.LoginRepository
import com.sample.mvicardapp.utils.Error
import com.sample.mvicardapp.utils.Success
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
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

class LoginViewModel @AssistedInject constructor(
	@Assisted private val loginState : LoginState,
	private val repository: LoginRepository,
) : MavericksViewModel<LoginState>(loginState) {
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

	private fun login(userName: String, password: String) {
		viewModelScope.launch {
			setState { copy(isLoading = true)}
			withContext(Dispatchers.IO) {
				repository.login(userName, password).also { response ->
					when (response) {
						is Success -> setState { copy(isLoading = false, userDetail = response.value.user.toUser(), error = "")} //LoginState.ResultUserDetail(response.value.user.toUser()) }
						is Error -> setState { copy(isLoading = false, userDetail = null, error = response.message.orEmpty())} //setState {LoginState.ResultError(response.message.orEmpty())}
					}
				}
			}
		}
	}

	@AssistedFactory
	interface Factory : AssistedViewModelFactory<LoginViewModel, LoginState> {
		override fun create(state: LoginState): LoginViewModel
	}

	companion object : MavericksViewModelFactory<LoginViewModel, LoginState> by hiltMavericksViewModelFactory()
}