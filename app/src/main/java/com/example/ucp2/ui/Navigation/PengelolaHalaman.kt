package com.example.ucp2.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.Menu
import com.example.ucp2.ui.view.DestinasiInsertDosen
import com.example.ucp2.ui.view.DestinasiInsertMataKuliah
import com.example.ucp2.ui.view.DetailMataKuliahView
import com.example.ucp2.ui.view.HomeDosenView
import com.example.ucp2.ui.view.HomeMataKuliahView
import com.example.ucp2.ui.view.InsertDosenView
import com.example.ucp2.ui.view.InsertMataKuliahView
import com.example.ucp2.ui.view.UpdateMataKuliahView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(navController = navController,
        startDestination = Navigasi.DestinasiMenu.route)
    {
        composable(
            route = Navigasi.DestinasiMenu.route
        ) {
            Menu(
                onHalamanMenuDosen = {
                    navController.navigate(Navigasi.DestinasiHomeDosen.route)
                },
                onHalamanMenuMK = {
                    navController.navigate(Navigasi.DestinasiHomeMataKuliah.route)
                },
                modifier = modifier
            )
        }
        composable(
            route = Navigasi.DestinasiHomeDosen.route
        ) {
            HomeDosenView(
                onAddDosen = {
                    navController.navigate(DestinasiInsertDosen.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertDosen.route
        ) {
            InsertDosenView(
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
            route = Navigasi.DestinasiHomeMataKuliah.route
        ) {
            HomeMataKuliahView(
                onDetailClick = { kode ->
                    navController.navigate("${Navigasi.DestinasiDetailMataKuliah.route}/$kode")
                    println(
                        "PengelolaHalaman: kode = $kode"
                    )
                },
                onAddMatkul = {
                    navController.navigate(DestinasiInsertMataKuliah.route)
                },
                onBack = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertMataKuliah.route
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
            Navigasi.DestinasiDetailMataKuliah.routeWithArg,
            arguments = listOf(
                navArgument(Navigasi.DestinasiDetailMataKuliah.Kode){
                    type = NavType.StringType
                }
            )
        ) {
            val kode = it.arguments?.getString(Navigasi.DestinasiDetailMataKuliah.Kode)
            kode?.let { kode->
                DetailMataKuliahView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${Navigasi.DestinasiUpdateMataKuliah.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            Navigasi.DestinasiUpdateMataKuliah.routeWithArg,
            arguments = listOf(
                navArgument(Navigasi.DestinasiUpdateMataKuliah.Kode){
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
            )
        }
    }
}