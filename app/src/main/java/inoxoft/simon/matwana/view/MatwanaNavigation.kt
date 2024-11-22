package inoxoft.simon.matwana.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import inoxoft.simon.matwana.view.pages.Auth.LoginScreen
import inoxoft.simon.matwana.view.pages.Auth.NewPasswordScreen
import inoxoft.simon.matwana.view.pages.Auth.OTPVerificationScreen
import inoxoft.simon.matwana.view.pages.Auth.SignUpScreen
import inoxoft.simon.matwana.view.pages.Auth.WelcomeScreen
import inoxoft.simon.matwana.view.pages.home.BookingConfirmationScreen
import inoxoft.simon.matwana.view.pages.home.BookingScreen
import inoxoft.simon.matwana.view.pages.home.HomePageScreen
import inoxoft.simon.matwana.view.pages.home.ProfileScreen
import inoxoft.simon.matwana.viewmodel.UserViewModel
import androidx.compose.runtime.collectAsState




@Composable
fun MatwanaNavigation(modifier: Modifier = Modifier, viewModel: UserViewModel) {
    val navController = rememberNavController()


// Observe the authentication state from the ViewModel
    val authState by viewModel.authState.collectAsState()

// Automatically navigate based on the current authentication state
    LaunchedEffect(authState) {
        when (authState) {
            is UserViewModel.AuthState.Authenticated -> {
                // Navigate to the homepage if authenticated
                navController.navigate("homepage")
            }
            is UserViewModel.AuthState.Unauthenticated -> {
                // Navigate to the welcome page if unauthenticated
                navController.navigate("welcome")
            }

            UserViewModel.AuthState.Authenticating -> {
                // Handle authenticating state: Show a loading indicator
                println("Authenticating... Please wait.")
            }
            is UserViewModel.AuthState.Error -> {
                // Handle the error state: Display an error message
                val errorMessage = (authState as UserViewModel.AuthState.Error).message
                println("Error occurred during authentication: $errorMessage")
            }
        }
    }

    // Define the navigation graph
    NavHost(
        navController = navController,
        startDestination = "welcome", // Default start destination
        modifier = modifier
    ) {
        // Welcome screen for unauthenticated users
        composable("welcome") { WelcomeScreen(navController) }

        // Login screen
        composable("login") { LoginScreen(navController, viewModel) }

        // Sign-up screen
        composable("sign_Up") { SignUpScreen(navController, viewModel) }

        // Screen for resetting password
        composable("new_password") { NewPasswordScreen(navController) }

        // OTP Verification screen
        composable("OTP_Verification") { OTPVerificationScreen(navController, "") }

        // Home page for authenticated users
        composable("homepage") { HomePageScreen(navController) }

        // Booking screen
        composable("booking") { BookingScreen(navController) }

        // Profile screen
        composable("profile") { ProfileScreen(navController, viewModel) }

        // Booking confirmation screen with arguments
        composable(
            route = "bookingConfirmation/{selectedRoute}/{selectedDate}/{selectedTime}/{selectedSeat}",
            arguments = listOf(
                navArgument("selectedRoute") { type = NavType.StringType },
                navArgument("selectedDate") { type = NavType.StringType },
                navArgument("selectedTime") { type = NavType.StringType },
                navArgument("selectedSeat") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val selectedRoute = backStackEntry.arguments?.getString("selectedRoute") ?: ""
            val selectedDate = backStackEntry.arguments?.getString("selectedDate") ?: ""
            val selectedTime = backStackEntry.arguments?.getString("selectedTime") ?: ""
            val selectedSeat = backStackEntry.arguments?.getString("selectedSeat") ?: ""

            BookingConfirmationScreen(
                navController = navController,
                selectedRoute = selectedRoute,
                selectedDate = selectedDate,
                selectedTime = selectedTime,
                selectedSeat = selectedSeat
            )
        }
    }
}
