package com.example.vista_movil_pi.vista.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.modelo.Cliente
import com.example.vista_movil_pi.modelo.Usuario
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.viewmodel.ListadoClientesVM
import com.example.vista_movil_pi.vista.componentes.InputDelineado
import com.example.vista_movil_pi.vista.componentes.TarjetaCliente
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoClientes(navController: NavController, viewModel: ListadoClientesVM, uid: String) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val clientes: MutableList<Cliente>? by viewModel.clientes.observeAsState(null)
    val cargando: Boolean by viewModel.cargando.observeAsState(initial = false)
    val botonActivo: Boolean by viewModel.botonActivo.observeAsState(initial = false)
    val codCliente: String by viewModel.codCliente.observeAsState(initial = "")
    val scope = rememberCoroutineScope()
    var regCliente by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getClientes(uid)
    }

    if (cargando) {
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
                            IconButton(onClick = { navController.navigate(Vistas.ListadoProyectos.ruta + "?uid=" + uid) }) {
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
                            onClick = { regCliente = true },
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
                    clientes?.let {
                        items(it.size) { indice ->
                            TarjetaCliente(
                                alClickar = { /*TODO*/ },
                                funcionBtn1 =
                                {
                                    navController.navigate(Vistas.PerfilCliente.ruta + "?uid=" + uid + "&indice=" + indice)
                                },
                                funcionBtn2 =
                                {
                                    scope.launch {
                                        viewModel.eliminarCliente(
                                            uid,
                                            clientes!!,
                                            clientes!![indice]._id,
                                            navController
                                        )
                                    }
                                },
                                nombre = clientes!![indice].nombre,
                                apellidos = clientes!![indice].apellidos
                            )
                        }
                    }
                }

                if (regCliente){
                    Dialog(onDismissRequest =
                    {

                    }) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(16.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(id = R.color.blanco_claro),
                                contentColor = colorResource(id = R.color.azul_oscuro)
                            )
                        ) {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp)
                            ) {
                                Text(
                                    text = "Inserta el código de cliente:",
                                    textAlign = TextAlign.Center,
                                )
                                InputDelineado(
                                    valor = codCliente,
                                    alCambiarValor = { viewModel.CambiarInputs(it) },
                                    etiqueta = {
                                        Text(text = "Código del cliente")
                                    },
                                    placeholder = {
                                        Text(text = "Código del cliente...")
                                    },
                                    esContra = false
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                ) {
                                    TextButton(
                                        onClick = {
                                            regCliente = false
                                        },
                                        modifier = Modifier.padding(8.dp),
                                        colors = ButtonDefaults.textButtonColors(contentColor = colorResource(id = R.color.rojo_tomate))
                                    ) {
                                        Text("Cancelar", color = colorResource(id = R.color.rojo_tomate))
                                    }
                                    TextButton(
                                        onClick = {
                                            scope.launch { viewModel.anadirCliente(uid, codCliente) }
                                            scope.launch { viewModel.getClientes(uid) }
                                            regCliente = false
                                        },
                                        enabled = botonActivo,
                                        modifier = Modifier.padding(8.dp),
                                        colors = ButtonDefaults.textButtonColors(containerColor = colorResource(id = R.color.azul_verdoso))
                                    ) {
                                        Text(text = "Añadir", color = colorResource(id = R.color.azul_oscuro))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}