package edu.unicauca.example.poparun.register

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unicauca.example.poparun.data.PopaRunRepository
import edu.unicauca.example.poparun.data.user
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.security.MessageDigest

/*class RegisterViewModel(savedStateHandle: SavedStateHandle, private val UserRepository: PopaRunRepository,):ViewModel(){

    val uiState: StateFlow<UserDetailsUiState> =

        UserRepository.getAllItemsStream()
            .filterNotNull().map{
                UserDetailsUiState(outOfStock = it.isEmpty(), UserDetails = it.first().toUserDetails())

            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = UserDetailsUiState()
            )
    suspend fun deleteUser() {
        UserRepository.deleteUser(uiState.value.UserDetails.toUser())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

//Esta data class me permite declarar en vacio los campos los campos que voy a trabajar
data class UserDetails(
    val id: Int = 0,
    val name: String = "",

    val email: String="",
    val country: String="",
    val phoneNumber: String="",
    val password: String=""
)
//Inicializa los campos vacios
data class UserDetailsUiState(
    val outOfStock: Boolean = true,
    val UserDetails: UserDetails = UserDetails()
)
//Esta funcion me permite parsear el valor a una clase user
fun UserDetails.toUser(): user = user(
    id = id,
    name = name,
    email =email,
    country = country,
    phoneNumber=phoneNumber,
    password = password
)
//Esta funcion me permite parsear el valor a una clase UserDetails a user
fun user.toUserDetails(): UserDetails = UserDetails(
    id = id,
    name = name,
    email = email,
    country = country,
    phoneNumber = phoneNumber,
    password = password
)*/
class RegisterViewModel(
    private val repository: PopaRunRepository
) : ViewModel() {
    val passwordMatchError: Boolean
        get() = userDetails.password != userDetails.confirmPassword


    var userDetails by mutableStateOf(UserDetails())
        private set

    var isFormValid by mutableStateOf(false)
        private set

    fun onFieldChange(updated: UserDetails) {
        userDetails = updated
        isFormValid = validateFields(updated)
    }

    private fun validateFields(details: UserDetails): Boolean {
        return details.name.isNotBlank() &&
                details.email.isNotBlank() &&
                details.password.length >= 8 &&
                details.phoneNumber.isNotBlank() &&
                details.password == details.confirmPassword
    }
    fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
    fun saveUser(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val hashedPassword = hashPassword(userDetails.password)
            repository.insertUser(userDetails.copy(password = hashedPassword).toUser())
            onSuccess()
        }

    }

}
data class UserDetails(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val country: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)

fun UserDetails.toUser(): user = user(
    id = id,
    name = name,
    email = email,
    country = country,
    phoneNumber = phoneNumber,
    password = password,
    confirmPassword = confirmPassword
)



