package com.example.vista_movil_pi.vista.pantallas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registro(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(137, 189, 187)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        var correo by remember { mutableStateOf("") }
        var contrasena by remember { mutableStateOf("") }

        Text(
            text = "Nombre de la APP",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .width(95.dp)
                .offset(y = -75.dp)
        )
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
                color = Color(0xFFb82418),
                start = Offset(0f, triangleHeight),
                end = Offset(triangleBase / 2, 0f),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = Color(0xFFb82418),
                start = Offset(triangleBase / 2, 0f),
                end = Offset(triangleBase, triangleHeight),
                strokeWidth = strokeWidth
            )
            drawPath(path = path, color = Color(0xFFE85A50), style = Fill)
        }
        Box(
            modifier = Modifier
                .background(Color(0xFFE85A50))
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "¡Regístrate!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .offset(y = -50.dp)
                )
                Column(

                ) {
                    TextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo") },
                        modifier = Modifier
                            .width(330.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            unfocusedIndicatorColor = Color.Black
                        )
                    )
                    Text(
                        text = "Obligatorio*",
                        fontSize = 12.sp,
                    )
                }
                Column(
                    modifier = Modifier
                        .offset(y = 12.dp)
                ) {
                    TextField(
                        value = contrasena,
                        onValueChange = { contrasena = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier
                            .width(330.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            unfocusedIndicatorColor = Color.Black
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                    )
                    Text(
                        text = "Obligatorio*",
                        fontSize = 12.sp,
                    )
                }
                ElevatedButton(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(Color(137, 189, 187)),
                    modifier = Modifier
                        .width(330.dp)
                        .offset(y = 45.dp)
                ) {
                    Text("Registrar")
                }
                Row(
                    modifier = Modifier
                        .offset(y = 55.dp)
                ) {
                    Text(
                        text = "¿Ya tienes una cuenta? "
                    )
                    Text(
                        text = "Inicia sesión",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}