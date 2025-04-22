package edu.unicauca.example.poparun.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.unicauca.example.poparun.R
import edu.unicauca.example.poparun.data.actividades.Actividades
import edu.unicauca.example.poparun.data.actividades.ActividadesRepository
import edu.unicauca.example.poparun.screens.Screens
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel() // Usar ViewModel
) {
    // Observamos datos del ViewModel
    val userName by viewModel.userName.collectAsState()
    val dailyGoalCalories by viewModel.dailyGoalCalories.collectAsState()
    val totalCalories by viewModel.totalCalories.collectAsState()
    val completedWorkouts by viewModel.completedWorkouts.collectAsState()
    val totalWorkouts by viewModel.totalWorkouts.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi App para Trotar") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Hola, $userName\n¿Listo para trotar hoy?",
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Imagen central (usa logopopa si ya existe en tu drawable)
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.logopopa),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center)
                )
            }

            // Muestra de progreso general
            ProgressSummary(
                dailyGoalCalories = dailyGoalCalories,
                totalCalories = totalCalories,
                completedWorkouts = completedWorkouts,
                totalWorkouts = totalWorkouts
            )

            // Botón para registrar entrenamiento
            NewWorkoutButton(navController)
        }
    }
}

@Composable
fun NewWorkoutButton(navController: NavHostController) {
    Button(
        onClick = { navController.navigate(Screens.StartScreen.name) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text("Registrar Nuevo Entrenamiento")
    }
}

@Composable
fun ProgressSummary(
    dailyGoalCalories: Int,
    totalCalories: Int,
    completedWorkouts: Int,
    totalWorkouts: Int
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Tu Progreso",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Objetivo calórico diario", fontSize = 16.sp)
                Text("$dailyGoalCalories kcal", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calorías consumidas", fontSize = 16.sp)
                Text("$totalCalories kcal", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Entrenamientos completados", fontSize = 16.sp)
                Text("$completedWorkouts/$totalWorkouts", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}


