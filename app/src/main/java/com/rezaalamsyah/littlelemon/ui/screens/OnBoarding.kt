package com.rezaalamsyah.littlelemon.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rezaalamsyah.littlelemon.R
import com.rezaalamsyah.littlelemon.data.model.User
import com.rezaalamsyah.littlelemon.ui.navigation.Destinations
import com.rezaalamsyah.littlelemon.utils.theme.ColorPalette
import com.rezaalamsyah.littlelemon.viewmodel.OnBoardingViewModel
import kotlinx.coroutines.launch

@Composable
fun OnBoarding(navController: NavController, viewModel: OnBoardingViewModel = viewModel()) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var isValidEmail by rememberSaveable { mutableStateOf(true) }
    val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(80.dp)
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little lemon logo",
        )
        Box(
            modifier = Modifier
                .background(ColorPalette.primary1)
                .padding(0.dp, 48.dp, 0.dp, 48.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                color = ColorPalette.highlight1,
                text = stringResource(R.string.on_boarding_title),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Text(
            text = stringResource(R.string.on_boarding_sub_title),
            modifier = Modifier
                .padding(16.dp, 40.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 16.dp),
            value = firstName,
            singleLine = true,
            onValueChange = { firstName = it },
            label = {
                Text(text = stringResource(R.string.on_boarding_form_first_name_label))
            },

            )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp),
            value = lastName,
            onValueChange = { lastName = it },
            singleLine = true,
            label = {
                Text(text = stringResource(R.string.on_boarding_form_last_name_label))
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp),
            value = email,
            singleLine = true,
            onValueChange = {
                email = it
                isValidEmail = emailPattern.matches(it) },
            label = {
                Text(text = stringResource(R.string.on_boarding_form_email_label))
            },
            isError = !isValidEmail,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        if (!isValidEmail) {
            Text(
                text = "Invalid email address",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Button(
            onClick = {
                if (
                    firstName.isNotBlank() &&
                    lastName.isNotBlank() &&
                    email.isNotBlank()
                ) {
                    coroutineScope.launch {
                        //save the user
                        viewModel.saveUser(
                            User(
                                firstName,
                                lastName,
                                email
                            )
                        )
                        Toast.makeText(
                            context,
                            context.getString(R.string.on_boarding_registration_successful_msg),
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.popBackStack()
                        navController.navigate(Destinations.Home.getRoute())
                    }
                } else Toast.makeText(
                    context,
                    context.getString(R.string.on_boarding_invalid_user_input),
                    Toast.LENGTH_LONG
                ).show()

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.filledTonalButtonColors(containerColor = ColorPalette.primary2)
        ) {
            Text(text = stringResource(R.string.on_boarding_form_register_btn_txt))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    OnBoarding(
        navController = rememberNavController(),
        viewModel = viewModel()
    )
}