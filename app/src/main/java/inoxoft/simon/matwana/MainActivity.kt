package inoxoft.simon.matwana


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import inoxoft.simon.matwana.model.auth.Database
import inoxoft.simon.matwana.repository.UserRepository
import inoxoft.simon.matwana.view.MatwanaNavigation
import inoxoft.simon.matwana.view.ui.theme.MatwanaTheme
import inoxoft.simon.matwana.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //database
        val db by lazy {
            Room.databaseBuilder(
                applicationContext,
                Database::class.java,
                name = "User.dp"
            ).build()
        }
        //TODO : Add viewmodel provider
      val viewModel by viewModels<UserViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return UserViewModel(UserRepository(db)) as T
                        }
                    }
                }

        )

        setContent {
            MatwanaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MatwanaNavigation(modifier=Modifier.padding(innerPadding),viewModel)
                }
            }
        }
    }
}