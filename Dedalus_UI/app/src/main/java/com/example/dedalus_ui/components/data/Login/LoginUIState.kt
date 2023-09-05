package com.example.dedalus_ui.components.data.Login


// whatever the data user will enter we can hold inside this UI State
data class LoginUIState(
    var email : String = "",
    var password : String = "",


    // variables for error state
    var emailError : Boolean = false,
    var passwordError : Boolean = false


)