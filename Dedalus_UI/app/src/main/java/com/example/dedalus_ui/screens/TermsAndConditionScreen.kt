package com.example.dedalus_ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dedalus_ui.R
import com.example.dedalus_ui.app.DedalusApp
import com.example.dedalus_ui.components.HeadingTextComponent
import com.example.dedalus_ui.navigation.DedalusRouter
import com.example.dedalus_ui.navigation.Screen
import com.example.dedalus_ui.navigation.SystemBackButtonHandler


@Composable
fun TermsAndConditionScreen(){

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(16.dp)
    ) {

        HeadingTextComponent(value = stringResource(id = R.string.terms_and_condition_header))

    }

    SystemBackButtonHandler{
        // we need to specify where we need to go
        DedalusRouter.navigateTo(Screen.signUpScreen)
    }
}