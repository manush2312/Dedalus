package com.example.dedalus_ui.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(){

    object signUpScreen : Screen()
    object TermsAndConditionScreen : Screen()
    object LoginScreen : Screen()
    object DoctorSelectionScreen : Screen()

}

object DedalusRouter{

    // this is our default screen or first screen
    var currentScreen : MutableState<Screen> = mutableStateOf(Screen.DoctorSelectionScreen)

    // whatever destination we pass in this method that becomes the current screen
    fun navigateTo(destination : Screen){
        currentScreen.value = destination
    }

}