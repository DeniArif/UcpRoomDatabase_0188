package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
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

@Composable
fun PengelolaHalaman(
  navController: NavHostController = rememberNavController(),
  modifier: Modifier = Modifier
){
    NavHost(navController = navController, startDestination = DestinasiHomeMatkul.route){
        composable(
            route = DestinasiHomeMatkul.route
        ){
            HomeMatakuliahView(
                onDetailClick = {kode ->
                    navController.navigate("${DestinasiDetail.route}/$kode")
                    println(
                        "PengelolaHalaman: kode = $kode"
                    )
                },
                onAddMatkul = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }
        composable(
            route = DestinasiInsert.route
        ){
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
        composable(
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.KODE){
                    type = NavType.StringType
                }
            )
        ) {
            val kode = it.arguments?.getString(DestinasiDetail.KODE)
            kode.let { kode ->
                DetailMatakuliahView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateMatkul.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateMatkul.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateMatkul.KODE) {
                    type = NavType.StringType
                }
            )
        ){
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