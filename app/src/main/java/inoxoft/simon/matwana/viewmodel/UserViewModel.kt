package inoxoft.simon.matwana.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import inoxoft.simon.matwana.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    // Define AuthState to represent the current state of authentication
    sealed class AuthState {
        object Unauthenticated : AuthState()
        object Authenticating : AuthState()
        data class Authenticated(val user: FirebaseUser) : AuthState()
        data class Error(val message: String) : AuthState()
    }

    // Backing field for auth state
    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    //setting up a variable that will hold the instance of firebase
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    init{
        checkAuthenticationStatus()
    }
    /**
     * Check if the user is already authenticated and update the state accordingly.
     */
    fun checkAuthenticationStatus() {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            _authState.value = AuthState.Authenticated(currentUser)
        } else {
            _authState.value = AuthState.Unauthenticated
        }
    }

    /**
     * Create a new user using Firebase Authentication and add user details to the Room database.
     */
    fun createUser(email: String, password: String, name: String, phoneNumber: String) {
        _authState.value = AuthState.Authenticating
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.user?.let { firebaseUser ->
                        // Update Auth State
                        _authState.value = AuthState.Authenticated(firebaseUser)


                    } ?: run {
                        _authState.value = AuthState.Error("User creation failed: Unknown error")
                    }
                } else {
                    _authState.value = AuthState.Error(
                        task.exception?.message ?: "User creation failed"
                    )
                }
            }
    }

    /**
     * Authenticate an existing user using Firebase Authentication.
     */
    fun authenticateUser(email: String, password: String) {
        // Set the state to Authenticating to indicate the process has started
        _authState.value = AuthState.Authenticating

        // Data Validation
        val error = validateInput(email, password)
        if (error != null) {
            _authState.value = AuthState.Error(error)
            return
        }

        // Proceed with Firebase authentication if input is valid
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.user?.let {
                        _authState.value = AuthState.Authenticated(it)
                    } ?: run {
                        _authState.value = AuthState.Error("Authentication failed: Unknown error")
                    }
                } else {
                    _authState.value = AuthState.Error(
                        task.exception?.message ?: "Authentication failed"
                    )
                }
            }
    }

    // Input validation function
    private fun validateInput(email: String, password: String): String? {
        // Check if email is empty
        if (email.isBlank()) return "Email cannot be empty"

        // Check if email is valid
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Invalid email format"
        }

        // Check if password is empty
        if (password.isBlank()) return "Password cannot be empty"

        // Check password length (example: minimum 6 characters for Firebase)
        if (password.length < 6) return "Password must be at least 6 characters long"

        // No validation errors
        return null
    }

    /**
     * Sign out the current user and update the state to unauthenticated.
     */
    fun signOut() {
        firebaseAuth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    /**
     * Get the currently signed-in Firebase user.
     */
    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser
}
