package com.example.vista_movil_pi.vista.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.viewmodel.ListadoProyectosVM
import com.example.vista_movil_pi.viewmodel.ProyectoVM
import com.example.vista_movil_pi.vista.componentes.BotonTonal
import com.example.vista_movil_pi.vista.componentes.InputDelineado

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTarea(navController: NavController, viewModel: ListadoProyectosVM, uid: String, pid: String) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val nombreTarea: String by viewModel.nombreTarea.observeAsState(initial = "")

    var expandido by remember { mutableStateOf(false) }
    val opcionImportancia: String by viewModel.opcionImportancia.observeAsState(initial = "")
    var importante: Boolean = false

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
                        "NUEVA TAREA:",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Vistas.ListadoProyectos.ruta) }) {
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
            BottomAppBar(
                containerColor = colorResource(id = R.color.azul_oscuro),
                contentColor = colorResource(id = R.color.blanco_claro),
                actions = {
                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        IconButton(onClick = { /* do something */ }) {
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
                                tint = colorResource(id = R.color.rojo_tomate)
                            )
                        }
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                modifier = Modifier.size(35.dp),
                                imageVector = Icons.Filled.Person,
                                contentDescription = "",
                                tint = colorResource(id = R.color.blanco_claro)
                            )
                        }
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blanco_claro))
            .padding(innerPadding)
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 15.dp, end = 15.dp), content = {
                item{

                }
                item {
                    InputDelineado(
                        valor = nombreTarea,
                        alCambiarValor = { viewModel.cambiarInputs(it) },
                        etiqueta = { Text(text = "Nombre") },
                        placeholder = { Text(text = "") },
                        esContra = false
                    )
                }
                item {
                    ExposedDropdownMenuBox(
                        expanded = expandido,
                        onExpandedChange = {
                            expandido = !expandido
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp)
                    ) {
                        OutlinedTextField(
                            value = opcionImportancia,
                            onValueChange = {  },
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido) },
                            label = { Text(text = "Importancia") },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = colorResource(id = R.color.azul_oscuro),
                                unfocusedBorderColor = colorResource(id = R.color.azul_oscuro),
                                focusedLabelColor = colorResource(id = R.color.azul_oscuro),
                                unfocusedLabelColor = colorResource(id = R.color.azul_verdoso),
                            ),
                        )
                        ExposedDropdownMenu(
                            expanded = expandido,
                            onDismissRequest = { expandido = false },
                        ) {
                            DropdownMenuItem(
                                text = { Text(text = "Importante") },
                                onClick = {
                                    expandido = false
                                    viewModel.cambiarSelect("Importante")
                                    importante = true;
                                },
                                modifier = Modifier
                                    .background(colorResource(id = R.color.blanco_claro))
                                    .fillMaxWidth()
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Estándar") },
                                onClick = {
                                    expandido = false
                                    viewModel.cambiarSelect("Estándar")
                                    importante = false;
                                },
                                modifier = Modifier
                                    .background(colorResource(id = R.color.blanco_claro))
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
                item {
                    BotonTonal(
                        alClickar = {
                            viewModel.agregarTarea(pid, nombreTarea, importante)
                            navController.navigate(Vistas.ListadoProyectos.ruta + "?uid=" + uid)
                        },
                        valorTexto = "Crear tarea"
                    )
                }
            })
        }
    }
}