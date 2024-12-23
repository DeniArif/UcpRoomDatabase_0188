package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.ucp2.ui.viewdosen.DestinasiInsert
import com.example.ucp2.ui.viewmatakuliah.HomeMatakuliahView
import com.example.ucp2.ui.viewmatakuliah.InsertMatakuliahView

@Composable
fun PengelolaHalaman(
  navController: NavController = rememberNavController(),
  modifier: Modifier = Modifier
){
    NavHost(navController = navController, startDestination = DestinasiHome.route){
        composable(
            route = DestinasiHome.route
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

        )
    }
}