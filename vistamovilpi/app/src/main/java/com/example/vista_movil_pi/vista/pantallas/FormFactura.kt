package com.example.vista_movil_pi.vista.pantallas

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.vista.componentes.BotonTonal
import com.example.vista_movil_pi.vista.componentes.InputDelineado

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormFactura(navController: NavController, uid: String){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current
    val clientes = arrayOf("Cliente#1", "Cliente#2", "Cliente#3", "Cliente#4", "Cliente#5")
    var expandido by remember { mutableStateOf(false) }
    var clienteSeleccionado by remember { mutableStateOf("") }
    var listaItemsFact by remember { mutableStateOf (1) }

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
                        "NUEVA FACTURA:",
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
            BottomAppBar(
                containerColor = colorResource(id = R.color.azul_oscuro),
                contentColor = colorResource(id = R.color.blanco_claro  ),
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
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.list_check_solid),
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
            .padding(innerPadding))
        {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 15.dp, end = 15.dp), content = {
                item{

                }
                item {
                    InputDelineado(
                        valor = "",
                        alCambiarValor = {},
                        etiqueta = { Text(text = "Concepto") },
                        placeholder = { Text(text = "Un concepto...") },
                        esContra = false
                    )
                }
                item {
                    InputDelineado(
                        valor = "",
                        alCambiarValor = {},
                        etiqueta = { Text(text = "Descripción") },
                        placeholder = { Text(text = "Una descripción...") },
                        esContra = false
                    )
                }
                item {
                    InputDelineado(
                        valor = "",
                        alCambiarValor = {},
                        etiqueta = { Text(text = "Número") },
                        placeholder = { Text(text = "El nº de la factura...") },
                        esContra = false
                    )
                }
                item {
                    InputDelineado(
                        valor = "",
                        alCambiarValor = {},
                        etiqueta = { Text(text = "Fecha") },
                        placeholder = { Text(text = "La fecha...") },
                        esContra = false
                    )
                }
                item {
                    InputDelineado(
                        valor = "",
                        alCambiarValor = {},
                        etiqueta = { Text(text = "Base imponible") },
                        placeholder = { Text(text = "La base imponible...") },
                        esContra = false
                    )
                }
                item {
                    InputDelineado(
                        valor = "",
                        alCambiarValor = {},
                        etiqueta = { Text(text = "Retención de IRPF") },
                        placeholder = { Text(text = "La retención...") },
                        esContra = false
                    )
                }
                item {
                    InputDelineado(
                        valor = "",
                        alCambiarValor = {},
                        etiqueta = { Text(text = "IVA") },
                        placeholder = { Text(text = "Porcentaje del IVA...") },
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
                            value = clienteSeleccionado,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido) },
                            label = { Text(text = "Remitente de la factura") },
                            placeholder = { Text(text = "Elige remitente...") },
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
                            clientes.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        clienteSeleccionado = item
                                        expandido = false
                                        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                    },
                                    modifier = Modifier
                                        .background(colorResource(id = R.color.blanco_claro))
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                }
                item {
                    BotonTonal(alClickar = { listaItemsFact++ }, valorTexto = "Nuevo item factura")
                }
                items(listaItemsFact){ indice ->
                    InputDelineado(
                        valor = "",
                        alCambiarValor = {},
                        etiqueta = { Text(text = "Concepto item #" + (indice+1)) },
                        placeholder = { Text(text = "El concepto...") },
                        esContra = false
                    )
                    InputDelineado(
                        valor = "",
                        alCambiarValor = {},
                        etiqueta = { Text(text = "Precio item #" + (indice+1)) },
                        placeholder = { Text(text = "El precio...") },
                        esContra = false
                    )
                }
                item {
                    BotonTonal(alClickar = { }, valorTexto = "Registrar factura")
                }
                item{

                }
            })
        }
    }
}