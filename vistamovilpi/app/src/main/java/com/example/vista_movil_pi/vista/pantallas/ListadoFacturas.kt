package com.example.vista_movil_pi.vista.pantallas

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.modelo.Factura
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.viewmodel.ListadoFacturasVM
import com.example.vista_movil_pi.vista.componentes.TarjetaMinimizadaFacturas
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoFacturas(navController: NavController, viewModel: ListadoFacturasVM, uid: String){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val facturas: List<Factura> by viewModel.facturas.observeAsState(emptyList())
    val cargando: Boolean by viewModel.cargando.observeAsState(initial = false)
    var soloRecibidas by remember { mutableStateOf(false) }
    var scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {

        viewModel.getFacturas(uid)

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
                        "TUS FACTURAS:",
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
                contentColor = colorResource(id = R.color.blanco_claro  ),
                actions = {
                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        IconButton(onClick = { /* do something */ }, enabled = false) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.receipt_solid),
                                contentDescription = "",
                                tint = colorResource(id = R.color.rojo_tomate)
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
                        onClick = { navController.navigate(Vistas.FormFactura.ruta + "?uid=" + uid) },
                        containerColor = colorResource(id = R.color.blanco_claro),
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(modifier = Modifier.size(30.dp), painter = painterResource(id = R.drawable.circle_plus_solid), contentDescription = "", tint = colorResource(id = R.color.azul_oscuro))
                    }
                }
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blanco_claro))
            .padding(innerPadding))
        {
            LazyColumn(modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 15.dp, end = 15.dp)
                .fillMaxSize(),
                content = {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FilterChip(
                                onClick = { soloRecibidas = !soloRecibidas },
                                label = {
                                    Text("Solo recibidas")
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = colorResource(id = R.color.azul_verdoso),
                                    iconColor = colorResource(id = R.color.azul_oscuro),
                                ),
                                selected = soloRecibidas,
                                leadingIcon = if (soloRecibidas) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.ShoppingCart,
                                            contentDescription = "",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                },
                            )
                            Spacer(modifier = Modifier.padding(end = 15.dp))
                            FilterChip(
                                onClick = { soloRecibidas = !soloRecibidas },
                                label = {
                                    Text("Solo emitidas")
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = colorResource(id = R.color.azul_verdoso),
                                    iconColor = colorResource(id = R.color.azul_oscuro),
                                ),
                                selected = !soloRecibidas,
                                leadingIcon = if (!soloRecibidas) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.Edit,
                                            contentDescription = "",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                },
                            )
                        }
                    }

                    val factorIVA = 0.21
                    val factorIRPF = 0.15

                    items(facturas.size) { indice ->
                        val baseImp = facturas[indice].baseImp
                        val iva = baseImp * factorIVA
                        val irpf = baseImp * factorIRPF
                        val total = baseImp + iva - irpf

                        if (!soloRecibidas){
                            if (facturas[indice].datosEmisor._id == uid){
                                TarjetaMinimizadaFacturas(facturas[indice].concepto,
                                    "$total €", alClickar = {
                                    navController.navigate(Vistas.Factura.ruta + "?id=" + facturas[indice]._id + "&uid=" + uid)
                                }, funcionEliminar = {viewModel.deleteFactura(facturas[indice]._id, navController, uid)}, completada = facturas[indice].completada, modifier = Modifier
                                    .height(IntrinsicSize.Min)
                                    .wrapContentHeight()
                                    .padding(5.dp, bottom = 10.dp), alCheckear = {
                                        scope.launch {
                                            if (facturas[indice].completada){
                                                val factura = Factura(completada = false)
                                                viewModel.putFactura(facturas[indice]._id, uid, factura, navController)
                                                Log.d("hecho", "Se pondría en false")
                                            } else {
                                                val factura = Factura(completada = true)
                                                viewModel.putFactura(facturas[indice]._id, uid, factura, navController)
                                                Log.d("hecho", "Se pondría en true")
                                            }
                                        }
                                    })
                            }
                        } else {
                            if (facturas[indice].datosEmisor._id != uid){
                                TarjetaMinimizadaFacturas(facturas[indice].concepto,
                                    "$total €", alClickar = {
                                    navController.navigate(Vistas.Factura.ruta + "?id=" + facturas[indice]._id + "&uid=" + uid)
                                }, funcionEliminar = {viewModel.deleteFactura(facturas[indice]._id, navController, uid)}, esEmitida = false, completada = facturas[indice].completada, modifier = Modifier
                                        .height(IntrinsicSize.Min)
                                        .wrapContentHeight()
                                        .padding(5.dp, bottom = 10.dp),
                                    alCheckear = {})
                            }
                        }
            }})
        }
    }
}