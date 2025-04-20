package edu.unicauca.example.poparun.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.PopaRunTheme
import edu.unicauca.example.poparun.LabelDatos
import edu.unicauca.example.poparun.R
import edu.unicauca.example.poparun.Screens
import edu.unicauca.example.poparun.bottonRedondoStateless

@Composable
fun loginScreen(navController: NavHostController, modifier: Modifier=Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {

        //Botton superior
        Box(modifier= Modifier
            .align(Alignment.TopStart)
            .padding(30.dp)){
            bottonRedondoStateless(onClick={navController.navigate(Screens.PresentacionScreen.name)},
                Icons.Default.ArrowBack,
                colors = MaterialTheme.colorScheme.tertiary, modifier = Modifier
                .size(width = 40.dp, height = 40.dp)
            )
        }



        // Tarjeta de Inicio de Sesión centrada en la pantalla
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card_InicioSesion(navController)
        }
    }
}

@Composable
fun Card_InicioSesion(navController: NavController, modifier: Modifier=Modifier) {
    Box(modifier=modifier){
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(580.dp)
                .padding(20.dp)
                .border(0.3.dp, MaterialTheme.colorScheme.scrim,RoundedCornerShape(10.dp)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(0.4f)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp,vertical = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                imagenesPopa(R.drawable.logopopa, 150)
                Spacer(modifier = Modifier.height(40.dp))
                /*LabelDatos(
                    stringResource(R.string.Users),
                    Icons.Default.AccountCircle,
                    modifier = Modifier.size(width = 400.dp, height = 50.dp))
                Spacer(modifier = Modifier.height(15.dp))
                LabelDatos(
                    stringResource(R.string.Password),Icons.Default.Lock,
                    modifier = Modifier.size(width = 400.dp, height = 50.dp))
                Spacer(modifier = Modifier.height(25.dp))

                buttonRec(onClick = {navController.navigate(Screens.RegistroScreen.name)},
                    stringResource(R.string.buttom_iniciar_sesion), modifier = Modifier
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(stringResource(R.string.sinCuenta),modifier = Modifier.clickable { navController.navigate(Screens.RegistroScreen.name) })
            */}
        }

    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    PopaRunTheme {
        val navController = rememberNavController()
        loginScreen(navController = navController, modifier = Modifier)
    }
}

@Composable
fun imagenesPopa(imagenurl:Int,sizeImage: Int){
    Image(painter = painterResource(imagenurl),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(sizeImage.dp)
            .clip(CircleShape))
}

@Composable
fun buttonRec(onClick: () -> Unit, descripton: String,modifier: Modifier = Modifier){
    Button(onClick = onClick,modifier = modifier) {
        Text(descripton)
    }
}

