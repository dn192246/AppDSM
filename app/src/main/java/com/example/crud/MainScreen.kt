package com.example.crud

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun MainScreen(navController: NavController, modifier: Modifier = Modifier){
    Surface(
        modifier = Modifier.fillMaxSize(),
    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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
                text = "Fomentando la Confianza entre Nuestros Clientes y Mascotas",
                style = TextStyle(
                    fontSize = 27.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(20.dp))
            
            Button(
                onClick = {navController.navigate("login_screen") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF003459),
                    contentColor = Color.White
                )
            ) {
                Row{
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .background(color = Color.White, shape = CircleShape)
                    ){
                        Icon(imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Iniciar Sesión",
                            tint= Color.Blue,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Text(
                        text= "Acceder",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(start= 8.dp)
                    )
                }
            }
        }
    }
}