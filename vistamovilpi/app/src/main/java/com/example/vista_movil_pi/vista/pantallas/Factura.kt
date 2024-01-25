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
import androidx.navigation.NavController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.vista.componentes.TarjetaMinimizadaFacturas

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Factura(navController: NavController) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

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
                        "Factura",
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
                        IconButton(onClick = { /* do something */ }, enabled = false) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.receipt_solid),
                                contentDescription = "",
                                tint = colorResource(id = R.color.rojo_tomate)
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
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ },
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
                            Text(text = "Gold", fontSize = 36.sp,  fontWeight = FontWeight.Bold)
                        }
                        Box (

                            modifier = Modifier
                                .fillMaxWidth(),

                        ){
                            Column(

                                modifier = Modifier.padding(5.dp)

                            ) {
                                Text(text = "Emisor",textAlign = TextAlign.End , modifier = Modifier.fillMaxWidth(),fontFamily = FontFamily.Monospace )
                                Text(text = "CIF/NIF",textAlign = TextAlign.End , modifier = Modifier.fillMaxWidth(),fontFamily = FontFamily.Monospace )
                                Text(text = "Direccion",textAlign = TextAlign.End , modifier = Modifier.fillMaxWidth(),fontFamily = FontFamily.Monospace )
                                Text(text = "Correo",textAlign = TextAlign.End , modifier = Modifier.fillMaxWidth(), textDecoration = TextDecoration.Underline,fontFamily = FontFamily.Monospace )
                                Text(text = "Numero",textAlign = TextAlign.End , modifier = Modifier.fillMaxWidth(),fontFamily = FontFamily.Monospace )
                            }
                        }
                    }

                    Divider(thickness = 3.dp, color = Color.Black)
                }
                //Factura
                Column(   modifier = Modifier.padding(start = 30.dp , end = 30.dp, bottom = 20.dp)) {
                    Box (

                        modifier = Modifier.padding(bottom = 10.dp)

                        ){
                        Text(text = "Factura #F190348", fontSize = 25.sp,  fontWeight = FontWeight.Bold)
                    }
                    Row (

                        horizontalArrangement = Arrangement.SpaceBetween

                    ){
                        Box (

                            modifier = Modifier,

                            ){
                           Column( modifier = Modifier.padding(5.dp)) {
                               Text(text = "Nombre", fontSize = 15.sp,  fontWeight = FontWeight.Bold)
                               Text(text = "NIF", fontSize = 15.sp)
                               Text(text = "623495079", fontSize = 15.sp)
                               Text(text = "Domicilio", fontSize = 15.sp)
                           }
                        }
                        Box (

                            modifier = Modifier
                                .fillMaxWidth(),

                            ){
                            Column( modifier = Modifier.padding(5.dp)) {
                                Text(text = "Fecha = xx/xx/xx",textAlign = TextAlign.End , modifier = Modifier.fillMaxWidth())
                                Text(text = "Fecha de vencimiento= xx/xx/xx",textAlign = TextAlign.End , modifier = Modifier.fillMaxWidth())
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
                                    .padding(10.dp)
                                    .fillMaxWidth()) {
                                    item { Column(modifier = Modifier
                                        .padding(5.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = "Base \n Imponible", fontSize = 17.sp,  fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                        Spacer(modifier = Modifier.size(10.dp))
                                        Text(text = "XXX€", fontSize = 15.sp, textAlign = TextAlign.Center)
                                        Spacer(modifier = Modifier.size(15.dp))
                                        Text(text = "XXX€", fontSize = 15.sp,  fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                    }}
                                    item { Column(modifier = Modifier
                                        .padding(5.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = "Impuesto", fontSize = 17.sp,  fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                        Spacer(modifier = Modifier.size(10.dp))
                                        Text(text = "XXX€", fontSize = 15.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(top =25.dp))
                                    }}
                                    item { Column(modifier = Modifier
                                        .padding(5.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = "Total \n Impuesto", fontSize = 17.sp,  fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                        Spacer(modifier = Modifier.size(10.dp))
                                        Text(text = "XXX€", fontSize = 15.sp, textAlign = TextAlign.Center)
                                    }}
                                    item { Column(modifier = Modifier
                                        .padding(5.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = "Total", fontSize = 17.sp,  fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                        Spacer(modifier = Modifier.size(10.dp))
                                        Text(text = "XXX€", fontSize = 15.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(top =25.dp))
                                        Spacer(modifier = Modifier.size(15.dp))
                                        Text(text = "XXX€", fontSize = 15.sp,  fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                    }}
                                }

                            }

                        }

                    }

                }

                Box (

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center

                ){

                    Button(onClick = { /*TODO*/ },) {
                         Text(text = "Ver Conceptos")
                    }

                }

            }

        }

    }



}