package com.example.vista_movil_pi.vista.pantallas

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.navegacion.Vistas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(41, 101, 138)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        var correo by remember { mutableStateOf("") }
        var contrasena by remember { mutableStateOf("") }

        Text(
            text = "Nombre de la APP",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .width(95.dp)
                .offset(y = -75.dp)
        )

        LoginTriangulo()

        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Iniciar sesión",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.offset(y = -50.dp)
                )

                LoginTextField(value = correo, onValueChange = { nuevoValor -> correo = nuevoValor }, label = "Correo", icon = null)
                Spacer(modifier = Modifier.height(12.dp))

                LoginTextField(value = contrasena, onValueChange = { nuevoValor -> contrasena = nuevoValor }, label = "Contraseña", icon = R.drawable.hide)
                Spacer(modifier = Modifier.height(30.dp))

                Spacer(modifier = Modifier.height(80.dp))
                ElevatedButton(
                    onClick = { navController.navigate("ListadoFacturas") },
                    colors = ButtonDefaults.buttonColors(Color(137, 189, 187)),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .width(330.dp)
                        .height(40.dp)
                ) {
                    Text(
                        text = "Iniciar sesión",
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(
                        text = "¿No tienes una cuenta? ",
                        color = Color(150,150,150),
                    )
                    Text(
                        text = "Regístrate",
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable {
                            navController.navigate("Registro")
                        }
                    )
                }
            }
        }
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
                unfocusedIndicatorColor = Color(150,150,150)
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