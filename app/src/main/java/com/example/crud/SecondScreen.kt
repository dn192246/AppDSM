package com.example.crud


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SecondScreen(navController: NavController){
    //Surface proporciona color de fondo y otras propiedades de superficie. Como un DIV
    Surface(
        //Modifier modifica el componente Surface.
        modifier= Modifier.fillMaxSize(),
        //Se usa el color de fondo que usa el esquema "secondary"
        color= MaterialTheme.colorScheme.secondary
    ){
        //Organiza los elementos hijos de forma vertical
        Column(modifier = Modifier.padding(16.dp)){
            Text("Segunda Pantalla")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {navController.navigate("main_screen")}){
                Text("Go to Main Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("third_screen") }) {
                Text("Ir a tercera pantalla")
            }
        }
    }
}