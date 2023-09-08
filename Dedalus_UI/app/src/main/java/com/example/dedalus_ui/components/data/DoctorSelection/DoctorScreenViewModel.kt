package com.example.dedalus_ui.components.data.DoctorSelection

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dedalus_ui.components.data.Rules.Validator
import com.example.dedalus_ui.components.data.SignUp.RegistrationUIState
import com.example.dedalus_ui.navigation.DedalusRouter
import com.example.dedalus_ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class DoctorScreenViewModel : ViewModel() {

    private val TAG = DoctorScreenViewModel::class.simpleName

    var doctorSelectionUIState = mutableStateOf(DoctorSelectionUIState())

    var allValidationsPassed = mutableStateOf(false)

    val navigationItemsList = listOf<NavigationItem>(
        NavigationItem(
            title = "Home",
            icon = Icons.Default.Home,
            description = "Home Screen",
            itemId = "homeScreen"
        ),
        NavigationItem(
            title = "Settings",
            icon = Icons.Default.Settings,
            description = "Settings Screen",
            itemId = "settingScreen"
        ),
        NavigationItem(
            title = "Home",
            icon = Icons.Default.Favorite,
            description = "Favourite Screen",
            itemId = "favouriteScreen"
        )
    )

    fun logout(){

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()   // for signing out..



        // how we can validate if sign out is successfully done or not
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                // this means our signOut is successful
                Log.d(TAG, "Inside signOut success")
                DedalusRouter.navigateTo(Screen.LoginScreen)
            } else {
                Log.d(TAG, "Inside signOut is not complete")
            }
        }

        firebaseAuth.addAuthStateListener (authStateListener)
    }


    fun onEvent(event : DoctorSelectionUIEvent){
        when(event){
            is DoctorSelectionUIEvent.FirstNameChanged -> {
                doctorSelectionUIState.value = doctorSelectionUIState.value.copy(
                    firstname = event.firstname
                )
                validateDataWithRules()
                printState()
            }

            is DoctorSelectionUIEvent.LastNameChanged ->{
                doctorSelectionUIState.value = doctorSelectionUIState.value.copy(
                    lastname = event.lastname
                )
                validateDataWithRules()
                printState()
            }

            is DoctorSelectionUIEvent.AddressChanged ->{
                doctorSelectionUIState.value = doctorSelectionUIState.value.copy(
                    address = event.address
                )
                validateDataWithRules()
                printState()
            }

            is DoctorSelectionUIEvent.MiddleNameChanged ->{
                doctorSelectionUIState.value = doctorSelectionUIState.value.copy(
                    middlename = event.middlename
                )
                validateDataWithRules()
                printState()
            }
        }
    }


    private fun printState(){
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, doctorSelectionUIState.value.toString())
    }


    private fun validateDataWithRules() {
        // in this function we are going to use our validator

        val fNameResult = Validator.validateFirstName(
            fName = doctorSelectionUIState.value.firstname
        )

        val lNameResult = Validator.validateLastName(
            lName = doctorSelectionUIState.value.lastname
        )


        val mNameResult = Validator.validateMiddleName(
            mName = doctorSelectionUIState.value.middlename
        )


        val addressResult = Validator.validateAddress(
            addr = doctorSelectionUIState.value.address
        )



        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult = $fNameResult")
        Log.d(TAG, "lNameResult = $lNameResult")
        Log.d(TAG, "mNameResult = $mNameResult")
        Log.d(TAG, "addressResult = $addressResult")



        doctorSelectionUIState.value = doctorSelectionUIState.value.copy(
            firstnameError =  fNameResult.status,
            lastnameError = lNameResult.status,
            middlenameError = mNameResult.status,
            addressError = addressResult.status
        )

        // if all the details entered is true then update "allValidationsPassed"
        if(fNameResult.status && lNameResult.status && mNameResult.status && addressResult.status){
            allValidationsPassed.value = true
        }else{
            allValidationsPassed.value = false
        }

    }






}