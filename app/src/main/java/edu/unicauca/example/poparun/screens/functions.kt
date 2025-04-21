package edu.unicauca.example.poparun.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun bottonRedondoStateless(onClick: () -> Unit, icon: ImageVector, colors: Color, modifier: Modifier = Modifier){
    bottonRedondo(onClick = onClick, iconboton = icon,colorsbot = colors,modifier = modifier)
}
//Botones que redondos que permiten cambiar de pantallas o navegar por una pantalla
@Composable
fun bottonRedondo(onClick: () -> Unit, iconboton: ImageVector, colorsbot: Color, modifier: Modifier = Modifier){
    Button(onClick = onClick,colors = ButtonDefaults.buttonColors(colorsbot), contentPadding = PaddingValues(5.dp),modifier = modifier) {
        Icon(
            imageVector = iconboton,
            contentDescription = null,
            modifier = modifier
        )
    }
}
//Permite crear las cajas de texto de inicio de sesion
@Composable
fun LabelDatos(
    textoId:String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    esPassword: Boolean = false,
    modifier: Modifier = Modifier) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = if (esPassword) PasswordVisualTransformation() else VisualTransformation.None,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.1f)
        ),
        placeholder = {
            Text(textoId)
        },
        modifier = modifier
            .heightIn(min=20.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .border(0.3.dp, MaterialTheme.colorScheme.scrim
                , RoundedCornerShape(30.dp)
            )
            .clip(RoundedCornerShape(30.dp))

    )
}