package com.example.vista_movil_pi.vista.pantallas

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.modelo.Cliente
import com.example.vista_movil_pi.modelo.Factura
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.viewmodel.FormFacturaVM
import com.example.vista_movil_pi.vista.componentes.BotonTonal
import com.example.vista_movil_pi.vista.componentes.InputDelineado
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormFactura(navController: NavController, uid: String, viewModel: FormFacturaVM){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current
    var expandido by remember { mutableStateOf(false) }
    val cargando: Boolean by viewModel.cargando.observeAsState(false)
    val clientes: MutableList<Cliente>? by viewModel.clientes.observeAsState(null)
    val remitenteElegido: Cliente by viewModel.remitenteElegido.observeAsState(Cliente())
    val remitente: String by viewModel.remitente.observeAsState("")
    val botonActivo: Boolean by viewModel.botonActivo.observeAsState(false)
    val concepto: String by viewModel.concepto.observeAsState("")
    val descripcion: String by viewModel.descripcion.observeAsState("")
    val fecha: String by viewModel.fecha.observeAsState("")
    val baseImponible: String by viewModel.baseImponible.observeAsState("")
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getClientes(uid)
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
                        valor = concepto,
                        alCambiarValor = {
                            viewModel.CambiarInputs(it, descripcion, fecha, baseImponible, remitenteElegido)
                        },
                        etiqueta = { Text(text = "Concepto") },
                        placeholder = { Text(text = "Un concepto...") },
                        esContra = false
                    )
                }
                item {
                    InputDelineado(
                        valor = descripcion,
                        alCambiarValor = { viewModel.CambiarInputs(concepto, it, fecha, baseImponible, remitenteElegido)},
                        etiqueta = { Text(text = "Descripción") },
                        placeholder = { Text(text = "Una descripción...") },
                        esContra = false
                    )
                }
                item {
                    InputDelineado(
                        valor = fecha,
                        alCambiarValor = {viewModel.CambiarInputs(concepto, descripcion, it, baseImponible, remitenteElegido)},
                        etiqueta = { Text(text = "Fecha") },
                        placeholder = { Text(text = "La fecha...") },
                        esContra = false
                    )
                }
                item {
                    InputDelineado(
                        valor = baseImponible,
                        alCambiarValor = {viewModel.CambiarInputs(concepto, descripcion, fecha, it, remitenteElegido)},
                        etiqueta = { Text(text = "Base imponible") },
                        placeholder = { Text(text = "La base imponible...") },
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
                            value = remitente,
                            onValueChange = {  },
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
                            clientes?.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = "${item.nombre} ${item.apellidos}") },
                                    onClick = {
                                        viewModel.CambiarInputs(concepto, descripcion, fecha, baseImponible, item)
                                        expandido = false
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
                    BotonTonal(alClickar =
                    {
                        val factura: Factura = Factura(concepto, false, descripcion,
                            Cliente(uid), "", fecha, baseImponible.toDouble(), Cliente(remitenteElegido._id)
                        )

                        scope.launch{viewModel.postFactura(uid, factura, navController)}
                    }, valorTexto = "Registrar factura", estaActivo = botonActivo)
                }
            })
        }
    }
}