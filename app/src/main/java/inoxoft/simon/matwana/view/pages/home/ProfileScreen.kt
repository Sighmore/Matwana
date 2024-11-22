package inoxoft.simon.matwana.view.pages.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import inoxoft.simon.matwana.R
import inoxoft.simon.matwana.viewmodel.UserViewModel

@Composable
fun ProfileScreen(navController: NavHostController, viewmodel: UserViewModel) {

    // Sample profile data
    val userName = "Simon Kuria"
    val userEmail = "simon.kuria@matwana.com"
    val userPhone = "+254 712 345678"

    // State variables for editable fields
    var isEditing by remember { mutableStateOf(false) }
    var editableName by remember { mutableStateOf(userName) }
    var editableEmail by remember { mutableStateOf(userEmail) }
    var editablePhone by remember { mutableStateOf(userPhone) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile picture (Circle)
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.Gray, CircleShape)
                .border(2.dp, Color.Black, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // Placeholder image for profile picture
            Icon(
                painter = painterResource(id = R.drawable.sacco), // Replace with your actual drawable
                contentDescription = "Profile Picture",
                tint = Color.White,
                modifier = Modifier.size(60.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // User name
        Text(
            text = "Name:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        if (isEditing) {
            TextField(
                value = editableName,
                onValueChange = { editableName = it },
                label = { Text("Edit Name") },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(text = editableName, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Email
        Text(
            text = "Email:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        if (isEditing) {
            TextField(
                value = editableEmail,
                onValueChange = { editableEmail = it },
                label = { Text("Edit Email") },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(text = editableEmail, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Phone number
        Text(
            text = "TEL:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        if (isEditing) {
            TextField(
                value = editablePhone,
                onValueChange = { editablePhone = it },
                label = { Text("Edit Phone") },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(text = editablePhone, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Edit/Save button
        Button(
            onClick = {
                if (isEditing) {
                    // Save changes here
                    Toast.makeText(navController.context, "Profile updated", Toast.LENGTH_SHORT).show()
                }
                isEditing = !isEditing
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (isEditing) "Save" else "Edit Profile",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Log Out button
        Button(
            onClick = {
                // Handle logout logic
                Toast.makeText(navController.context, "Logging out...", Toast.LENGTH_SHORT).show()
                viewmodel.signOut() // Navigate to login screen
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C))
        ) {
            Text(
                text = "Log Out",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

