package com.example.dedalus_ui.components.data.Rules


// this class will have all the rules which we want to identify our data is valid or not.
// we will create "rules function"
object Validator {

    fun validateFirstName(fName : String) : ValidationResult{
        return ValidationResult(
            (!fName.isNullOrEmpty() && fName.length >= 3)  // first name should not be empty and length should be 6 or more
        )
    }

    fun validateLastName(lName : String) : ValidationResult{
        return ValidationResult(
            (!lName.isNullOrEmpty() && lName.length >= 2)
        )

    }

    fun validateEmail(email : String) : ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty())
        )

    }

    fun validatePassword(password : String) : ValidationResult{
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 4)
        )

    }

//    fun validatePrivacyPolicyAcceptance(statusValue : Boolean) : ValidationResult{
//        return ValidationResult(
//            statusValue
//        )
//    }
}


data class ValidationResult(
    val status : Boolean = false
)