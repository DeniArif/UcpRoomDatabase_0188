package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun PengelolaHalaman(
  navController: NavController = rememberNavController(),
  modifier: Modifier = Modifier
){
    NavHost(navController = navController, startDestination = DestinasiHome.route){
        composable(
            route = DestinasiHome.route
        )
    }
}