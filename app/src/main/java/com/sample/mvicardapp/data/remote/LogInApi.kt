package com.sample.mvicardapp.data.remote

import com.sample.mvicardapp.data.dto.LoginResponse
import com.sample.mvicardapp.utils.Result
import retrofit2.http.GET

interface LogInApi {
	@GET("/login")
	suspend fun login(): Result<LoginResponse>
}