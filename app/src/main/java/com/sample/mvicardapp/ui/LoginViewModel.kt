package com.sample.mvicardapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.mvicardapp.domain.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
 fun login(){
	 viewModelScope.launch {
		 withContext(Dispatchers.IO) {
			 println("thread is ${Thread.currentThread().name}")
			 val response = repository.login("", "")

			 println("response is $response")
		 }
	 }
 }
}