package inoxoft.simon.matwana.view.pages.Auth


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import inoxoft.simon.matwana.R
import inoxoft.simon.matwana.view.ui.theme.background
import inoxoft.simon.matwana.view.ui.theme.text
import inoxoft.simon.matwana.viewmodel.UserViewModel
import inoxoft.simon.matwana.viewmodel.UserViewModel.AuthState


@Composable
fun LoginScreen(navController: NavHostController, viewModel: UserViewModel) {
    // State variables to hold email and password input
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    //observe the authstate from viewmodel via stateflow
    val authState by viewModel.authState.collectAsState()
    val currentUser = viewModel.getCurrentUser()

    //login only when authstate is authenticated
    if (authState is AuthState.Authenticated) {
        navController.navigate("homepage")
    }

    // Main Column for arranging components
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //icon image
        Image(
            painter = painterResource(id = R.drawable.matwana), // Replace with actual logo resource
            contentDescription = "Matwana Logo",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 10.dp),
            contentScale = ContentScale.Fit
        )
        // Title
        Text(
            text = "Welcome Back!",
            style = MaterialTheme.typography.headlineMedium,
            color = text,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            //rounded border
            shape = RoundedCornerShape(22.dp), // Sets rounded corners
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            shape = RoundedCornerShape(22.dp),
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        )

        // Forgot Password Text
        Text(
            text = "Forgot Password?",
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.End,
            modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = 24.dp)
                .clickable {
                    navController.navigate("OTP_Verification")
                },
            style = MaterialTheme.typography.bodyMedium,
            textDecoration = TextDecoration.Underline
        )

        // Login Button
        Button(
            onClick = {
                viewModel.authenticateUser(email,password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = text)
        ) {
            Text(text = "Login", fontSize = 18.sp, color = Color.White)
        }

        // Sign Up Option
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Don't have an account?", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Sign up",
                color = text,
                modifier = Modifier.clickable {
                   navController.navigate("sign_Up")
                },
                style = MaterialTheme.typography.bodyMedium,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}


