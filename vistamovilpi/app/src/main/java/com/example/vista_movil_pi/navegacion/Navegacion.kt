package com.example.vista_movil_pi.navegacion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.crm.perfilCliente.PerfilCliente
import com.example.vista_movil_pi.viewmodel.ListadoClientesVM
import com.example.vista_movil_pi.viewmodel.ListadoFacturasVM
import com.example.vista_movil_pi.viewmodel.ListadoProyectosVM
import com.example.vista_movil_pi.viewmodel.LoginVM
import com.example.vista_movil_pi.viewmodel.PerfilClienteVM
import com.example.vista_movil_pi.viewmodel.ProyectoVM
import com.example.vista_movil_pi.viewmodel.RegistroVM

import com.example.vista_movil_pi.vista.pantallas.FormFactura
import com.example.vista_movil_pi.vista.pantallas.Factura
import com.example.vista_movil_pi.vista.pantallas.FormProyecto
import com.example.vista_movil_pi.vista.pantallas.FormTarea
import com.example.vista_movil_pi.vista.pantallas.ListadoClientes
import com.example.vista_movil_pi.vista.pantallas.ListadoFacturas
import com.example.vista_movil_pi.vista.pantallas.ListadoProyectos
import com.example.vista_movil_pi.vista.pantallas.Login
import com.example.vista_movil_pi.vista.pantallas.Opciones
import com.example.vista_movil_pi.vista.pantallas.Registro

@Composable
fun Navegacion() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Vistas.Login.ruta) {
        composable(
            route = "${Vistas.ListadoFacturas.ruta}?uid={uid}",
            arguments = listOf(navArgument(name = "uid") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("uid")
                ?.let { uid ->
                    ListadoFacturas(
                        viewModel = ListadoFacturasVM(),
                        navController = navController,
                        uid = uid
                    )
                }
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
                viewModel = LoginVM()
            )
        }
        composable("${Vistas.Registro.ruta}") {
            Registro(
                navController = navController,
                viewModel = RegistroVM()
            )
        }
        composable(
            route = "${Vistas.ListadoProyectos.ruta}?uid={uid}",
            arguments = listOf(navArgument(name = "uid") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("uid")
                ?.let { uid ->
                    ListadoProyectos(
                        navController = navController,
                        uid = uid,
                        viewModel = ListadoProyectosVM()
                    )
                }
        }
        composable(
            route = "${Vistas.ListadoClientes.ruta}?uid={uid}",
            arguments = listOf(navArgument(name = "uid") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("uid")
                ?.let { uid ->
                    ListadoClientes(
                        viewModel = ListadoClientesVM(),
                        navController = navController,
                        uid = uid
                    )
                }
        }
        composable("${Vistas.Opciones.ruta}") {
            Opciones(
                navController = navController,
            )
        }
        composable(
            route = "${Vistas.PerfilCliente.ruta}?uid={uid}&indice={indice}",
            arguments = listOf(
                navArgument(name = "uid") {
                    type = NavType.StringType
                },
                navArgument(name = "indice") {
                    type = NavType.IntType
                }
            )
        ) {
            val uid = it.arguments?.getString("uid")
            val indice = it.arguments?.getInt("indice")

            if (uid != null && indice != null) {
                PerfilCliente(
                    viewModel = PerfilClienteVM(),
                    navController = navController,
                    uid = uid,
                    indice = indice
                )
            }
        }
        composable(
            route = "${Vistas.FormProyecto.ruta}?uid={uid}",
            arguments = listOf(navArgument(name = "uid") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("uid")
                ?.let { uid ->
                    FormProyecto(
                        navController = navController,
                        viewModel = ProyectoVM(),
                        uid = uid
                    )
                }
        }
        composable(
            route = "${Vistas.FormTarea.ruta}?uid={uid}&pid={pid}",
            arguments = listOf(
                navArgument(name = "uid") {
                    type = NavType.StringType
                },
                navArgument(name = "pid") {
                    type = NavType.StringType
                }
            )
        ) {
            val uid = it.arguments?.getString("uid")
            val pid = it.arguments?.getString("pid")

            if (uid != null && pid != null) {
                FormTarea(
                    navController = navController,
                    viewModel = ListadoProyectosVM(),
                    uid = uid,
                    pid = pid
                )
            }
        }
    }
}