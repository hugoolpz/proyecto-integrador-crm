package com.example.vista_movil_pi.navegacion
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vista_movil_pi.vista.pantallas.ListadoFacturas
import com.example.vista_movil_pi.vista.pantallas.OpcionesPerfil
import com.example.vista_movil_pi.vista.pantallas.TusProyectos

@Composable
fun Navegacion(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Vistas.ListadoFacturas.ruta) {
        composable("${Vistas.ListadoFacturas.ruta}") {
            ListadoFacturas(
                navController = navController,
            )
        }
        composable(Vistas.OpcionesPerfil.ruta) {
            OpcionesPerfil(
                navController = navController,
            )
        }
        composable(Vistas.TusProyectos.ruta) {
            TusProyectos(
                navController = navController,
            )
        }
    }
}