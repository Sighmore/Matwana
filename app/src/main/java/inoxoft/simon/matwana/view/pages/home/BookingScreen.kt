package inoxoft.simon.matwana.view.pages.home

import android.app.DatePickerDialog
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun BookingScreen(navController: NavHostController) {
    var selectedRoute by remember { mutableStateOf("") }
    val routes = listOf("Nairobi - Mombasa", "Kisumu - Eldoret", "Nairobi - Nakuru")

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showDatePickerDialog by remember { mutableStateOf(false) }

    var selectedTime by remember { mutableStateOf("") }
    val times = listOf("08:00 AM", "10:00 AM", "01:00 PM", "04:00 PM", "06:00 PM")

    var selectedSeat by remember { mutableStateOf("") }
    val seats = listOf("A1", "A2", "A3", "B1", "B2", "B3")

    var showSeatPreview by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Screen title
        Text(
            text = "Book Your Trip",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Route selection dropdown
        CustomDropdownMenu(
            label = "Select Route",
            options = routes,
            selectedOption = selectedRoute,
            onOptionSelected = { selectedRoute = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Date picker
        Text(text = "Select Date", fontSize = 18.sp, fontWeight = FontWeight.Medium)
        DatePicker(
            date = selectedDate,
            onDateSelected = { selectedDate = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Departure time selection dropdown
        CustomDropdownMenu(
            label = "Select Desired Departure Time",
            options = times,
            selectedOption = selectedTime,
            onOptionSelected = { selectedTime = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Show seat preview button
        Button(
            onClick = { showSeatPreview = !showSeatPreview },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
        ) {
            Text(
                text = if (showSeatPreview) "Hide Seat Preview" else "Show Seat Preview",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Seat preview and selection
        if (showSeatPreview) {
            SeatPreview(seats)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Seat selection dropdown
        CustomDropdownMenu(
            label = "Select Seat",
            options = seats,
            selectedOption = selectedSeat,
            onOptionSelected = { selectedSeat = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirmation button
        Button(
            onClick = {
                // Handle booking confirmation
                navController.navigate("bookingConfirmation/$selectedRoute/$selectedDate/$selectedTime/$selectedSeat") // Navigate to confirmation screen
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenu(
        label: String,
        options: List<String>,
        selectedOption: String,
        onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = { },
            label = { Text(text = label) },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DatePicker(
        date: LocalDate,
        onDateSelected: (LocalDate) -> Unit
) {
    val context = LocalContext.current
    var showDatePickerDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable {
                showDatePickerDialog = true
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            fontSize = 16.sp,
            color = Color.Black
        )
    }

    // Show the DatePicker dialog on click
    if (showDatePickerDialog) {
        val calendar = Calendar.getInstance()
        calendar.time = Date.from(date.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant())

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDayOfMonth)
                onDateSelected(selectedDate)
            },
            year,
            month,
            day
        ).show()
    }
}

@Composable
fun SeatPreview(seats: List<String>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Visualization of seats
        Text(text = "Seat Arrangement", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        // Create rows of seats for a 14-seater
        Row(horizontalArrangement = Arrangement.Center) {
            seats.take(3).forEach {
                SeatBox(seat = it)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            seats.drop(3).take(3).forEach {
                SeatBox(seat = it)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            seats.drop(6).take(3).forEach {
                SeatBox(seat = it)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            seats.drop(9).take(3).forEach {
                SeatBox(seat = it)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            seats.drop(12).take(2).forEach {
                SeatBox(seat = it)
            }
        }
    }
}

@Composable
fun SeatBox(seat: String) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(Color.Gray, RoundedCornerShape(8.dp))
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = seat,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
