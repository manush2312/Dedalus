package com.example.dedalus_ui

import android.app.Application
import com.google.firebase.FirebaseApp

class DedalusAppFirebass : Application() {

    // launching point of our application
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }

}