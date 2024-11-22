package inoxoft.simon.matwana.view.pages.Auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import inoxoft.simon.matwana.view.ui.theme.background
import inoxoft.simon.matwana.view.ui.theme.text
import kotlinx.coroutines.delay

@Composable
fun OTPVerificationScreen(navController: NavHostController, phoneNumber: String) {
    var otp1 by remember { mutableStateOf("") }
    var otp2 by remember { mutableStateOf("") }
    var otp3 by remember { mutableStateOf("") }
    var otp4 by remember { mutableStateOf("") }
    var timer by remember { mutableStateOf(30) }
    var isResendEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(timer) {
        if (timer > 0) {
            delay(1000L)
            timer -= 1
        } else {
            isResendEnabled = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                background
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title and Description
            Text(
                text = "OTP Verification",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = text
            )

            Text(
                text = "Enter the OTP sent to $phoneNumber",
                fontSize = 16.sp,
                color = text,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // OTP Input Fields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OTPTextField(otp1) { otp1 = it }
                OTPTextField(otp2) { otp2 = it }
                OTPTextField(otp3) { otp3 = it }
                OTPTextField(otp4) { otp4 = it }
            }

            // Verify Button
            Button(
                onClick = {
                    navController.navigate("new_password")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = text)
            ) {
                Text("Verify", fontSize = 18.sp, color = Color.White)
            }

            // Resend OTP Option
            Text(
                text = if (isResendEnabled) "Resend OTP" else "Resend in $timer seconds",
                fontSize = 14.sp,
                color = if (isResendEnabled) text else text,
                modifier = Modifier
                    .clickable(enabled = isResendEnabled) {
                        // Handle resend OTP logic
                        timer = 30
                        isResendEnabled = false
                    }
                    .padding(vertical = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPTextField(
        value: String,
        onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = {
            if (it.length <= 1) onValueChange(it)
        },
        modifier = Modifier
            .width(60.dp)
            .height(60.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        singleLine = true
    )
}
