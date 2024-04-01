package com.sample.mvicardapp.di

import com.sample.mvicardapp.data.CardRepositoryImpl
import com.sample.mvicardapp.data.LoginRepositoryImpl
import com.sample.mvicardapp.domain.CardRepository
import com.sample.mvicardapp.domain.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/***
 * Created by Nikunj Dave on 28/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
@Module
@InstallIn(SingletonComponent::class)

abstract class LoginRepositoryModule {
	@Binds
	@Singleton
	abstract fun bindLoginRepository(
		loginRepositoryImpl: LoginRepositoryImpl
	): LoginRepository

	@Binds
	@Singleton
	abstract fun bindCardRepository(
		cardRepositoryImpl: CardRepositoryImpl
	): CardRepository
}