package com.sample.mvicardapp.ui.card

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.sample.mvicardapp.domain.CardRepository
import com.sample.mvicardapp.domain.SelectedCard
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
class CardViewModel @AssistedInject constructor(
	private val repository: CardRepository,
	@Assisted private val cardState: CardState
) : MavericksViewModel<CardState>(cardState) {

	val cardIntent = Channel<CardIntent>(Channel.UNLIMITED)
	init {
		handleIntent()
	}

	private fun handleIntent() {
		viewModelScope.launch {
			cardIntent.consumeAsFlow().collect {
				when (it) {
					is CardIntent.FetchCard -> fetchCardList()
					is CardIntent.SelectCard -> {
						SelectedCard.card = it.card
					}
				}
			}
		}
	}

	private fun fetchCardList() {
		viewModelScope.launch {
			setState { copy(isLoading = true) }
			repository.fetchCards().also {
				setState { copy(isLoading = false,cards = it) }
			}
		}
	}

	@AssistedFactory
	interface Factory : AssistedViewModelFactory<CardViewModel, CardState> {
		override fun create(state: CardState): CardViewModel
	}

	companion object : MavericksViewModelFactory<CardViewModel, CardState> by hiltMavericksViewModelFactory()

}
