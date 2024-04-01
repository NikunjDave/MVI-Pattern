package com.sample.mvicardapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.unpackFloat1
import com.sample.mvicardapp.domain.SelectedCard
import com.sample.mvicardapp.domain.model.CardDetail

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */

@Composable
fun  CheckoutScreen(){
	Column(modifier = Modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Top) {
		Text(text = "Show Selected Card")
		if(SelectedCard.card != null){
			CardRow(card = SelectedCard.card!!)
		}else{
			Text(text = "No Card selected yet.")
		}
	}
}

@Composable
private fun CardRow(card: CardDetail) {
	Card(
		modifier = Modifier
			.padding(all = 10.dp)
			.fillMaxWidth()
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