package com.sample.mvicardapp.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.sample.mvicardapp.data.LogInApi
import com.sample.mvicardapp.data.remote.ApiInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	@Singleton
	fun okhttpClient(@ApplicationContext appContext: Context): OkHttpClient {
		val client = OkHttpClient().newBuilder()
			.addInterceptor(ApiInterceptor(appContext))
		return client.build()
	}

	@Provides
	@Singleton
	fun retrofit(okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder()
			.baseUrl("https://google.com")
			.addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
			.client(okHttpClient)
			.build()

	}

	@Provides
	fun loginApi(retrofit: Retrofit): LogInApi {
		return retrofit.create(LogInApi::class.java)
	}
}
