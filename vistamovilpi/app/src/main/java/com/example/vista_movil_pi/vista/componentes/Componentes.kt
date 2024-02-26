package com.example.vista_movil_pi.vista.componentes

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.modelo.Proyecto
import com.example.vista_movil_pi.navegacion.Vistas
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetaMinimizadaFacturas(
    titulo: String,
    total: String,
    alClickar: () -> Unit
) {
    ElevatedCard(
        onClick = alClickar,
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
            .padding(5.dp, bottom = 10.dp),
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
                    text = titulo,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = total,
                    color = colorResource(id = R.color.azul_verdoso),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetaInfo() {
    ElevatedCard(
        //Cambiar info de perfil
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
    ElevatedCard(
        //Cerrar sesion
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
    ElevatedCard(
        //Eliminar cuenta
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
fun TarjetaProyectos(
    uid: String,
    navController: NavController,
    proyecto: Proyecto,
    eliminarProyecto: () -> Unit
) {
    val checkboxStates = remember { mutableStateListOf<Boolean>() }

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
                    text = proyecto.nombre,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = proyecto.subtitulo,
                    color = colorResource(id = R.color.azul_verdoso),
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(top = 30.dp, bottom = 10.dp),
                    text = proyecto.descripcion,
                    color = colorResource(id = R.color.azul_verdoso),
                    fontSize = 16.sp
                )
                proyecto.tareas.forEachIndexed { index, tarea ->
                    Text(
                        text = if (proyecto.tareas[index].importante) "Importante*" else "Estándar",
                        fontSize = 12.sp,
                        color = Color(0xFFEE7E4F)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        if (checkboxStates.size <= index) {
                            checkboxStates.add(false)
                        }
                        Text(
                            text = tarea.nombre,
                            fontSize = 18.sp,
                            textDecoration = if (checkboxStates[index]) TextDecoration.LineThrough else TextDecoration.None,
                            modifier = Modifier
                                .clickable {
                                    checkboxStates.forEachIndexed { i, _ ->
                                        checkboxStates[i] = i == index
                                    }
                                }
                                .weight(1f)
                        )
                        Checkbox(
                            checked = checkboxStates[index],
                            onCheckedChange = {
                                checkboxStates[index] = it
                            },
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = { navController.navigate(Vistas.FormTarea.ruta + "?uid=" + uid + "&pid=" + proyecto._id) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Agregar tarea")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = { eliminarProyecto() },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Eliminar proyecto")
                        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    @DrawableRes icon: Int? = null
) {
    Column {
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(text = label, fontSize = 15.sp) },
            modifier = Modifier.width(330.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedLabelColor = colorResource(id = R.color.azul_oscuro),
                unfocusedLabelColor = colorResource(id = R.color.azul_verdoso),
                focusedIndicatorColor = colorResource(id = R.color.azul_oscuro),
                unfocusedIndicatorColor = colorResource(id = R.color.azul_verdoso),
            ),

            trailingIcon = {
                if (icon != null) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                    )
                }
            }
        )
    }
}

@Composable
fun LoginTriangulo() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        val triangleHeight = size.height
        val triangleBase = size.width
        val strokeWidth = 5.dp.toPx()

        val path = Path().apply {
            moveTo(0f, triangleHeight)
            lineTo(triangleBase / 2, 0f)
            lineTo(triangleBase, triangleHeight)
            close()
        }
        drawLine(
            color = Color.White,
            start = Offset(0f, triangleHeight),
            end = Offset(triangleBase / 2, 0f),
            strokeWidth = strokeWidth
        )
        drawLine(
            color = Color.White,
            start = Offset(triangleBase / 2, 0f),
            end = Offset(triangleBase, triangleHeight),
            strokeWidth = strokeWidth
        )
        drawPath(path = path, color = Color.White, style = Fill)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetaCliente(
    alClickar: () -> Unit,
    funcionBtn1: () -> Unit,
    funcionBtn2: () -> Unit,
    nombre: String,
    apellidos: String
) {
    ElevatedCard(
        onClick = alClickar,
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
            .padding(5.dp, bottom = 10.dp),
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
                .padding(15.dp)
        ) {
            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$nombre $apellidos",
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .padding(end = 5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = funcionBtn1,
                        modifier = Modifier.clip(
                            RoundedCornerShape(50)
                        )
                    ) {
                        Icon(
                            Icons.Rounded.Info,
                            contentDescription = "",
                            tint = colorResource(id = R.color.blanco_claro),
                            modifier = Modifier
                                .size(100.dp, 100.dp)
                                .background(
                                    colorResource(id = R.color.azul_verdoso)
                                )
                        )
                    }
                }
                Column(
                    Modifier
                        .fillMaxHeight()
                        .padding(start = 5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = funcionBtn2,
                        modifier = Modifier.clip(
                            RoundedCornerShape(50)
                        )
                    ) {
                        Icon(
                            Icons.Rounded.Delete,
                            contentDescription = "",
                            tint = colorResource(id = R.color.blanco_claro),
                            modifier = Modifier
                                .size(100.dp, 100.dp)
                                .background(
                                    colorResource(id = R.color.rojo_tomate)
                                )
                        )
                    }
                }
            }
        }
    }
}