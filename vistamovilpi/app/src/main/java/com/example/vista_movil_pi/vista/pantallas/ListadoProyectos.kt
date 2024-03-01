package com.example.vista_movil_pi.vista.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.modelo.Factura
import com.example.vista_movil_pi.modelo.Proyecto
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.viewmodel.ListadoProyectosVM
import com.example.vista_movil_pi.vista.componentes.TarjetaProyectos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoProyectos(navController: NavController, uid: String, viewModel: ListadoProyectosVM) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val proyectos: List<Proyecto> by viewModel.proyectos.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.getProyectos()
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(id = R.color.azul_oscuro),
                    titleContentColor = colorResource(id = R.color.blanco_claro),
                    navigationIconContentColor = colorResource(id = R.color.blanco_claro),
                    actionIconContentColor = colorResource(id = R.color.blanco_claro),
                ),
                title = {
                    Text(
                        "TUS PROYECTOS:",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.left_long_solid),
                            contentDescription = "",
                            tint = colorResource(id = R.color.blanco_claro)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.circle_user_solid),
                            contentDescription = "",
                            tint = colorResource(id = R.color.blanco_claro)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = colorResource(id = R.color.azul_oscuro),
                contentColor = colorResource(id = R.color.blanco_claro),
                actions = {
                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        IconButton(onClick = { navController.navigate(Vistas.ListadoFacturas.ruta + "?uid=" + uid) }) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.receipt_solid),
                                contentDescription = "",
                                tint = colorResource(id = R.color.blanco_claro)
                            )
                        }
                        IconButton(onClick = { navController.navigate(Vistas.ListadoProyectos.ruta + "?uid=" + uid) }, enabled = false) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.list_check_solid),
                                contentDescription = "",
                                tint = colorResource(id = R.color.rojo_tomate)
                            )
                        }
                        IconButton(onClick = { navController.navigate(Vistas.ListadoClientes.ruta + "?uid=" + uid)}) {
                            Icon(
                                modifier = Modifier.size(35.dp),
                                imageVector = Icons.Filled.Person,
                                contentDescription = "",
                                tint = colorResource(id = R.color.blanco_claro)
                            )
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { navController.navigate(Vistas.FormProyecto.ruta + "?uid=" + uid) },
                        containerColor = colorResource(id = R.color.blanco_claro),
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.circle_plus_solid),
                            contentDescription = "",
                            tint = colorResource(id = R.color.azul_oscuro)
                        )
                    }
                })
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.blanco_claro))
                .padding(innerPadding)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 20.dp),
                content = {
                    items(proyectos.size) { index ->
                        TarjetaProyectos(
                            uid = uid,
                            navController = navController,
                            proyecto = proyectos[index],
                            eliminarProyecto = {
                                viewModel.deleteProyecto(proyectos[index]._id)
                                navController.navigate(Vistas.ListadoProyectos.ruta + "?uid=" + uid)
                            },
                            viewModel = ListadoProyectosVM()
                        )
                    }
                }
            )
        }
    }
}