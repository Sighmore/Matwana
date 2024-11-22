package inoxoft.simon.matwana.view.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import inoxoft.simon.matwana.R
import inoxoft.simon.matwana.view.ui.theme.background
import inoxoft.simon.matwana.view.ui.theme.text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageScreen(navController: NavHostController) {

    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(16.dp)
    ) {
        // Header and Search
        Text(
            text = "Safiri na Matwana",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color =text,
            modifier = Modifier.padding(bottom = 8.dp).align(alignment = Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = search,
            onValueChange = {search=it},
            label = { Text("Search routes or destinations") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                focusedBorderColor = text
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Popular Routes Section
        Text(
            text = "Popular Routes",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1B5E20),
            modifier = Modifier.padding(vertical = 4.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val routes = listOf(
                "Nairobi - Mombasa",
                "Nakuru - Kitale",
                "Kisumu - Eldoret",
                "Nairobi - Nakuru",
                "Nakuru - Nyeri"
            )
            items(routes) { route: String ->
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50)),
                    modifier = Modifier
                        .size(width = 160.dp, height = 100.dp)
                        .clickable {
                            // Navigate to booking details
                        }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = route,
                            fontSize = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Featured Offers Section
        Text(
            text = "Special Offers",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1B5E20),
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFBC02D)),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(vertical = 8.dp)
                .clickable {
                    // Navigate to offers page
                }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "50% off on your first ride!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val weatherData = listOf(
                Triple("Nairobi", "24°C, Sunny", R.drawable.sunny),
                Triple("Mombasa", "30°C, Cloudy", R.drawable.cloudy),
                Triple("Kisumu", "26°C, Rainy", R.drawable.rainy),
                Triple("Nakuru", "22°C, Clear", R.drawable.sky)
            )

            items(weatherData) { (city, weather, iconRes) ->
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF64B5F6)),
                    modifier = Modifier
                        .size(width = 120.dp, height = 100.dp) // Adjusted height for better layout
                        .clickable {
                            // Navigate to weather details if needed
                        }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(8.dp)
                    ) {

                        Spacer(modifier = Modifier.height(3.dp))

                        // Display city name
                        Text(
                            text = city,  // Text composable expects a String type
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        // Display the weather icon
                        Image(
                            painter = painterResource(id = iconRes),
                            contentDescription = "Weather Icon",
                            modifier = Modifier.size(35.dp)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Display weather information
                        Text(
                            text = weather, // Text composable expects a String type
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp).weight(1f))


        // Bottom Navigation Bar with Icons.Default
        NavigationBar(
            containerColor = text,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = Color.White
                    )
                },
                label = { Text("Home", color = Color.White) },
                selected = true,
                onClick = { /* Handle Home Click */ }
            )



            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.DirectionsBus,
                        contentDescription = "Booking",
                        tint = Color.White
                    )
                },
                label = { Text("Booking", color = Color.White) },
                selected = false,
                onClick = {
                    navController.navigate("booking")
                }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile",
                        tint = Color.White
                    )
                },
                label = { Text("Profile", color = Color.White) },
                selected = false,
                onClick = {
                    navController.navigate("profile")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomePageScreen() {
    HomePageScreen(navController = rememberNavController())
}