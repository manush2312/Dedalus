package com.example.dedalus_ui.components.data.SignUp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dedalus_ui.components.data.Rules.Validator
import com.example.dedalus_ui.navigation.DedalusRouter
import com.example.dedalus_ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener


// UI Event --> whenever the user enters a first name, last name or anything on the screen or Composable or touches any button that is called UI Event.

class SignUpViewModel: ViewModel() {

    private val TAG = SignUpViewModel::class.simpleName

    var registrationUIState = mutableStateOf(RegistrationUIState())

    // we are creating a variable that will inform us that all the details are entered correctly or not
    var allValidationsPassed = mutableStateOf(false)

    // creating a state for circularIndicator that will be active when signup is in progress
    var signUpInProgress = mutableStateOf(false)


    // basically when user enters or touches anything on screen this function will be called from respective UIScreen
    fun onEvent(event : SignUpUIEvent){
        when(event){
            is SignUpUIEvent.FirstNameChanged -> {
                // update the value
                registrationUIState.value = registrationUIState.value.copy(
                    firstname = event.firstname
                )
                validateDataWithRules()
                printState()
            }

            is SignUpUIEvent.LastNameChanged -> {
                // update the value
                registrationUIState.value = registrationUIState.value.copy(
                    lastname = event.lastname
                )
                validateDataWithRules()
                printState()
            }

            is SignUpUIEvent.EmailChanged -> {
                // update the value
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                validateDataWithRules()
                printState()
            }

            is SignUpUIEvent.PasswordChanged -> {
                // update the value
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                validateDataWithRules()
                printState()
            }

            is SignUpUIEvent.RegisterButtonClicked -> {
                signUp()
            }

//            is UIEvent.privacyPolicyCheckBoxClicked -> {
//                registrationUIState.value = registrationUIState.value.copy(
//                    privacyPolicyAccepted = event.status
//                )
//            }


        }
    }

    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()

        createUserInFireBase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }

    private fun validateDataWithRules() {
        // in this function we are going to use our validator

        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstname
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastname
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

//        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
//            statusValue = registrationUIState.value.privacyPolicyAccepted
//        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult = $fNameResult")
        Log.d(TAG, "lNameResult = $lNameResult")
        Log.d(TAG, "emailResult = $emailResult")
        Log.d(TAG, "passwordResult = $passwordResult")
        //Log.d(TAG, "privacyPolicyResult = $privacyPolicyResult")


        registrationUIState.value = registrationUIState.value.copy(
            firstnameError =  fNameResult.status,
            lastnameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status
            //privacyPolicyError = privacyPolicyResult.status
        )

        // if all the details entered is true then update "allValidationsPassed"
        if(fNameResult.status && lNameResult.status && emailResult.status && passwordResult.status){
            allValidationsPassed.value = true
        }else{
            allValidationsPassed.value = false
        }


    }

    private fun printState(){
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }



    private fun createUserInFireBase(email : String, password : String){

        signUpInProgress.value = true

        // firebase authentication..
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{  // if everything goes right
                Log.d(TAG, "inside_addOnCompleteListener")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")
                signUpInProgress.value = false

                // whenever our registration process is successful we will go to DoctorSelectionScreen
                if(it.isSuccessful){
                    DedalusRouter.navigateTo(Screen.DoctorSelectionScreen)
                }
            }
            .addOnFailureListener {   // for any error
                Log.d(TAG, "Inside_onFailureListener")
                Log.d(TAG, "Exception = ${it.message}")   // printing the exception if failure occurs
            }

    }


//    fun logout(){
//
//        val firebaseAuth = FirebaseAuth.getInstance()
//
//        firebaseAuth.signOut()   // for signing out..
//
//
//
//        // how we can validate if sign out is successfully done or not
//        val authStateListener = AuthStateListener{
//            if(it.currentUser == null){
//                // this means our signOut is successful
//                Log.d(TAG, "Inside signOut success")
//                DedalusRouter.navigateTo(Screen.LoginScreen)
//            }else{
//                Log.d(TAG, "Inside signOut is not complete")
//            }
//        }
//
//        firebaseAuth.addAuthStateListener (authStateListener)
//    }

}