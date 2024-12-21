package com.example.ucp2.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.DestinasiInsert
import com.example.ucp2.ui.view.DetailMataKuliahView
import com.example.ucp2.ui.view.HomeView
import com.example.ucp2.ui.view.InsertMataKuliahView
import com.example.ucp2.ui.view.UpdateMataKuliahView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(navController = navController, startDestination = Navigasi.DestinasiHome.route) {
        composable(
            route = Navigasi.DestinasiHome.route
        ) {
            HomeView(
                onDetailClick = {nim ->
                    navController.navigate("${Navigasi.DestinasiDetail.route}/$nim")
                    println("Pengelola Halaman: nim = $nim")
                },
                onAddDosen = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }
        composable(
            route = DestinasiInsert.route
        ) {
            InsertMataKuliahView(
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
            Navigasi.DestinasiDetail.routeWithArg,
            arguments = listOf(
                navArgument(Navigasi.DestinasiDetail.Kode){
                    type = NavType.StringType
                }
            )
        ) {
            val kode = it.arguments?.getString(Navigasi.DestinasiDetail.Kode)
            kode?.let { kode->
                DetailMataKuliahView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${Navigasi.DestinasiUpdate.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            Navigasi.DestinasiUpdate.routeWithArg,
            arguments = listOf(
                navArgument(Navigasi.DestinasiUpdate.Kode){
                    type = NavType.StringType
                }
            )
        ) { UpdateMataKuliahView(
            onBack = {
                navController.popBackStack()
            },
            onNavigate = {
                navController.popBackStack()
            },
            modifier = modifier
        ) }
    }
}