package com.sample.mvicardapp.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun LoginScreen() {
	val loginViewModel: LoginViewModel = hiltViewModel()
	val loginState = loginViewModel.loginStateFlow.collectAsState()
	val coroutineScope = rememberCoroutineScope()
	Box(modifier = Modifier) {
		when (loginState.value) {
			is LoginState.Loading -> {
				CircularProgressIndicator(
					modifier = Modifier.align(Alignment.Center)
				)
			}

			is LoginState.ResultUserDetail -> {
				println("user  called")
				Toast.makeText(
					LocalContext.current,
					"user is ${(loginState.value as LoginState.ResultUserDetail).data.fullName}",
					Toast.LENGTH_SHORT
				).show()
			}

			is LoginState.ResultError -> {
				println("error called")
			}

			else -> {}
		}
		LoginCardWithButton { credentials ->
			coroutineScope.launch {
				loginViewModel.loginIntent.send(LoginIntent.Login(credentials.first, credentials.second))
			}
		}

	}

}


@Composable
private fun LoginCardWithButton(onLoginClick : (Pair<String,String>) -> Unit) {
	var userNamePassword by remember { mutableStateOf(Pair("","")) }

	Column(
		modifier = Modifier.padding(all = 10.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {

		LoginCard {
			userNamePassword = it
		}
		Button(
			onClick = {
				onLoginClick.invoke(userNamePassword)
			},
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

@Composable
private fun LoginCard(modifier: Modifier = Modifier, credential : (Pair<String,String>) -> Unit) {
	val userNamePassword :  Pair<String,String> = Pair("john","1234")
	Card(
		modifier = modifier
			.padding(all = 10.dp)
			.fillMaxWidth()
	) {
		Column(
			modifier = Modifier.padding(all = 10.dp)
		) {
			InputBox(text = "Enter Username", value = userNamePassword.first, onValueChange = {
				credential.invoke(userNamePassword.copy(first = it))
			})

			InputBox(text = "Enter Password", value = userNamePassword.second, onValueChange = {
				credential.invoke(userNamePassword.copy(second = it))
			})
		}
	}
}


@Preview(showSystemUi = true)
@Composable
fun LoginCardPreview() {
	//LoginCard()
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
