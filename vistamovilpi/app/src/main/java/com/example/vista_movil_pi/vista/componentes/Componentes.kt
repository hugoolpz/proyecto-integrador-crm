package com.example.vista_movil_pi.vista.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vista_movil_pi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetaMinimizadaFacturas(){

    ElevatedCard(
        onClick = {
        },
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.blanco_claro),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Absolute.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(
                    painter = painterResource(id = R.drawable.receipt_solid),
                    contentDescription = "",
                    tint = colorResource(id = R.color.azul_verdoso),
                    modifier = Modifier.size(30.dp)
                )
            }
            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Título de la factura...",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Total de la factura...",
                    color = colorResource(id = R.color.azul_verdoso),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetaInfo(){
    ElevatedCard(          //Cambiar info de perfil
        onClick = {
        },
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.blanco_claro),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Absolute.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(
                    painter = painterResource(id = R.drawable.circle_user_solid),
                    contentDescription = "",
                    tint = colorResource(id = R.color.azul_verdoso),
                    modifier = Modifier.size(60.dp)
                )
            }
            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Cambiar info de perfil",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }
    }
    ElevatedCard(          //Cerrar sesion
        onClick = {
        },
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.blanco_claro),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Absolute.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(
                    painter = painterResource(id = R.drawable.log_out_solid),
                    contentDescription = "",
                    tint = colorResource(id = R.color.azul_verdoso),
                    modifier = Modifier.size(60.dp)
                )
            }
            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Cerrar sesión",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }
    }
    ElevatedCard(          //Eliminar cuenta
        onClick = {
        },
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.blanco_claro),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Absolute.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(
                    painter = painterResource(id = R.drawable.delete_user_solid),
                    contentDescription = "",
                    tint = colorResource(id = R.color.azul_verdoso),
                    modifier = Modifier.size(60.dp)
                )
            }
            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Eliminar cuenta",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetaProyectos() {
    //var isChecked by remember { mutableStateOf(false) }
    val checkboxStates = remember { mutableStateListOf(false, false, false, false) }

    ElevatedCard(

        onClick = {
        },
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.blanco_claro),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Absolute.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Título del proyecto...",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Subtitulo del proyecto...",
                    color = colorResource(id = R.color.azul_verdoso),
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(top = 30.dp, bottom = 10.dp),
                    text = "Descripción del proyecto...",
                    color = colorResource(id = R.color.azul_verdoso),
                    fontSize = 16.sp
                )
                Text(
                    text = "Importante*",
                    fontSize = 12.sp
                )
                for (i in 0 until 4) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Título de tarea ${i + 1}",
                            fontSize = 18.sp,
                            textDecoration = if (checkboxStates[i]) TextDecoration.LineThrough else TextDecoration.None,
                            modifier = Modifier.clickable {
                                checkboxStates.forEachIndexed { index, _ ->
                                    checkboxStates[index] = index == i
                                }
                            }
                        )
                        Checkbox(
                            checked = checkboxStates[i],
                            onCheckedChange = {
                                checkboxStates[i] = it
                            },
                            modifier = Modifier.padding(start = 150.dp, end = 50.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDelineado(
    valor: String,
    alCambiarValor: (String) -> Unit,
    modifier: Modifier = Modifier,
    estaActivo: Boolean = true,
    etiqueta: @Composable (() -> Unit),
    placeholder: @Composable (() -> Unit),
    colorBordeEnfocado: Color = colorResource(id = R.color.azul_oscuro),
    colorBordeDesenfocado: Color = colorResource(id = R.color.azul_oscuro),
    colorEtiquetaEnfocada: Color = colorResource(id = R.color.azul_verdoso),
    colorEtiquetaDesenfocada: Color = colorResource(id = R.color.azul_verdoso),
    textoAyuda: @Composable (() -> Unit)? = null,
    tipoInput: KeyboardType = KeyboardType.Text,
    esContra: Boolean,
    unaSolaLinea: Boolean = true
) {
    OutlinedTextField(
        value = valor,
        onValueChange = { alCambiarValor(it) },
        modifier = modifier.fillMaxWidth(),
        enabled = estaActivo,
        label = etiqueta,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorBordeEnfocado,
            unfocusedBorderColor = colorBordeDesenfocado,
            focusedLabelColor = colorEtiquetaEnfocada,
            unfocusedLabelColor = colorEtiquetaDesenfocada,
        ),
        supportingText = textoAyuda,
        keyboardOptions = if (esContra) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions(
            keyboardType = tipoInput
        ),
        placeholder = placeholder,
        visualTransformation = if (esContra) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = unaSolaLinea
    )
}

@Composable
fun BotonTonal(
    alClickar: () -> Unit,
    modifier: Modifier = Modifier,
    estaActivo: Boolean = true,
    colorContenedor: Color = colorResource(id = R.color.azul_oscuro),
    colorContenido: Color = colorResource(id = R.color.blanco_claro),
    colorContenedorDesactivado: Color = colorResource(id = R.color.azul_verdoso),
    colorContenidoDesactivado: Color = colorResource(id = R.color.blanco_claro),
    valorTexto: String
) {
    FilledTonalButton(
        onClick = alClickar,
        modifier = modifier,
        enabled = estaActivo,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = colorContenedor,
            contentColor = colorContenido,
            disabledContainerColor = colorContenedorDesactivado,
            disabledContentColor = colorContenidoDesactivado,
        ),
        elevation = ButtonDefaults.filledTonalButtonElevation(),
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = valorTexto,
                fontWeight = FontWeight.Bold,
            )
            Icon(
                painter = painterResource(id = R.drawable.circle_plus_solid),
                contentDescription = "",
                tint = colorContenido,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}