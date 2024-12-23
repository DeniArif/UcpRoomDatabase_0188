package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.viewdosen.HomeDosenView
import com.example.ucp2.ui.viewdosen.InsertDosenView
import com.example.ucp2.ui.viewmatakuliah.*

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = "home") {
        // Home Page
        composable(route = "home") {
            HomeView(
                onNavigateToMatakuliah = {
                    navController.navigate(DestinasiHomeMatkul.route)
                },
                onNavigateToDosen = {
                    navController.navigate(DestinasiHomeDosen.route)
                },
                modifier = modifier

            )
        }

        // Matakuliah Home Page
        composable(route = DestinasiHomeMatkul.route) {
            HomeMatakuliahView(
                onDetailClick = { kode ->
                    navController.navigate("${DestinasiDetail.route}/$kode")
                },
                onBack = { navController.popBackStack() },
                onAddMatkul = {
                    navController.navigate(DestinasiInsertMatkul.route)
                },
                modifier = modifier

            )
        }

        // Insert Matakuliah Page
        composable(route = DestinasiInsertMatkul.route) {
            InsertMatakuliahView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier
            )
        }

        // Detail Matakuliah Page
        composable(
            route = DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.KODE) { type = NavType.StringType }
            )
        ) {
            val kode = it.arguments?.getString(DestinasiDetail.KODE)
            DetailMatakuliahView(
                onBack = { navController.popBackStack() },
                onEditClick = { navController.navigate("${DestinasiUpdateMatkul.route}/$kode") },
                modifier = modifier,
                onDeleteClick = { navController.popBackStack() }
            )
        }

        // Update Matakuliah Page
        composable(
            route = DestinasiUpdateMatkul.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateMatkul.KODE) { type = NavType.StringType }
            )
        ) {
            UpdateMtkView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier
            )
        }

        // Dosen Home Page
        composable(route = DestinasiHomeDosen.route) {
            HomeDosenView(
                onAddDosen = {
                    navController.navigate(DestinasiInsertDosen.route)
                },
                onBack = { navController.popBackStack() },
                modifier = modifier

            )
        }

        // Insert Dosen Page
        composable(route = DestinasiInsertDosen.route) {
            InsertDosenView(
                onBack = { navController.popBackStack() },
                onNavigasi = { navController.popBackStack() },
                modifier = modifier
            )
        }
    }
}
