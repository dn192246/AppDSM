package com.example.crud

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DateInfoScreen(navController: NavController){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Button(
                    onClick = {navController.navigate("dashboard_screen") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Regresar",
                        tint= Color.Black,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Text(text = "Carmen Gutiérrez", textAlign = TextAlign.Center)

                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    )
                ) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Regresar",
                        tint= Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Image(painter = painterResource(id = R.drawable.cat), contentDescription = "Gato")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Nombre de la Mascota: Dharma Gutiérrez",
                style = TextStyle(color = Color.Gray, fontSize = 20.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Cita Previa: 23 de Marzo de 2023",
                style = TextStyle(color = Color.Black, fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.height(23.dp))

            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(35.dp),
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Peso",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Peso",
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.Black),
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "10.4 lbs",
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.DarkGray),
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp
                    )
                }
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(35.dp),
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Tamaño",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Tamaño",
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.Black),
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "77 cm",
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.DarkGray),
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp
                    )
                }

                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(35.dp),
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Edad",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Edad",
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.Black),
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "12 Años",
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.DarkGray),
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp
                    )
                }

                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(35.dp),
                        imageVector = Icons.Filled.FavoriteBorder,
                        contentDescription = "Sexo",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Sexo",
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.Black),
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Hembra",
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.DarkGray),
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(23.dp))

            Text(
                text = "Acerca de la Consulta",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Black
            )

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eros justo, cursus nec nunc interdum, lacinia iaculis velit. Ut auctor ex consequat orci pharetra convallis. Aenean blandit rhoncus nibh.",
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Gray
            )

            Text(
                text = "Receta - Diagnóstico",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Black
            )

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eros justo, cursus nec nunc interdum, lacinia iaculis velit. Ut auctor ex consequat orci pharetra convallis. Aenean blandit rhoncus nibh.",
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {/* */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF003459),
                    contentColor = Color.White
                )
            ) {
                Row{
                    Icon(imageVector = Icons.Filled.DateRange,
                        contentDescription = "Agendar Cita",
                        tint= Color.White,
                        modifier = Modifier.size(40.dp)
                    )

                    Text(
                        text= "Agendar Próxima Cita",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(start= 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}