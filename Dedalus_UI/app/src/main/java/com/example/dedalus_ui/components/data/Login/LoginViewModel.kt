package com.example.dedalus_ui.components.data.Login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dedalus_ui.components.data.Rules.Validator
import com.example.dedalus_ui.navigation.DedalusRouter
import com.example.dedalus_ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    // we are creating a variable that will inform us that all the details are entered correctly or not
    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event : LoginUIEvent){
        when(event){

            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )

            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }

        validateLoginUIDataWithRules()
    }



    private fun validateLoginUIDataWithRules(){
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        // updating loginUI state globally
        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status   // if both the values are true then "allValidationPassed.value = true"
    }


    private fun login() {

        loginInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_login_success")
                Log.d(TAG, "${it.isSuccessful}")

                if(it.isSuccessful){
                    loginInProgress.value = false
                    DedalusRouter.navigateTo(Screen.DoctorSelectionScreen)
                }
            }
            .addOnFailureListener{
                Log.d(TAG, "Inside_login_failure")
                Log.d(TAG, "${it.localizedMessage}")
                loginInProgress.value = false
            }
    }

}