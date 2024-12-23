package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.viewdosen.DestinasiInsert
import com.example.ucp2.ui.viewmatakuliah.DetailMatakuliahView
import com.example.ucp2.ui.viewmatakuliah.HomeMatakuliahView
import com.example.ucp2.ui.viewmatakuliah.InsertMatakuliahView
import com.example.ucp2.ui.viewmatakuliah.UpdateMtkView
import com.example.ucp2.ui.viewmatakuliah.HomeView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = "home") {
        // Home Page
        composable(
            route = "home"
        ) {
            HomeView(
                onNavigateToMatakuliah = {
                    navController.navigate(DestinasiHomeMatkul.route)
                },
                onNavigateToDosen = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }

        // Matakuliah Home Page
        composable(
            route = DestinasiHomeMatkul.route
        ) {
            HomeMatakuliahView(
                onDetailClick = { kode ->
                    navController.navigate("${DestinasiDetail.route}/$kode")
                },
                onAddMatkul = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }

        // Insert Matakuliah Page
        composable(
            route = DestinasiInsert.route
        ) {
            InsertMatakuliahView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        // Detail Matakuliah Page
        composable(
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.KODE) {
                    type = NavType.StringType
                }
            )
        ) {
            val kode = it.arguments?.getString(DestinasiDetail.KODE)
            DetailMatakuliahView(
                onBack = {
                    navController.popBackStack()
                },
                onEditClick = {
                    navController.navigate("${DestinasiUpdateMatkul.route}/$kode")
                },
                modifier = modifier,
                onDeleteClick = {
                    navController.popBackStack()
                }
            )
        }

        // Update Matakuliah Page
        composable(
            DestinasiUpdateMatkul.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateMatkul.KODE) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateMtkView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}