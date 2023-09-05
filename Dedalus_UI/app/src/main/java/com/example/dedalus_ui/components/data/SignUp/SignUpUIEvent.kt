package com.example.dedalus_ui.components.data.SignUp


// UI Event --> whenever the user enters a first name, last name or anything on the screen or Composable or touches any button that is called UI Event.
// in this class we can define all of the events that a user can perform.
sealed class SignUpUIEvent {

    // whenever the user changes or enters the first name, this class or event will be triggered.
    data class FirstNameChanged(val firstname : String) : SignUpUIEvent()
    data class LastNameChanged(val lastname : String) : SignUpUIEvent()
    data class EmailChanged(val email : String) : SignUpUIEvent()
    data class PasswordChanged(val password : String) : SignUpUIEvent()


    object RegisterButtonClicked : SignUpUIEvent()

}
