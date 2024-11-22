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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import inoxoft.simon.matwana.R
import inoxoft.simon.matwana.view.ui.theme.background
import inoxoft.simon.matwana.view.ui.theme.text
import inoxoft.simon.matwana.viewmodel.UserViewModel
import inoxoft.simon.matwana.viewmodel.UserViewModel.AuthState

@Composable
fun SignUpScreen(navController: NavHostController, viewModel: UserViewModel) {



    //observe the authstate from viewmodel via stateflow
    val authState by viewModel.authState.collectAsState()
    val currentUser = viewModel.getCurrentUser()

    //login only when authstate is authenticated
    if (authState is AuthState.Authenticated) {
        navController.navigate("homepage")
    }





    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }



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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Logo at the top
            Image(
                painter = painterResource(id = R.drawable.matwana), // Replace with actual logo resource
                contentDescription = "Matwana Logo",
                modifier = Modifier.size(150.dp).padding(top = 40.dp)
            )

            Text(
                text = "Create Account!",
                style = MaterialTheme.typography.headlineMedium,
                color = text,
                modifier = Modifier.padding(bottom = 24.dp)
            )


            // Input Fields
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // name TextField
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    //rounded border
                    shape = RoundedCornerShape(22.dp), // Sets rounded corners
                    label = { Text("Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
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
                // phone number textfield
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    //rounded border
                    shape = RoundedCornerShape(22.dp), // Sets rounded corners
                    label = { Text("Phone number") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )
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
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Sign Up Button
            Button(
                onClick = {
                    viewModel.createUser(name,email,phone,password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = text)
            ) {
                Text("Sign Up", fontSize = 18.sp, color = Color.White)
            }

            // Login Option
            Text(
                text = "Already have an account? Login",
                fontSize = 14.sp,
                color = text,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable { navController.navigate("login") } // Navigate to login screen
                    .padding(16.dp)
            )
        }
    }
}

