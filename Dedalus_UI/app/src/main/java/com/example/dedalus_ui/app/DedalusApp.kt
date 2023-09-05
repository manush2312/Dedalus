package com.example.dedalus_ui.app

import androidx.compose.animation.Crossfade
import androidx.compose.material.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.dedalus_ui.navigation.DedalusRouter
import com.example.dedalus_ui.navigation.Screen
import com.example.dedalus_ui.screens.DoctorSelectionScreen
import com.example.dedalus_ui.screens.LoginScreen
import com.example.dedalus_ui.screens.TermsAndConditionScreen
import com.example.dedalus_ui.screens.signUpScreen

@Composable
fun DedalusApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ){

        // Router : for navigating between the Screens
        Crossfade(targetState = DedalusRouter.currentScreen) { currentState ->
            when(currentState.value){
                is Screen.signUpScreen ->{
                    signUpScreen()
                }
                is Screen.TermsAndConditionScreen ->{
                    TermsAndConditionScreen()
                }
                is Screen.LoginScreen ->{
                    LoginScreen()
                }
                is Screen.DoctorSelectionScreen ->{
                    DoctorSelectionScreen()
                }
            }
            
        }
    }

}