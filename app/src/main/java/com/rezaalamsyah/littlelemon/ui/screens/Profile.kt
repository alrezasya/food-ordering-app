package com.rezaalamsyah.littlelemon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rezaalamsyah.littlelemon.R
import com.rezaalamsyah.littlelemon.data.model.User
import com.rezaalamsyah.littlelemon.ui.navigation.Destinations
import com.rezaalamsyah.littlelemon.utils.theme.ColorPalette
import com.rezaalamsyah.littlelemon.viewmodel.ProfileViewModel

@Composable
fun Profile(navController: NavController, viewModel: ProfileViewModel = viewModel()) {
    val user by viewModel.user.collectAsStateWithLifecycle()
    ProfileUI(user = user) {
        viewModel.logOut()

        navController.popBackStack(Destinations.Home.getRoute(), true)
        navController.navigate(Destinations.OnBoarding.getRoute())
    }
}

@Composable
fun ProfileUI(user: User?, logOut: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "little lemon logo",
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 32.dp)
                .align(Alignment.CenterHorizontally)
                .height(80.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.user_profile_title),
            modifier = Modifier
                .padding(0.dp, 40.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        UserInfoLabel(label = stringResource(R.string.user_profile_first_name_label))
        UserInfoText(info = user?.firstName ?: "")

        Spacer(modifier = Modifier.height(32.dp))

        UserInfoLabel(label = stringResource(R.string.user_profile_last_name_label))
        UserInfoText(info = user?.lastName ?: "")

        Spacer(modifier = Modifier.height(32.dp))

        UserInfoLabel(label = stringResource(R.string.user_profile_email_label))
        UserInfoText(info = user?.email ?: "")

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { logOut() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 16.dp),
            colors = ButtonDefaults.filledTonalButtonColors(containerColor = ColorPalette.primary2),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = stringResource(R.string.user_profile_log_out_btn_txt))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileUI(
        User("Prem", "Thakur", "prem@example.com")
    ) {}
}

@Composable
fun UserInfoLabel(
    label: String
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyMedium,
        color = ColorPalette.primary1
    )
}

@Composable
fun UserInfoText(
    info: String
) {
    Text(
        text = info,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .padding(0.dp, 8.dp)
            .fillMaxWidth()
            .border(1.dp, ColorPalette.primary1, shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    )
}