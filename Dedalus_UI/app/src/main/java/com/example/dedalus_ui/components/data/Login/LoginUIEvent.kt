package com.example.dedalus_ui.components.data.Login


// UI Event --> whenever the user enters a first name, last name or anything on the screen or Composable or touches any button that is called UI Event.
// in this class we can define all of the events that a user can perform.
sealed class LoginUIEvent {

    // whenever the user changes or enters the first name, this class or event will be triggered.
    data class EmailChanged(val email : String) : LoginUIEvent()
    data class PasswordChanged(val password : String) : LoginUIEvent()


    object LoginButtonClicked : LoginUIEvent()

}
