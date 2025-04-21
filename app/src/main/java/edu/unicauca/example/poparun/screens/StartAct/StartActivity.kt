package edu.unicauca.example.poparun.screens.StartAct

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compose.onSecondaryContainerLightMediumContrast
import edu.unicauca.example.poparun.R
import edu.unicauca.example.poparun.screens.LabelDatos
import edu.unicauca.example.poparun.screens.Screens
import edu.unicauca.example.poparun.screens.login.buttonRec
import edu.unicauca.example.poparun.screens.login.imagenesPopa



@Composable
fun StartScreen(
    navController: NavHostController,
    viewModel: StartViewModel
) {
    val actividad by viewModel.actividad.collectAsState()
    val duration by viewModel.duration.collectAsState()
    val distance by viewModel.distance.collectAsState()
    val calories by viewModel.calories.collectAsState()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                imagenesPopa(R.drawable.logopopa, 150, modifier = Modifier)
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = stringResource(R.string.seleccionar_actividad))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(800.dp)
                    .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
                    .border(
                        0.3.dp,
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // Opciones: caminar / correr
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .background(MaterialTheme.colorScheme.inversePrimary)
                            .clip(RoundedCornerShape(60.dp))
                    ) {
                        IconButton(
                            onClick = { viewModel.onActividadChange("Correr") },
                            modifier = Modifier.size(150.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.icon_correr_),
                                contentDescription = "Correr",
                                modifier = Modifier
                                    .size(80.dp)
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(30.dp))
                                    .border(
                                        0.5.dp,
                                        MaterialTheme.colorScheme.onSecondaryContainer,
                                        RoundedCornerShape(30.dp)
                                    )
                            )
                        }

                        IconButton(
                            onClick = { viewModel.onActividadChange("Caminar") },
                            modifier = Modifier.size(150.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.icon_caminar_),
                                contentDescription = "Caminar",
                                modifier = Modifier
                                    .size(80.dp)
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(30.dp))
                                    .border(
                                        0.5.dp,
                                        MaterialTheme.colorScheme.onSecondaryContainer,
                                        RoundedCornerShape(30.dp)
                                    )
                            )
                        }
                    }

                    // Campos: duración, distancia, calorías
                    Box {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .background(MaterialTheme.colorScheme.inversePrimary)
                        ) {
                            Spacer(modifier = Modifier.height(10.dp))

                            LabelDatos(
                                textoId = stringResource(R.string.duration),
                                value = duration,
                                onValueChange = { viewModel.onDurationChange(it) },
                                icon = Icons.Default.Star,
                                modifier = Modifier.size(width = 300.dp, height = 50.dp)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            LabelDatos(
                                textoId = stringResource(R.string.distance),
                                value = distance,
                                onValueChange = { viewModel.onDistanceChange(it) },
                                icon = Icons.Default.LocationOn,
                                modifier = Modifier.size(width = 300.dp, height = 50.dp)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            LabelDatos(
                                textoId = stringResource(R.string.Calories),
                                value = calories,
                                onValueChange = { viewModel.onCaloriesChange(it) },
                                icon = Icons.Default.Add,
                                modifier = Modifier.size(width = 300.dp, height = 50.dp)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            // Botón para guardar
                            buttonRec(
                                onClick = {
                                    viewModel.guardarActividad()
                                    navController.popBackStack()
                                },
                                descripton = stringResource(R.string.Start)
                            )

                        }
                    }
                }
            }
        }
    }
}