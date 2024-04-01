package com.sample.mvicardapp.ui.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.mvicardapp.domain.model.CardDetail
import kotlinx.coroutines.launch

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */

@Composable
fun CardListScreen() {
	val cardViewModel: CardViewModel = hiltViewModel()
	val cardState = cardViewModel.cardStateFlow.collectAsState()
	val coroutineScope = rememberCoroutineScope()

	coroutineScope.launch {
		cardViewModel.cardIntent.send(CardIntent.FetchCard)
	}
	Box(modifier = Modifier) {
		when (cardState.value) {
			is CardState.Loading -> CircularProgressIndicator(
				modifier = Modifier.align(Alignment.Center)
			)

			is CardState.ResultCardList -> {
				CardList(cards = (cardState.value as CardState.ResultCardList).cards, onCardClick = {
					coroutineScope.launch {
						cardViewModel.cardIntent.send(CardIntent.SelectCard(it))
					}
				})
			}

			is CardState.ResultError -> {
				println("Error : ${(cardState.value as CardState.ResultError).error}")
			}

			else -> {}

		}
	}
}

@Composable
fun CardList(cards: List<CardDetail>, onCardClick: (CardDetail) -> Unit) {
	LazyColumn(modifier = Modifier.padding(bottom = 56.dp), content = {
		items(cards) { card ->
			CardRow(card, onCardClick)
		}
	})
}

@Composable
private fun CardRow(card: CardDetail, onCardClick: (CardDetail) -> Unit) {
	Card(
		modifier = Modifier
			.padding(all = 10.dp)
			.fillMaxWidth()
			.clickable { onCardClick(card) }
	) {
		Column(modifier = Modifier.padding(all = 10.dp)) {
			Text(
				card.name,
				fontSize = 25.sp,
				fontWeight = FontWeight.W700,
				modifier = Modifier.padding(10.dp)
			)
			Text(card.cardNumber, color = Color.Gray, modifier = Modifier.padding(10.dp))
			Text(card.cvv, color = Color.Blue, modifier = Modifier.padding(10.dp))
		}
	}
}