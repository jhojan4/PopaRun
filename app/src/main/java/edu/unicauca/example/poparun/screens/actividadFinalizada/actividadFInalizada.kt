package edu.unicauca.example.poparun.screens.actividadFinalizada

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import edu.unicauca.example.poparun.AppViewModelProvider
import edu.unicauca.example.poparun.viewmodel.ActivityFinishedViewModel
import edu.unicauca.aplimovil.poparun.model.RegistroCronometro

@Composable
fun ActivityFinishedScreen(navController: NavHostController) {
    val viewModel: ActivityFinishedViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val registro = viewModel.ultimoRegistro.value

    registro?.let {
        val duration = formatDuration(it.tiempo)
        val distance = it.distancia
        val avgPace = calculateAvgPace(it.tiempo, it.distancia)
        val calories = estimateCalories(it.tiempo, it.distancia)

        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                        InfoRow(label = "Distancia", value = "%.2f km".format(distance))
                        InfoRow(label = "Ritmo promedio", value = avgPace)
                        InfoRow(label = "Calorías quemadas", value = "$calories kcal")
                    }
                }

                Button(
                    onClick = {
                        navController.navigate("home") {
                            popUpTo("actividadFinalizada") { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar Actividad")
                }

                OutlinedButton(
                    onClick = {
                        navController.navigate("home") {
                            popUpTo("actividadFinalizada") { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Descartar")
                }
            }
        }
    } ?: run {
        // Mostrar un loader mientras se carga el registro
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
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

// 🔧 Función para convertir segundos en formato mm:ss
fun formatDuration(seconds: Long): String {
    val min = seconds / 60
    val sec = seconds % 60
    return "%02d:%02d".format(min, sec)
}

// 🔧 Función para calcular ritmo promedio min/km
fun calculateAvgPace(timeSeconds: Long, distanceKm: Double): String {
    if (distanceKm <= 0) return "--:-- /km"
    val minutes = timeSeconds.toDouble() / 60
    val pace = minutes / distanceKm
    val min = pace.toInt()
    val sec = ((pace - min) * 60).toInt()
    return "%d:%02d /km".format(min, sec)
}

// 🔧 Estimación simple de calorías
fun estimateCalories(timeSeconds: Long, distanceKm: Double): Int {
    return (distanceKm * 60).toInt() // Estimación: 60 kcal por km
}
