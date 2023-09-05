package com.example.dedalus_ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dedalus_ui.R
import com.example.dedalus_ui.components.*
import com.example.dedalus_ui.components.data.SignUp.SignUpViewModel
import com.example.dedalus_ui.components.data.SignUp.SignUpUIEvent
import com.example.dedalus_ui.navigation.DedalusRouter
import com.example.dedalus_ui.navigation.Screen

@Composable
// UI part for sign-up screen
fun signUpScreen(loginViewModel: SignUpViewModel = viewModel()) {

    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                NormalTextComponent(value = stringResource(id = R.string.Hey_There))
                HeadingTextComponent(value =  stringResource(id = R.string.Create_an_account))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextField(
                    labelValue = stringResource(id = R.string.first_name),
                    painterResource(id = R.drawable.profile),
                    onTextSelected = {
                        // our composable should be able to tell view model that view model please listen to me user has entered some value please update the UI State
                        // composable want to pass the data to view model
                        loginViewModel.onEvent(SignUpUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = loginViewModel.registrationUIState.value.firstnameError
                )

                MyTextField(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource(id = R.drawable.profile),
                    onTextSelected = {
                        loginViewModel.onEvent(SignUpUIEvent.LastNameChanged(it))
                    },
                    errorStatus = loginViewModel.registrationUIState.value.lastnameError
                )

                MyTextField(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    onTextSelected = {
                        loginViewModel.onEvent(SignUpUIEvent.EmailChanged(it))

                    },
                    errorStatus = loginViewModel.registrationUIState.value.emailError
                )

                MyPassowrdTextField(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.password),
                    onTextSelected = {
                        loginViewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.registrationUIState.value.passwordError
                )

                checkBoxComponent(value = stringResource(id = R.string.terms_and_condition),
                    onTextSelected = {
                        DedalusRouter.navigateTo(Screen.TermsAndConditionScreen)
                    }

                )

                Spacer(modifier = Modifier.height(35.dp))

                ButtonComponent(value = stringResource(id = R.string.register),
                    onButtonClicked = {    // we will invoke this function whenever we click the button
                        loginViewModel.onEvent(SignUpUIEvent.RegisterButtonClicked)

                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(10.dp))

                DividerTextComponent()

                Spacer(modifier = Modifier.height(35.dp))

                ClickableLoginTextComponent( tryingToLogin = true, onTextSelected = {
                    DedalusRouter.navigateTo(Screen.LoginScreen)
                })

            }

        }

        if(loginViewModel.signUpInProgress.value){   // if the signUp is in progress then show the circularIndicator
            CircularProgressIndicator()
        }

    }




}


@Preview
@Composable
fun DefaultPreviewOfSignUpScreen(){
    signUpScreen()
}