package com.sample.mvicardapp.ui.login

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.sample.mvicardapp.data.dto.toUser
import com.sample.mvicardapp.data.local.CardPrefs
import com.sample.mvicardapp.domain.LoginRepository
import com.sample.mvicardapp.utils.Error
import com.sample.mvicardapp.utils.Success
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/***
 * Created by Nikunj Dave on 28/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */

class LoginViewModel @AssistedInject constructor(
	@Assisted private val loginState: LoginState,
	private val prefs : CardPrefs,
	private val repository: LoginRepository,
) : MavericksViewModel<LoginState>(loginState) {
	fun login(userName: String, password: String) {
		if(userName.isEmpty() || password.isEmpty()) {
			setState { copy(error = "Please enter valid credential") }
			return
		}
		viewModelScope.launch {
			setState { copy(isLoading = true) }
			withContext(Dispatchers.IO) {
				repository.login(userName, password).also { response ->
					when (response) {
						is Success -> setState {
							copy(
								isLoading = false,
								userDetail = response.value.user.toUser(),
								error = ""
							)
						}
						is Error -> setState {
							copy(
								isLoading = false,
								userDetail = null,
								error = response.message.orEmpty()
							)
						}
					}
				}
			}
		}
	}

	val isLoggedInFlow = prefs.userLoginFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),false)

	fun resetLoginState() {
		setState {
			copy(
				isLoading = false,
				userDetail = null,
			)
		}
	}

	@AssistedFactory
	interface Factory : AssistedViewModelFactory<LoginViewModel, LoginState> {
		override fun create(state: LoginState): LoginViewModel
	}

	companion object : MavericksViewModelFactory<LoginViewModel, LoginState> by hiltMavericksViewModelFactory()
}