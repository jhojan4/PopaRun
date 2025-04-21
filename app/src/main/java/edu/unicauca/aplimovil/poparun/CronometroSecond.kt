package edu.unicauca.aplimovil.poparun

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.unicauca.aplimovil.poparun.viewmodel.CronometroViewModel

@Composable
fun CronometroPantalla() {

    val viewModel: CronometroViewModel = viewModel()
    val tiempo by viewModel.tiempo.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatTiempo(tiempo),
            fontSize = 48.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row {
            Button(onClick = { viewModel.iniciar() }) {
                Text("Iniciar")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { viewModel.detener() }) {
                Text("Detener")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { viewModel.reiniciar() }) {
                Text("Reiniciar")
            }
        }
    }
}

    fun formatTiempo(segundos: Long): String {
        val minutos = segundos / 60
        val seg = segundos % 60
        return String.format("%02d:%02d", minutos, seg)
    }


@Preview(showBackground = true)
@Composable
fun PreviewCronometroPantalla() {
    CronometroPantalla()
}