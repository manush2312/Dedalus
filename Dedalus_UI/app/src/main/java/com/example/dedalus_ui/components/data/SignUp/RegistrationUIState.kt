package com.example.dedalus_ui.components.data.SignUp


// whatever the data user will enter we can hold inside this UI State
data class RegistrationUIState(
    var firstname : String = "",
    var lastname : String = "",
    var email : String = "",
    var password : String = "",
    //var privacyPolicyAccepted : Boolean = false,

    // variables for error state
    var firstnameError : Boolean = false,
    var lastnameError : Boolean = false,
    var emailError : Boolean = false,
    var passwordError : Boolean = false,

    // we are defining a variable for checkbox, i.e. if checkbox is selected then only "Register Button" clickable
    //var privacyPolicyError : Boolean = false
)