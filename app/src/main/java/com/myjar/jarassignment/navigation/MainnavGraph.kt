package com.myjar.jarassignment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myjar.jarassignment.presentation.home.ItemDetailScreen
import com.myjar.jarassignment.presentation.home.ItemListScreen

import com.myjar.jarassignment.presentation.home.JarViewModel

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()

) {



    NavHost(modifier = modifier, navController = navController, startDestination = "item_list") {
        composable("item_list") {

            val viewModel = viewModel<JarViewModel>()

            ItemListScreen(
                viewModel = viewModel,
                onNavigateToDetail = { route ->
                    navController.navigate(route)
                },

                )
        }
        composable("item_detail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            ItemDetailScreen(itemId = itemId)
        }


    }
}