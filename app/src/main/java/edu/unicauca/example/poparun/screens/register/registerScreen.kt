package edu.unicauca.example.poparun.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.unicauca.example.poparun.screens.LabelDatos
import edu.unicauca.example.poparun.R
import edu.unicauca.example.poparun.screens.Screens
import edu.unicauca.example.poparun.screens.bottonRedondoStateless
import edu.unicauca.example.poparun.screens.login.buttonRec
import edu.unicauca.example.poparun.screens.login.imagenesPopa
@Composable
fun registerScreen(navController: NavHostController, viewModel: RegisterViewModel, modifier: Modifier =Modifier){
    Box(modifier = modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
        Box(modifier= Modifier.align(Alignment.TopStart).padding(10.dp)){
            bottonRedondoStateless(onClick={navController.navigate(Screens.PresentacionScreen.name)},
                Icons.Default.ArrowBack,colors =MaterialTheme.colorScheme.tertiary, modifier = Modifier
                .size(width = 40.dp, height = 40.dp)
            )
        }
        Card_Registro(navController,viewModel,modifier.align(Alignment.Center))
    }
}
@Composable
fun Card_Registro(navController: NavHostController,
                  viewModel: RegisterViewModel,
                  modifier: Modifier = Modifier) {
    Box(modifier=modifier) {
        val user = viewModel.userDetails

        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(900.dp)
                .padding(horizontal = 35.dp, vertical = 40.dp)
                .border(0.3.dp, MaterialTheme.colorScheme.scrim,RoundedCornerShape(10.dp)),

            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(0.4f)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp,vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally){

                imagenesPopa(R.drawable.logopopa, 150)
                Spacer(modifier = Modifier.height(30.dp))
                LabelDatos(stringResource(R.string.Users),
                    value = user.name,
                    onValueChange = {viewModel.onFieldChange(user.copy(name = it))},
                    Icons.Default.AccountCircle,
                    modifier = Modifier.size(width = 400.dp, height = 50.dp))
                Spacer(modifier = Modifier.height(30.dp),)
                LabelDatos(
                    stringResource(R.string.Email),
                    value = user.email,
                    onValueChange = {viewModel.onFieldChange(user.copy(email = it))},
                    Icons.Default.Email,
                    modifier = Modifier.size(width = 400.dp, height = 50.dp))
                Spacer(modifier = Modifier.height(30.dp))
                LabelDatos(
                    stringResource(R.string.Pais),
                    value = user.country,
                    onValueChange = {viewModel.onFieldChange(user.copy(country = it))},
                    Icons.Default.LocationOn,
                    modifier = Modifier.size(width = 400.dp, height = 50.dp))
                Spacer(modifier = Modifier.height(30.dp))
                LabelDatos(
                    stringResource(R.string.PhoneNumber),
                    value = user.phoneNumber,
                    onValueChange = {viewModel.onFieldChange(user.copy(phoneNumber = it))},
                    Icons.Default.Call,
                    modifier = Modifier.size(width = 400.dp, height = 50.dp))
                Spacer(modifier = Modifier.height(30.dp))
                LabelDatos(
                    stringResource(R.string.Password),
                    value = user.password,
                    onValueChange = {viewModel.onFieldChange(user.copy(password = it))},
                    Icons.Default.Lock,
                    esPassword = true,
                    modifier = Modifier.size(width = 400.dp, height = 50.dp))
                Spacer(modifier = Modifier.height(30.dp))
                LabelDatos(
                    stringResource(R.string.RepeatPassword),
                    value = user.confirmPassword,
                    onValueChange = {viewModel.onFieldChange(user.copy(confirmPassword = it))},
                    Icons.Default.Lock,
                    esPassword = true,
                    modifier = Modifier.size(width = 400.dp, height = 50.dp))

                if (viewModel.passwordMatchError) {
                    Text(
                        text = "Las contraseñas no coinciden",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                buttonRec(
                    onClick = {
                        viewModel.saveUser {
                            navController.navigate(Screens.LoginScreen.name) // o la pantalla de inicio real
                        }
                    },
                    stringResource(R.string.buttom_iniciar_sesion)
                )

            }
        }
    }
}