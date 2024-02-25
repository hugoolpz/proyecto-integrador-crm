package com.example.crm.perfilCliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.modelo.Cliente
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.viewmodel.PerfilClienteVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilCliente(viewModel: PerfilClienteVM, navController: NavHostController,  uid: String , indice: Int) {
    val cliente: Cliente by viewModel.cliente.observeAsState(Cliente("","","","","","","", emptyList()))
    val cargando : Boolean by viewModel.cargando.observeAsState(initial = false)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(Unit){
        viewModel.ObtenerDatosClienteElegido(uid, indice)
    }

    if (cargando){
        Box(modifier = Modifier
            .fillMaxSize()
            .border(10.dp, colorResource(id = R.color.azul_oscuro)), contentAlignment = Alignment.Center){
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(text = "Cargando...", color = colorResource(id = R.color.azul_verdoso), fontSize = 25.sp)
                LinearProgressIndicator(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(15.dp),
                    color = colorResource(id = R.color.azul_oscuro),
                    trackColor = colorResource(id = R.color.azul_verdoso)
                )
            }
        }
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
                        IconButton(onClick = { navController.navigate(Vistas.ListadoClientes.ruta + "?uid=" + uid) }) {
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
                            IconButton(onClick = {navController.navigate(Vistas.ListadoClientes.ruta + "?uid=" + uid)}) {
                                Icon(
                                    modifier = Modifier.size(35.dp),
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "",
                                    tint = colorResource(id = R.color.blanco_claro)
                                )
                            }
                        }
                    }
                )
            },
        ){ paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(colorResource(id = R.color.azul_verdoso))
            ){
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    ElevatedCard(
                        modifier = Modifier
                            .size(350.dp, 310.dp)
                            .padding(top = 20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.blanco_claro),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Row(horizontalArrangement = Arrangement.spacedBy(90.dp), verticalAlignment = Alignment.CenterVertically) {
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text(text = "Nombre", fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline, fontSize = 20.sp)
                                    Text(text = cliente.nombre)
                                    Text(text = "Apellidos", fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline, fontSize = 20.sp)
                                    Text(text = cliente.apellidos)
                                    Text(text = "Correo", fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline, fontSize = 20.sp)
                                    Text(text = cliente.correo)
                                }
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text(text = "NIF", fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline, fontSize = 20.sp)
                                    Text(text = cliente.nif)
                                    Text(text = "Dirección", fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline, fontSize = 20.sp)
                                    Text(text = cliente.direccion)
                                    Text(text = "Teléfono", fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline, fontSize = 20.sp)
                                    Text(text = cliente.telefono)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}