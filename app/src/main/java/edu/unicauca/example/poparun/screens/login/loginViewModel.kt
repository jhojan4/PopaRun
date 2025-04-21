package edu.unicauca.example.poparun.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unicauca.example.poparun.PopaRunApp
import edu.unicauca.example.poparun.data.user.PopaRunRepository
import edu.unicauca.example.poparun.data.user.user
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.security.MessageDigest

class LoginViewModel(private val repository: PopaRunRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun loginUser(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val email = _uiState.value.email
            val password = _uiState.value.password

            val user: user? = repository.getUserByEmail(email)

            if (user != null && checkPassword(password, user.password)) {
                Log.d("LOGIN", "Login exitoso: ${user.id}")
                PopaRunApp.instance.container.loggedInUserId = user.id
                _uiState.value = _uiState.value.copy(loginSuccess = true, loginError = false)
                onSuccess()
            } else {
                Log.d("LOGIN", "Credenciales incorrectas")
                _uiState.value = _uiState.value.copy(loginError = true)
            }
        }
    }

    private fun checkPassword(inputPassword: String, hashedPassword: String): Boolean {
        return hashPassword(inputPassword) == hashedPassword
    }

    fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val hashBytes = md.digest(password.toByteArray())
        return hashBytes.joinToString("") { "%02x".format(it) }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val loginSuccess: Boolean = false,
    val loginError: Boolean = false
)
