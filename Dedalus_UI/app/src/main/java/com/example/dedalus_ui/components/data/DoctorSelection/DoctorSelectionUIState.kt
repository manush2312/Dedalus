package com.example.dedalus_ui.components.data.DoctorSelection

data class DoctorSelectionUIState (

    var firstname : String = "",
    var middlename : String = "",
    var lastname : String = "",
    var address : String = "",
    var phoneNumber : String = "",
    var age : String = "",


    var firstnameError : Boolean = false,
    var middlenameError : Boolean = false,
    var lastnameError : Boolean = false,
    var addressError : Boolean = false,
    var phoneNumberError : Boolean = false,
    var ageError : Boolean = false


)
