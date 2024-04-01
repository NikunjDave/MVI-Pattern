package com.sample.mvicardapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.mvicardapp.ui.card.CardListScreen
import com.sample.mvicardapp.ui.login.LoginScreen
import com.sample.mvicardapp.ui.theme.MviCardAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val navController = rememberNavController()
			MviCardAppTheme {
				val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
				Scaffold(
					modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

					topBar = {
						CenterAlignedTopAppBar(
							colors = TopAppBarDefaults.topAppBarColors(
								containerColor = MaterialTheme.colorScheme.primaryContainer,
								titleContentColor = MaterialTheme.colorScheme.primary,
							),
							title = {
								Text(
									"CardApp",
									maxLines = 1,
									overflow = TextOverflow.Ellipsis
								)
							},
							navigationIcon = {
								IconButton(onClick = { navController.navigateUp() }) {
									Icon(
										imageVector = Icons.Filled.ArrowBack,
										contentDescription = "Localized description"
									)
								}
							},
							actions = {
								IconButton(onClick = {
									navController.navigate("checkout")
								}) {
									Icon(
										imageVector = Icons.Filled.ShoppingCart,
										contentDescription = "Localized description"
									)
								}
							},
							scrollBehavior = scrollBehavior,
						)
					},
				) {
					Box (modifier = Modifier.padding(it)){
						CardApp(navController)
					}
				}
			}
		}
	}


	@Composable
	 fun CardApp(navController: NavHostController) {
		NavHost(navController = navController, startDestination = "login") {
			// list of nodes
			composable(route = "login") {
				LoginScreen {
					navController.navigate("card_list")
				}
			}
			composable(route = "card_list") {
				CardListScreen()
			}
			composable(route = "checkout") {
				CheckoutScreen()
			}
		}
	}
}
