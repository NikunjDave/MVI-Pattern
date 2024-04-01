package com.sample.mvicardapp.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.mvicardapp.data.local.CardPrefs
import com.sample.mvicardapp.domain.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
@HiltViewModel
class CardViewModel @Inject constructor(
	private val repository: CardRepository
) : ViewModel() {

	val cardIntent = Channel<CardIntent>(Channel.UNLIMITED)

	init {
		handleIntent()
	}

	private fun handleIntent() {
		viewModelScope.launch {
			cardIntent.consumeAsFlow().collect {
				when (it) {
					is CardIntent.FetchCard -> fetchCardList()
				}
			}
		}
	}

	private val _cardStateFlow = MutableStateFlow<CardState?>(null)

	val cardStateFlow = _cardStateFlow.asStateFlow()

	private fun fetchCardList() {
		viewModelScope.launch {
			_cardStateFlow.emit(CardState.Loading)
			repository.fetchCards().also {
				_cardStateFlow.emit(CardState.ResultCardList(it))
			}
		}
	}

}
