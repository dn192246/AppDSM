package com.example.crud

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController){

    var usuario by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("")}
    val estilo = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        color = Color.White
    )

    //Surface proporciona color de fondo y otras propiedades de superficie. Como un DIV
    Surface(
        //Modifier modifica el componente Surface.
        modifier= Modifier.fillMaxSize(),
        //Se usa el color de fondo que usa el esquema "secondary"
        //color= MaterialTheme.colorScheme.secondary

        color = Color.White
    ){
        //Organiza los elementos hijos de forma vertical
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Veterinaria\nSanta Bárbara",
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            )
            Image(painter = painterResource(id = R.drawable.logo_new), contentDescription = "Logo")

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Inicio de Sesión",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                ))

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color(0xFF003459))
            ){
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Nombre de Usuario",
                        style = estilo
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = usuario,
                        onValueChange = {nuevoUsuario -> usuario = nuevoUsuario},
                        modifier = Modifier.clip(RoundedCornerShape(20.dp))
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = "Contraseña",
                        style = estilo)
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = clave,
                        onValueChange = {nuevaClave -> clave = nuevaClave},
                        modifier = Modifier.clip(RoundedCornerShape(20.dp))
                    )

                    //Botón para Iniciar Sesión
                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { navController.navigate("dashboard_screen")},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF007EA7),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text= "Iniciar Sesión",
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    //Botón para Registrarse

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "¿No tienes una cuenta?",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Center,
                            color= Color.White
                        ))

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF007EA7),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text= "Registrarse",
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}