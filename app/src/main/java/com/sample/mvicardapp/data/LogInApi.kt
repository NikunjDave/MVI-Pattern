package com.sample.mvicardapp.data

import com.sample.mvicardapp.data.dto.LoginResponse
import retrofit2.http.GET

interface LogInApi {
	@GET("/login")
	suspend fun login(): LoginResponse
}