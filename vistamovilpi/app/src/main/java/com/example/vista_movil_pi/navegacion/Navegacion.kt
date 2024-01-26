package com.example.vista_movil_pi.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.vista_movil_pi.vista.pantallas.FormFactura
import com.example.vista_movil_pi.vista.pantallas.Factura
import com.example.vista_movil_pi.vista.pantallas.ListadoFacturas
import com.example.vista_movil_pi.vista.pantallas.Login
import com.example.vista_movil_pi.vista.pantallas.Registro


@Composable
fun Navegacion() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Vistas.Login.ruta) {
        composable("${Vistas.ListadoFacturas.ruta}") {
            ListadoFacturas(
                navController = navController,
            )
        }
        composable("${Vistas.Factura.ruta}") {
            Factura(
                navController = navController,
            )
        }
        composable("${Vistas.FormFactura.ruta}") {
            FormFactura(
                navController = navController,
            )
        }
        composable("${Vistas.Login.ruta}") {
            Login(
                navController = navController,
            )
        }
        composable("${Vistas.Registro.ruta}") {
            Registro(
                navController = navController,
            )
        }
    }
}