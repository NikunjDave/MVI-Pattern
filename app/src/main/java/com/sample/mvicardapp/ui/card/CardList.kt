package com.sample.mvicardapp.ui.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/***
 * Created by Nikunj Dave on 01/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */

@Composable
fun  CardListScreen(){
	println("Card List screen")
	Box(modifier = Modifier.fillMaxSize()){
		Text(text = "Card list screen")
	}

}