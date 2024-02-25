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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.modelo.Cliente
import com.example.vista_movil_pi.modelo.Usuario
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.viewmodel.ListadoClientesVM
import com.example.vista_movil_pi.vista.componentes.TarjetaCliente
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoClientes(navController: NavController, viewModel: ListadoClientesVM, uid: String) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val clientes: List<Cliente> by viewModel.clientes.observeAsState(emptyList())
    val cargando: Boolean by viewModel.cargando.observeAsState(initial = false)
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getClientes(uid)
    }

    if (cargando){
        CircularProgressIndicator()
    } else {
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
                            "TUS CLIENTES:",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.SemiBold,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(Vistas.Login.ruta) }) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.left_long_solid),
                                contentDescription = "",
                                tint = colorResource(id = R.color.blanco_claro)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(Vistas.Opciones.ruta) }) {
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
                BottomAppBar(
                    containerColor = colorResource(id = R.color.azul_oscuro),
                    contentColor = colorResource(id = R.color.blanco_claro),
                    actions = {
                        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                            IconButton(
                                onClick = { navController.navigate(Vistas.ListadoFacturas.ruta + "?uid=" + uid) },
                            ) {
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    painter = painterResource(id = R.drawable.receipt_solid),
                                    contentDescription = "",
                                    tint = colorResource(id = R.color.blanco_claro)
                                )
                            }
                            IconButton(onClick = { navController.navigate(Vistas.ListadoProyectos.ruta) }) {
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    painter = painterResource(id = R.drawable.list_check_solid),
                                    contentDescription = "",
                                    tint = colorResource(id = R.color.blanco_claro)
                                )
                            }
                            IconButton(onClick = {}, enabled = false) {
                                Icon(
                                    modifier = Modifier.size(35.dp),
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "",
                                    tint = colorResource(id = R.color.rojo_tomate)
                                )
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { navController.navigate(Vistas.FormFactura.ruta) },
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
                    }
                )
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.blanco_claro))
                    .padding(innerPadding)
            )
            {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 15.dp, end = 15.dp)
                        .fillMaxSize()
                ) {
                    items(clientes.size) { indice ->
                        TarjetaCliente(alClickar = { /*TODO*/ },
                            funcionBtn1 = { /*TODO*/ },
                            funcionBtn2 =
                            {
                                scope.launch { viewModel.eliminarCliente(uid, clientes, clientes[indice]._id, navController) }
                            },
                            nombre = clientes[indice].nombre,
                            apellidos = clientes[indice].apellidos
                        )
                    }
                }
            }
        }
    }
}