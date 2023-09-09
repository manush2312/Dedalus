package com.example.dedalus_ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dedalus_ui.R
import com.example.dedalus_ui.components.data.DoctorSelection.NavigationItem
import com.example.dedalus_ui.ui.theme.*


// components package contains the composable elements that are common to both the screens.

@Composable
fun NormalTextComponent(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),   // heightIn --> minimum height for the text view but if content is getting out of range it will expand
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )

}



@Composable
fun HeadingTextComponent(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),   // heightIn --> minimum height for the text view but if content is getting out of range it will expand
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )

}

@Composable
fun MyTextField(labelValue:String, painterResource: Painter, onTextSelected: (String) -> Unit,
                errorStatus : Boolean = false
                ){

    val textValue = remember{   // remember function --> remembers the last value entered by the user
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            backgroundColor = BgColor

        ),
        // for adding "NEXT" button in our keyboard
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,    // for adding "NEXT" button in our keyboard
        maxLines = 1 ,     // for adding "NEXT" button in our keyboard

        value = textValue.value,
        onValueChange ={     // whenever user enter anything onValueChange will trigger
            textValue.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )
    
}

@Composable
fun MyPassowrdTextField(labelValue:String, painterResource: Painter, onTextSelected: (String) -> Unit,
                        errorStatus : Boolean = false
                        ){

    val localFocusManager = LocalFocusManager.current   // for shutting down the keyboard when we have entered password

    val password = remember{   // remember function --> remembers the last value entered by the user
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)   // initially password is not visible
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            backgroundColor = BgColor

        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        keyboardActions = KeyboardActions{
                                         localFocusManager.clearFocus()  // for shutting down the keyboard when we have entered password
        },
        maxLines = 1,
        value = password.value,
        onValueChange ={
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if(passwordVisible.value){
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }

            var description = if(passwordVisible.value){
                stringResource(id = R.string.hide_password)
            }else{
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
                
            }
        },

        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )

}


@Composable
fun checkBoxComponent(value:String, onTextSelected: (String) -> Unit){
    
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        val checkedState = remember{
            mutableStateOf(false)
        }

        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = !checkedState.value
            }
        )

        ClickableTextComponent(value = value, onTextSelected)

    }
}

@Composable
fun ClickableTextComponent(value:String, onTextSelected: (String) -> Unit){

    val initialText = "By continuing you accpet our "
    val privacyPolicyText = "Privacy Policy "
    val andText = "and "
    val termsAndConditionText = "Term of Use"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = termsAndConditionText, annotation = termsAndConditionText)
            append(termsAndConditionText)
        }
    }

    ClickableText(text = annotatedString, onClick = {offset ->

        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span->
                Log.d("ClickableTextComponent", "{$span}")

                if((span.item == termsAndConditionText) || (span.item == privacyPolicyText)){
                    onTextSelected(span.item)
                }
            }

    })

}


@Composable
fun ButtonComponent(value : String, onButtonClicked : () -> Unit, isEnabled : Boolean = false){
    Button(
    modifier = Modifier
        .fillMaxWidth()
        .heightIn(48.dp),
        onClick = { onButtonClicked.invoke() },
        contentPadding = PaddingValues(),
        enabled = isEnabled,    // if all of the entered details are fine then register or login
        colors = ButtonDefaults.buttonColors(Color.Transparent)  // we are adding transparent color to add gradient later on
    ) {
        
        // we are gradient
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){

            Text(text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)

        }

    }
}


@Composable
fun DividerTextComponent(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {

        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        color = GrayColor,
        thickness = 1.dp
        )
    
        Text(modifier = Modifier.padding(18.dp),
            text = "or",
            fontSize = 18.sp,
            color = TextColor)

        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = GrayColor,
            thickness = 1.dp
        )
        
    }
}


@Composable
fun ClickableLoginTextComponent(tryingToLogin : Boolean = true, onTextSelected: (String) -> Unit){

    val initialText = if(tryingToLogin) "Already have an account? "  else "Don't have an account yet? "

    val loginText = if(tryingToLogin) "Login"  else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }

    }

    ClickableText(modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 40.dp),   // heightIn --> minimum height for the text view but if content is getting out of range it will expand
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = {offset ->

        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span->
                Log.d("ClickableTextComponent", "{$span}")

                if(span.item == loginText){
                    onTextSelected(span.item)
                }
            }

    })

}


@Composable
fun UnderLinedTextComponent(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),   // heightIn --> minimum height for the text view but if content is getting out of range it will expand
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = GrayColor,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )

}

@Composable
fun AppToolBar(toolbarTitle : String, logoutButtonClicked : () -> Unit, navigationIconClicked : () -> Unit){
    
   TopAppBar(
       backgroundColor = Primary,
       title = {
           Text(text = toolbarTitle, color = WhiteColor)
       },
       navigationIcon = {
           
           IconButton(onClick = {
                navigationIconClicked.invoke()
           })
           {
               Icon(
                   imageVector = Icons.Filled.Menu,
                   contentDescription = stringResource(id = R.string.menu),
                   tint = WhiteColor

               )
           }
           

       },
       actions = {
           IconButton(onClick = {
               logoutButtonClicked.invoke()
           }) {
               Icon(imageVector = Icons.Filled.Logout, contentDescription = stringResource(id = R.string.logout))
           }

       }

   
   )
       
}


@Composable
fun NavigationDrawerHeader(){
        Box(modifier = Modifier
            .background(
                Brush.horizontalGradient(
                    listOf(Primary, Secondary)
                )
            )
            .fillMaxWidth()

            .padding(32.dp))
        {

            NavigationDrawerText(title = stringResource(R.string.navigation_header), 24.sp)

        }
}

@Composable
fun NavigationDrawerBody(navigationDrawerItems : List<NavigationItem>, onNavigationItemClicked : (NavigationItem) -> Unit){
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        items(navigationDrawerItems){
            NavigationItemRow(item = it, onNavigationItemClicked)
        }
    }
}


@Composable
fun NavigationItemRow(item : NavigationItem, onNavigationItemClicked : (NavigationItem) -> Unit){



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigationItemClicked.invoke(item)
            }
            .padding(all = 16.dp)
    ) {
        
        Icon(imageVector = item.icon, contentDescription = item.description)
        
        Spacer(modifier = Modifier.width(18.dp))

        NavigationDrawerText(title = item.title, 18.sp)
        
    }
}


@Composable
// this composable is all about the "TEXT" in the navigationToolBar
fun NavigationDrawerText(title: String, textUnit: TextUnit){

    val shadowOffset = Offset(4f, 6f)  // for adding shadows to our text component

    Text(text = title, style = TextStyle(
        color = Color.Black,
        fontSize =textUnit,
        fontStyle = FontStyle.Normal,
        shadow = Shadow(
            color = Primary,
            offset = shadowOffset, 2f
        )
    )
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun doctorScreenDropDownAppMenu(parentList : List<String>)
{

    var expandedState by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(parentList[0]) }
    var mContext = LocalContext.current


    var childOptions = remember{
        mutableStateListOf<String>("Knee", "Back", "Shoulder", "Stomach", "Broken Bone", "Blood Injury")
    }
    var expandedStateChild by remember { mutableStateOf(false) }
    var selectedOptionChild by remember { mutableStateOf(childOptions[0]) }

    
    ExposedDropdownMenuBox(
        modifier = Modifier
            .clip(RoundedCornerShape(20))
            .background(Color.White)
            .padding(top = 16.dp, bottom = 16.dp),
        expanded = expandedState,
        onExpandedChange = {
            expandedState = !expandedState
        },
    )
    {
        
        TextField(value = selectedOption, onValueChange = {}, 
                    trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedState)},
                    readOnly = true,
                    textStyle = TextStyle.Default.copy(fontSize = 20.sp),
                    modifier = Modifier.background(WhiteColor),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                focusedLabelColor = Primary,
                cursorColor = Primary,
                backgroundColor = BgColor

            )
        )
        
        
        ExposedDropdownMenu(
            expanded = expandedState,
            onDismissRequest = { expandedState = false },
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .background(Color.White)
            ) {

            parentList.forEach{
                eachoption -> DropdownMenuItem(onClick = {
                    selectedOption = eachoption
                expandedState = false
                Toast.makeText(mContext, ""+selectedOption, Toast.LENGTH_SHORT).show()

                // adding the dependancy list based on parent list
                childOptions.clear()

                if(selectedOption.equals("Pain")){
                        childOptions.add("Knee")
                        childOptions.add("Back")
                        childOptions.add("Shoulder")
                        childOptions.add("Stomach")
                        childOptions.add("Broken Bone")
                        childOptions.add("Blood Injury")

                }else{
                    childOptions.add("Fever")
                    childOptions.add("Cold")
                    childOptions.add("Cough")
                    childOptions.add("Conjuctivital")
                    childOptions.add("Typhoid")
                    childOptions.add("Cholera")
                }

            }) {
                    Text(text = eachoption, fontSize = 20.sp)
            }
            }
            
        }
        
    }


    // child dropdownMenu
    ExposedDropdownMenuBox(
        expanded = expandedStateChild,
        onExpandedChange = {expandedStateChild = !expandedStateChild},
        modifier = Modifier
            .clip(RoundedCornerShape(20))
            .background(Color.White)
            .padding(bottom = 16.dp)
    )
    {
        TextField(value = selectedOptionChild, onValueChange = {},
            trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedStateChild)},
            readOnly = true,
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            modifier = Modifier.background(WhiteColor),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                focusedLabelColor = Primary,
                cursorColor = Primary,
                backgroundColor = BgColor

            )
        )


        ExposedDropdownMenu(
            expanded = expandedStateChild,
            onDismissRequest = { expandedStateChild = false },
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .background(Color.White)
            )
        {

            childOptions.forEach {
                    eachoption -> DropdownMenuItem(onClick = {
                selectedOptionChild = eachoption
                expandedStateChild = false
                Toast.makeText(mContext, ""+selectedOptionChild, Toast.LENGTH_SHORT).show()
            })
            {
                Text(text = eachoption, fontSize = 20.sp)
            }

            }

        }


    }




}

@Composable
fun TextForAddressField(labelValue:String, painterResource: Painter, onTextSelected: (String) -> Unit,
                errorStatus : Boolean = false
){

    val textValue = remember{   // remember function --> remembers the last value entered by the user
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            backgroundColor = BgColor

        ),
        // for adding "NEXT" button in our keyboard
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,    // for adding "NEXT" button in our keyboard
        maxLines = 3 ,     // for adding "NEXT" button in our keyboard

        value = textValue.value,
        onValueChange ={     // whenever user enter anything onValueChange will trigger
            textValue.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )

}


@Composable
fun NumberComponent(labelValue:String, painterResource: Painter, onTextSelected: (String) -> Unit,
                        errorStatus : Boolean = false
){

    val textValue = remember{   // remember function --> remembers the last value entered by the user
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            backgroundColor = BgColor

        ),
        // for adding "NEXT" button in our keyboard
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
        singleLine = true,    // for adding "NEXT" button in our keyboard
        maxLines = 1 ,     // for adding "NEXT" button in our keyboard

        value = textValue.value,
        onValueChange ={     // whenever user enter anything onValueChange will trigger
            textValue.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )

}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownAppMenu(placeholderList : String, parentList : List<String>)
{

    var expandedState by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    var mContext = LocalContext.current



    ExposedDropdownMenuBox(
        modifier = Modifier
            .clip(RoundedCornerShape(20))
            //.background()
            .padding(top = 16.dp, bottom = 16.dp),
        expanded = expandedState,
        onExpandedChange = {
            expandedState = !expandedState
        },
    )
    {

        TextField(value = selectedOption, onValueChange = {},
            placeholder = { Text(placeholderList) },
            trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedState)},
            readOnly = true,
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            modifier = Modifier.background(Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                focusedLabelColor = Primary,
                cursorColor = Primary,
                backgroundColor = BgColor

            )
        )


        ExposedDropdownMenu(
            expanded = expandedState,
            onDismissRequest = { expandedState = false },
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .background(Color.White)
        ) {

            parentList.forEach{
                    eachoption -> DropdownMenuItem(onClick = {
                selectedOption = eachoption
                expandedState = false
                Toast.makeText(mContext, ""+selectedOption, Toast.LENGTH_SHORT).show()



            }) {
                Text(text = eachoption, fontSize = 20.sp)
            }
            }

        }

    }







}











