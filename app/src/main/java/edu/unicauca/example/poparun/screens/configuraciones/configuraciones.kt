package edu.unicauca.example.poparun.screens.configuraciones

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.unicauca.example.poparun.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracionScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    // Estados para los switches
    var notificationsEnabled by remember { mutableStateOf(true) }
    var emailNotifications by remember { mutableStateOf(false) }
    var alertNotifications by remember { mutableStateOf(false) }
    var darkMode by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFA7ECA7) // color de fondo
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Sección superior con nombre, foto y botón
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFA7ECA7), // fondo verde claro
                        RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Logo (usar logopopa)
                    Image(
                        painter = painterResource(id = R.drawable.logopopa),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Nombre Usuario", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Correo", fontSize = 16.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { /* Acción para editar perfil */ }) {
                        Text("Editar Perfil")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de notificaciones
            Text("Configuración de notificaciones", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            SwitchOption("Activar/Desactivar notificaciones", Icons.Filled.Notifications, notificationsEnabled) {
                notificationsEnabled = it
            }

            ConfigOption("Frecuencia de notificaciones", Icons.Filled.Settings) {
                // lógica para frecuencia de notificaciones
            }

            SwitchOption("Notificaciones por correo", Icons.Filled.Email, emailNotifications) {
                emailNotifications = it
            }

            SwitchOption("Activar/Desactivar alertas", Icons.Filled.Notifications, alertNotifications) {
                alertNotifications = it
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de apariencia
            Text("Temas y apariencia", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            SwitchOption("Activar/Desactivar modo oscuro", Icons.Filled.DarkMode, darkMode) {
                darkMode = it
            }

            ConfigOption("Idioma", Icons.Filled.Language) {
                // lógica para cambiar idioma
            }
        }
    }
}

@Composable
fun SwitchOption(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    state: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 16.sp, modifier = Modifier.weight(1f))
        Switch(checked = state, onCheckedChange = onToggle)
    }
}

@Composable
fun ConfigOption(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConfiguracionScreen() {
    // Aquí creamos un navController para usarlo en el preview
    val navController = rememberNavController()

    // Usamos MaterialTheme y Surface para aplicar los estilos predeterminados y asegurar que se vea bien
    MaterialTheme {
        Surface {
            // Llamamos a la función ConfiguracionScreen para mostrar la UI
            ConfiguracionScreen(navController = navController)
        }
    }
}
