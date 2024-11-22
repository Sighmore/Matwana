package inoxoft.simon.matwana.view.pages.Auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import inoxoft.simon.matwana.R
import inoxoft.simon.matwana.view.ui.theme.background
import inoxoft.simon.matwana.view.ui.theme.lightBlack
import inoxoft.simon.matwana.view.ui.theme.text

@Composable
fun WelcomeScreen(navController: NavHostController) {


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
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo at the top
            Image(
                painter = painterResource(id = R.drawable.matwana), // Replace with actual logo resource
                contentDescription = "Matwana Logo",
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 40.dp),
                contentScale = ContentScale.Fit
            )

            // Welcome Message
            Text(
                text = "Welcome to Matwana!",
                fontSize = 30.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.Bold,
                color = text,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 20.dp)
            )

            // Brief App Description
            Text(
                text = "Book your matatu trips seamlessly for all your long-distance travels across Kenya.",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = lightBlack,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Action Buttons: Login and Sign Up
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        navController.navigate("login")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("Login", fontSize = 18.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = {
                        navController.navigate("sign_Up")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = text)
                ) {
                    Text("Sign Up", fontSize = 18.sp, color = text)
                }
            }

            Spacer(modifier = Modifier.height(40.dp)) // Extra space at the bottom
        }
    }
}


