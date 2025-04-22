package edu.unicauca.example.poparun.screens.actividadFinalizada

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun ActivityFinishedScreen(
    navController: NavHostController,
    duration: String,
    distanceKm: Double,
    avgPace: String,
    calories: Int
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*Image(
                painter = painterResource(id = R.drawable.finish_icon), // Usa un ícono decorativo
                (contentDescription = "Actividad finalizada",
                modifier = Modifier.size(120.dp)
            )*/

            Text(
                text = "¡Actividad Finalizada!",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    InfoRow(label = "Duración", value = duration)
                    InfoRow(label = "Distancia", value = "%.2f km".format(distanceKm))
                    InfoRow(label = "Ritmo promedio", value = avgPace)
                    InfoRow(label = "Calorías quemadas", value = "$calories kcal")
                }
            }

            Button(
                onClick = {
                    // TODO: guardar la actividad en la base de datos
                    navController.navigate("home") {
                        popUpTo("activityFinished") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Actividad")
            }

            OutlinedButton(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("activityFinished") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Descartar")
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 16.sp)
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Preview(showBackground = true, name = "Actividad Finalizada Preview")
@Composable
fun ActivityFinishedScreenPreview() {
    val fakeNavController = rememberNavController()

    ActivityFinishedScreen(
        navController = fakeNavController,
        duration = "42:17",
        distanceKm = 6.75,
        avgPace = "6:15 /km",
        calories = 450
    )
}
