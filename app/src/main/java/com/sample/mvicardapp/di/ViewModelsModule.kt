package com.sample.mvicardapp.di

import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import com.sample.mvicardapp.ui.card.CardViewModel
import com.sample.mvicardapp.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelsModule {
	@Binds
	@IntoMap
	@ViewModelKey(LoginViewModel::class)
	fun loginViewModelFactory(factory: LoginViewModel.Factory): AssistedViewModelFactory<*, *>

	@Binds
	@IntoMap
	@ViewModelKey(CardViewModel::class)
	fun cardViewModelFactory(factory: CardViewModel.Factory): AssistedViewModelFactory<*, *>
}