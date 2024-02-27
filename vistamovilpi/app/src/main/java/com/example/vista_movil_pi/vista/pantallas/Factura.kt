package com.example.vista_movil_pi.vista.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.modelo.Factura
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.viewmodel.FacturaVM
import com.example.vista_movil_pi.viewmodel.ListadoFacturasVM
import com.example.vista_movil_pi.vista.componentes.TarjetaMinimizadaFacturas

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Factura(navController: NavController, viewModel: FacturaVM, uid:String, id:String) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val factura: Factura by viewModel.factura.observeAsState(Factura())
    val impuesto = factura.baseImp*0.21
    val total =  factura.baseImp + impuesto

    LaunchedEffect(Unit) {
        viewModel.getFactura(id)
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
                        "FACTURA",
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
                    IconButton(onClick =  { navController.navigate(Vistas.Opciones.ruta) }) {
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
                        onClick = { navController.navigate(Vistas.FormFactura.ruta)  },
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
            .padding(innerPadding),

            contentAlignment = Alignment.Center
            )
        {
            ElevatedCard (

                modifier = Modifier.padding(10.dp)

            ){
                Column(   modifier = Modifier.padding(20.dp)) {
                    Row (

                        verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween

                    ){
                        Box (

                            modifier = Modifier,

                            ){
                            Text(text = factura.concepto, fontSize = 30.sp,  fontWeight = FontWeight.Bold)
                        }
                        Box (

                            modifier = Modifier
                                .fillMaxWidth(),

                        ){
                            Column(

                                modifier = Modifier.padding(5.dp)

                            ) {
                                Text(text = "Nombre: " + factura.datosEmisor.nombre,textAlign = TextAlign.End , modifier = Modifier.fillMaxWidth(),fontFamily = FontFamily.Monospace, fontSize = 11.sp )
                                Text(text = "NIF: " + factura.datosEmisor.nif,textAlign = TextAlign.End , modifier = Modifier.fillMaxWidth(),fontFamily = FontFamily.Monospace, fontSize = 11.sp )
                                Text(text = "Direccion: " + factura.datosEmisor.direccion,textAlign = TextAlign.End , modifier = Modifier.fillMaxWidth(),fontFamily = FontFamily.Monospace , fontSize = 11.sp)
                                Text(text = "Correo: " + factura.datosEmisor.correo,textAlign = TextAlign.End , modifier = Modifier.fillMaxWidth(), textDecoration = TextDecoration.Underline,fontFamily = FontFamily.Monospace, fontSize = 10.sp )
                                Text(text = "Telefono: " + factura.datosEmisor.telefono,textAlign = TextAlign.End , modifier = Modifier.fillMaxWidth(),fontFamily = FontFamily.Monospace , fontSize = 11.sp)
                            }
                        }
                    }

                    Divider(thickness = 3.dp, color = Color.Black)
                }
                //Factura
                Column(   modifier = Modifier.padding(start = 20.dp , end = 20.dp, bottom = 20.dp)) {
                    Box (

                        modifier = Modifier.padding(bottom = 10.dp)

                        ){
                        Text(text = "Factura # " + factura._id, fontSize = 17.sp,  fontWeight = FontWeight.Bold)
                        Text(text = "Factura # " + factura._id, fontSize = 17.sp,  fontWeight = FontWeight.Bold)
                    }
                    Row (

                        horizontalArrangement = Arrangement.SpaceBetween

                    ){
                        Box (

                            modifier = Modifier,

                            ){
                           Column( modifier = Modifier.padding(5.dp)) {
                               Text(text = "Nombre: " + factura.datosReceptor.nombre, fontSize = 15.sp)
                               Text(text = "NIF: " + factura.datosReceptor.nif, fontSize = 15.sp)
                               Text(text = "Telefono: " + factura.datosReceptor.telefono, fontSize = 15.sp)
                               Text(text = "Dirección: " + factura.datosReceptor.direccion, fontSize = 15.sp)
                           }
                        }
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Divider(thickness = 3.dp, color = Color.Black)
                }

                //Concepto
                Column(   modifier = Modifier.padding(start = 15.dp , end = 10.dp)) {
                    Row (){
                        Column {
                            Box (

                                ){
                                LazyRow(modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth()) {
                                    item { Column(modifier = Modifier
                                        .padding(5.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = "Base \n Imponible", fontSize = 17.sp,  fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                        Spacer(modifier = Modifier.size(10.dp))
                                        Text(text = factura.baseImp.toString()+"€" , fontSize = 15.sp, textAlign = TextAlign.Center)
                                        Spacer(modifier = Modifier.size(15.dp))
                                        Text(text = factura.baseImp.toString()+"€", fontSize = 15.sp,  fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                    }}
                                    item { Column(modifier = Modifier
                                        .padding(5.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = "Impuesto", fontSize = 17.sp,  fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                        Spacer(modifier = Modifier.size(10.dp))
                                        Text(text = "21%", fontSize = 15.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(top =25.dp))
                                    }}
                                    item { Column(modifier = Modifier
                                        .padding(5.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = "Total \n Impuesto", fontSize = 17.sp,  fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                        Spacer(modifier = Modifier.size(10.dp))
                                        Text(text = impuesto.toString() +"€" , fontSize = 15.sp, textAlign = TextAlign.Center)
                                    }}
                                    item { Column(modifier = Modifier
                                        .padding(5.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = "Total", fontSize = 17.sp,  fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                        Spacer(modifier = Modifier.size(10.dp))
                                        Text(text = total.toString()+"€", fontSize = 15.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(top =25.dp))
                                        Spacer(modifier = Modifier.size(15.dp))
                                        Text(text = total.toString()+"€", fontSize = 15.sp,  fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                    }}
                                }

                            }

                        }

                    }

                }

            }

        }

    }



}