package com.example.dedalus_ui.components.data

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.lifecycle.ViewModel
import com.example.dedalus_ui.components.data.SignUp.SignUpViewModel
import com.example.dedalus_ui.navigation.DedalusRouter
import com.example.dedalus_ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class DoctorScreenViewModel : ViewModel() {

    private val TAG = DoctorScreenViewModel::class.simpleName

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

}