package inoxoft.simon.matwana.view.pages.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun BookingConfirmationScreen(
        navController: NavHostController,
        selectedRoute: String,
        selectedDate: String,
        selectedTime: String,
        selectedSeat: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Booking Confirmation",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display booking details
        Text(text = "Route: $selectedRoute", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Date: $selectedDate", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Departure Time: $selectedTime", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Seat: $selectedSeat", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Confirmation section
        Text(
            text = "Are you sure you want to confirm your booking?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirmation button
        Button(
            onClick = {

                Toast.makeText(navController.context, "Seat reserved", Toast.LENGTH_SHORT).show()
                // Navigate to the payment screen or show a confirmation message

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
        ) {
            Text(
                text = "Confirm Booking",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Payment button
        Button(
            onClick = {

                Toast.makeText(navController.context, "Input mpesa pin to pay", Toast.LENGTH_SHORT).show()
                // Handle the payment process (navigate or show payment options)

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
        ) {
            Text(
                text = "Proceed to Payment",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

