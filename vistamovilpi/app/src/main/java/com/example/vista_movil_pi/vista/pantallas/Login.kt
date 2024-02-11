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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.vista_movil_pi.R
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.viewmodel.LoginVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController, viewModel: LoginVM) {
    val correo : String by viewModel.correo.observeAsState(initial = "")
    val contra : String by viewModel.contra.observeAsState(initial = "")
    val botonActivo : Boolean by viewModel.botonActivo.observeAsState(initial = false)
    val cargando : Boolean by viewModel.cargando.observeAsState(initial = false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(41, 101, 138)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "CRM",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
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
                    fontSize = 30.sp,
                )

                LoginTextField(value = correo, onValueChange = { viewModel.CambiarInputs(it, contra) }, label = "Correo", icon = null,)
                Spacer(modifier = Modifier.height(12.dp))

                LoginTextField(value = contra, onValueChange = { viewModel.CambiarInputs(correo, it) }, label = "Contraseña", icon = R.drawable.hide, esContra = true)
                Spacer(modifier = Modifier.height(25.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .offset(y = 20.dp),
                ) {
                    ElevatedButton(
                        onClick = { viewModel.IntentarInicioSesion(correo, contra, navController) },
                        colors = ButtonDefaults.buttonColors(Color(137, 189, 187)),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .width(330.dp)
                            .height(40.dp),
                        enabled = botonActivo
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
                                navController.navigate(Vistas.Registro.ruta)
                            }
                        )
                    }
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
    @DrawableRes icon: Int? = null,
    esContra: Boolean = false
) {
    Column {
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(text = label, fontSize = 15.sp) },
            modifier = Modifier.width(330.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                unfocusedIndicatorColor = colorResource(id = R.color.azul_oscuro),
                focusedIndicatorColor = colorResource(id = R.color.azul_oscuro),
                focusedLabelColor = colorResource(id = R.color.azul_oscuro),
                unfocusedLabelColor = colorResource(id = R.color.azul_oscuro)
            ),
            trailingIcon = {
                if (icon != null) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                    )
                }
            },
            visualTransformation = if (esContra) PasswordVisualTransformation() else VisualTransformation.None,
        )
    }
}