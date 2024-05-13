package com.example.crud
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen(navController: NavController){
    val coloresBotones = ButtonDefaults.buttonColors(
        containerColor = Color(0xF6F6F6),
        contentColor = Color.Black
    )

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Bienvenido, Dr. Juan",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color= Color.Black
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Image(painter = painterResource(id = R.drawable.logo_new), contentDescription = "Logo")

            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .background(Color(0xF6F6F6))
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Gestionar Pacientes",
                            style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Start)
                        )
                    }

                    //Botones para Mantenimiento de Pacientes
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        //Botón Buscar
                        Button(
                            onClick = { /*TODO*/ },
                            colors = coloresBotones
                        ) {
                            Column (
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
                                Icon(imageVector = Icons.Filled.Search,
                                    contentDescription = "Buscar",
                                    tint= Color.Black,
                                    modifier = Modifier.size(60.dp)
                                )
                                Text(text = "Buscar", style = TextStyle(fontSize = 20.sp))
                            }
                        }

                        //Botón Editar
                        Button(
                            onClick = { /*TODO*/ },
                            colors = coloresBotones
                        ) {
                            Column (
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
                                Icon(imageVector = Icons.Filled.Edit,
                                    contentDescription = "Editar",
                                    tint= Color.Black,
                                    modifier = Modifier.size(60.dp)
                                )
                                Text(text = "Editar", style = TextStyle(fontSize = 20.sp))
                            }
                        }

                        //Botón Agregar
                        Button(
                            onClick = { /*TODO*/ },
                            colors = coloresBotones
                        ){
                            Column (
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
                                Icon(imageVector = Icons.Filled.AddCircle,
                                    contentDescription = "Agregar",
                                    tint= Color.Black,
                                    modifier = Modifier.size(60.dp)
                                )
                                Text(text = "Agregar", style = TextStyle(fontSize = 20.sp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Próximas Citas",
                            style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Start)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    //Tarjeta de Cita Próxima
                    Button(
                        onClick = { navController.navigate("dateinfo_screen") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.DarkGray
                        ),
                        elevation = ButtonDefaults.buttonElevation(15.dp,0.dp),
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Filled.Person,
                                contentDescription = "Avatar",
                                tint= Color.Black,
                                modifier = Modifier.size(48.dp).weight(0.2f)
                            )

                            Spacer(modifier = Modifier.width(12.dp))
                            
                            Text(
                                text = "Carmen Gutiérrez",
                                style = TextStyle(color = Color.Gray),
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.5f)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column (
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(0.3f)
                            ){
                                
                                Icon(imageVector = Icons.Filled.DateRange,
                                    contentDescription = "Calendar",
                                    tint= Color.Black,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                
                                Text(text = "3 Abril 2024",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center)

                                Spacer(modifier = Modifier.height(18.dp))

                                Text(text = "10:00 AM",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    //Tarjeta de Cita Próxima
                    Button(
                        onClick = { navController.navigate("dateinfo_screen") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.DarkGray
                        ),
                        elevation = ButtonDefaults.buttonElevation(15.dp,0.dp),
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Filled.Person,
                                contentDescription = "Avatar",
                                tint= Color.Black,
                                modifier = Modifier.size(48.dp).weight(0.2f)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = "Carmen Gutiérrez",
                                style = TextStyle(color = Color.Gray),
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.5f)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column (
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(0.3f)
                            ){

                                Icon(imageVector = Icons.Filled.DateRange,
                                    contentDescription = "Calendar",
                                    tint= Color.Black,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.height(24.dp))

                                Text(text = "3 Abril 2024",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center)

                                Spacer(modifier = Modifier.height(18.dp))

                                Text(text = "10:00 AM",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    //Tarjeta de Cita Próxima
                    Button(
                        onClick = { navController.navigate("dateinfo_screen") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.DarkGray
                        ),
                        elevation = ButtonDefaults.buttonElevation(15.dp,0.dp),
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Filled.Person,
                                contentDescription = "Avatar",
                                tint= Color.Black,
                                modifier = Modifier.size(48.dp).weight(0.2f)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = "Carmen Gutiérrez",
                                style = TextStyle(color = Color.Gray),
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.5f)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column (
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(0.3f)
                            ){

                                Icon(imageVector = Icons.Filled.DateRange,
                                    contentDescription = "Calendar",
                                    tint= Color.Black,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.height(24.dp))

                                Text(text = "3 Abril 2024",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center)

                                Spacer(modifier = Modifier.height(18.dp))

                                Text(text = "10:00 AM",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }
        }
    }
}
