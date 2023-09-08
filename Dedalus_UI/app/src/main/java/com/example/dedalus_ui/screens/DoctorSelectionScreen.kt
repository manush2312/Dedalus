package com.example.dedalus_ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dedalus_ui.R
import com.example.dedalus_ui.components.*
import com.example.dedalus_ui.components.data.DoctorSelection.DoctorScreenViewModel
import com.example.dedalus_ui.components.data.DoctorSelection.DoctorSelectionUIEvent
import kotlinx.coroutines.launch

@Composable
fun DoctorSelectionScreen(doctorViewModel: DoctorScreenViewModel = viewModel()){   // creating a reference of loginView model

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppToolBar(toolbarTitle = stringResource(R.string.home),
                logoutButtonClicked = {
                    doctorViewModel.logout()
                },
                navigationIconClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
                )
        },

        drawerContent = {
            NavigationDrawerHeader()
            NavigationDrawerBody(navigationDrawerItems = doctorViewModel.navigationItemsList,
            onNavigationItemClicked = {
                Log.d("ComingHere", "Inside_onNavigationItemClicked")
                Log.d("ComingHere", "${it.itemId} = ${it.title}")
            })

        }
    )
    {paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

//                HeadingTextForScreensComponent(value = "Enter the basic Details:")

                MyTextField(
                    labelValue = stringResource(R.string.enter_first_name_of_patient),
                    painterResource(id = R.drawable.profile),
                    onTextSelected = {
                        doctorViewModel.onEvent(DoctorSelectionUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = doctorViewModel.doctorSelectionUIState.value.firstnameError
                )

                MyTextField(
                    labelValue = stringResource(R.string.enter_middle_name_of_patient),
                    painterResource(id = R.drawable.profile),
                    onTextSelected = {
                        doctorViewModel.onEvent(DoctorSelectionUIEvent.MiddleNameChanged(it))
                    },
                    errorStatus = doctorViewModel.doctorSelectionUIState.value.middlenameError
                )

                MyTextField(
                    labelValue = stringResource(R.string.enter_last_name_of_patient),
                    painterResource(id = R.drawable.profile),
                    onTextSelected = {
                        doctorViewModel.onEvent(DoctorSelectionUIEvent.LastNameChanged(it))
                    },
                    errorStatus = doctorViewModel.doctorSelectionUIState.value.lastnameError
                )

                TextForAddressField(
                    labelValue = stringResource(R.string.enter_address_of_patient),
                    painterResource(id = R.drawable.location),
                    onTextSelected = {
                        doctorViewModel.onEvent(DoctorSelectionUIEvent.AddressChanged(it))
                    },
                    errorStatus = doctorViewModel.doctorSelectionUIState.value.addressError
                )

                val category = listOf("Pain", "Infected")
                val affected_part = listOf("Knee Pain", "Back Pain", "Shoulder Pain", "Stomach Pain")
                doctorScreenDropDownAppMenu(parentList = category)
                

            }

        }
        
    }

}