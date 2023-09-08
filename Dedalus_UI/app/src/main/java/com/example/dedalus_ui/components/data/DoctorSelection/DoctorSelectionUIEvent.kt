package com.example.dedalus_ui.components.data.DoctorSelection

import com.example.dedalus_ui.components.data.SignUp.SignUpUIEvent

sealed class DoctorSelectionUIEvent {

    data class FirstNameChanged(val firstname : String) : DoctorSelectionUIEvent()
    data class MiddleNameChanged(val middlename : String) : DoctorSelectionUIEvent()
    data class LastNameChanged(val lastname : String) : DoctorSelectionUIEvent()
    data class AddressChanged(val address : String) : DoctorSelectionUIEvent()


}