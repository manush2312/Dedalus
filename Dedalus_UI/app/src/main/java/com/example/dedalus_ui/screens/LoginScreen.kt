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
import com.example.dedalus_ui.components.data.Login.LoginUIEvent
import com.example.dedalus_ui.components.data.Login.LoginViewModel
import com.example.dedalus_ui.navigation.DedalusRouter
import com.example.dedalus_ui.navigation.Screen
import com.example.dedalus_ui.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()){

   Box(modifier = Modifier.fillMaxSize(),
   contentAlignment = Alignment.Center)
   {

       Surface(
           modifier = Modifier
               .fillMaxSize()
               .background(Color.White)
               .padding(28.dp)
       ){
           Column(modifier = Modifier
               .fillMaxSize()) {

               NormalTextComponent(value = stringResource(id = R.string.Hey_There))

               HeadingTextComponent(value = stringResource(id = R.string.welcome_back))

               Spacer(modifier = Modifier.height(20.dp))

               MyTextField(labelValue = stringResource(id = R.string.email),
                   painterResource(id = R.drawable.message ),
                   onTextSelected = {
                       loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                   },
                   errorStatus = loginViewModel.loginUIState.value.emailError
               )

               MyPassowrdTextField(labelValue = stringResource(id = R.string.password),
                   painterResource(id = R.drawable.password ),
                   onTextSelected = {
                       loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it)) },
                   errorStatus = loginViewModel.loginUIState.value.passwordError
               )

               Spacer(modifier = Modifier.height(40.dp))

               UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

               Spacer(modifier = Modifier.height(40.dp))

               ButtonComponent(value = stringResource(id = R.string.login),
                   onButtonClicked = {   // we will invoke this function whenever we click the button
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked )
                   },
                   isEnabled = loginViewModel.allValidationsPassed.value)

               Spacer(modifier = Modifier.height(10.dp))

               DividerTextComponent()

               ClickableLoginTextComponent( tryingToLogin = false, onTextSelected = {
                   DedalusRouter.navigateTo(Screen.signUpScreen)
               })

           }


       }

       if(loginViewModel.loginInProgress.value){
           CircularProgressIndicator()
       }


   }

    //if we press the back button then we will be directed to SignUpScreen.
    SystemBackButtonHandler {
        DedalusRouter.navigateTo(Screen.signUpScreen)
    }



}


@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}