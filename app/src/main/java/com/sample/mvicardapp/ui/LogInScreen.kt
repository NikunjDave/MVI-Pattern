package com.sample.mvicardapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
	val loginViewModel: LoginViewModel = hiltViewModel()
	LoginCard{
		loginViewModel.login()
	}

}

@Composable
private fun LoginCard(onLoginClick: () -> Unit) {
	Card(
		modifier = Modifier
			.padding(all = 10.dp)
			.fillMaxWidth()
	) {
		Column(modifier = Modifier.padding(all = 10.dp)) {
			InputBox(text = "Enter Username", value = "john", onValueChange = {
			})

			InputBox(text = "Enter Password", value = "1234", onValueChange = {
			})

			Button(
				onClick = {onLoginClick.invoke()},
				modifier = Modifier
					.wrapContentSize()
					.padding(8.dp),
				colors = ButtonDefaults.buttonColors(
					containerColor = Color.Blue,
					contentColor = Color.White
				)
			) {
				Text(text = "Login")
			}

		}
	}
}


@Preview
@Composable
fun LoginCardPreview() {
	LoginCard({})
}

@Composable
private fun InputBox(
	text: String,
	value: String,
	onValueChange: (String) -> Unit
) {
	TextField(
		value = value,
		onValueChange = onValueChange,
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp, vertical = 8.dp),
		label = { Text(text) },
		singleLine = true
	)
}

@Composable
private fun MyButton(
	text: String,
	onClick: () -> Unit
) {
	Button(
		onClick = onClick,
		modifier = Modifier
			.wrapContentSize()
			.padding(8.dp),
		colors = ButtonDefaults.buttonColors(
			containerColor = Color.Blue,
			contentColor = Color.White
		)
	) {
		Text(text = text)
	}
}

